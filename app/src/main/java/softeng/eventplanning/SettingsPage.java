package softeng.eventplanning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import API.SettingsAPI;

/**
 * Created by josh on 11/2/16.
 */
public class SettingsPage extends AppCompatActivity {
    private static int RESULT_LOAD_IMG=0;
    private static int REQUEST_READ;
    String _encoded;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private SettingsAPI settings;
    private String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        EditText editTextPlace;

        editTextPlace = (EditText) findViewById(R.id.bday);
        editTextPlace.setText(LoggedInUser.birthday);
        editTextPlace = (EditText) findViewById(R.id.fname);
        editTextPlace.setText(LoggedInUser.fName);
        editTextPlace = (EditText) findViewById(R.id.lname);
        editTextPlace.setText(LoggedInUser.lName);
        editTextPlace = (EditText) findViewById(R.id.bio);
        editTextPlace.setText(LoggedInUser.bio);
        if(!LoggedInUser.encoded.equals("")){
            ImageView image = (ImageView) findViewById(R.id.userImage);
            image.setImageBitmap(LoggedInUser.image);
        }
        SettingsAPI settingsAPI = new SettingsAPI();
        settings = settingsAPI;



    }
    public void updateSettingsButtonClicked(View v){
        EditText passCheck = (EditText) findViewById(R.id.passCheck1);
        String pass = passCheck.getText().toString();
        passCheck = (EditText) findViewById(R.id.passCheck2);
        if(pass.equals(passCheck.getText().toString())) {
            settings.signupActivity(this);
            String[] accountSettings = new String[8];

            TextView placeHolder;

            //TextView placeHolder = (TextView)findViewById(R.id.usernameInput);
            accountSettings[0] = LoggedInUser.username;
            placeHolder = (EditText) findViewById(R.id.passCheck1);
            accountSettings[1] = placeHolder.getText().toString();
            placeHolder = (EditText) findViewById(R.id.bday);
            accountSettings[2] = placeHolder.getText().toString();
            LoggedInUser.setBirthday(placeHolder.getText().toString());


            placeHolder = (TextView) findViewById(R.id.phone);
            accountSettings[3] = placeHolder.getText().toString();
            placeHolder = (EditText) findViewById(R.id.fname);
            accountSettings[4] = placeHolder.getText().toString();
            LoggedInUser.setfName(placeHolder.getText().toString());
            placeHolder = (EditText) findViewById(R.id.lname);
            accountSettings[5] = placeHolder.getText().toString();
            LoggedInUser.setlName(placeHolder.getText().toString());
            placeHolder = (EditText) findViewById(R.id.bio);
            accountSettings[6] = placeHolder.getText().toString();
            LoggedInUser.setBio(placeHolder.getText().toString());
            placeHolder = (EditText) findViewById(R.id.currentPass);
            accountSettings[7] = placeHolder.getText().toString();
            settings.accountArray(accountSettings);
            settings.execute();
        }
        else{
            int duration = Toast.LENGTH_SHORT;
            Context context = this.getApplication();
            Toast.makeText(context,"Invalid Password",duration).show();
        }

    }

    public void selectImage(View v){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Log.d("IMG","START");
        super.onActivityResult(requestCode, resultCode, data);
        try {
            int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_READ);
            }
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgString;
                imgString = cursor.getString(columnIndex);
                File f = new File(imgString);
                FileInputStream fs = new FileInputStream(f);
                byte[] bytes = new byte[(int)f.length()];
                fs.read(bytes);
                String encodedFile = new String(Base64.encode(bytes,Base64.DEFAULT),"UTF-8");
                _encoded = encodedFile;
                Bitmap imgMap = BitmapFactory.decodeFile(imgString);
                cursor.close();

                //Convert Image to Blob
                ByteArrayOutputStream ba = new ByteArrayOutputStream();
//                imgMap.compress(Bitmap.CompressFormat.JPEG,100,ba);
//                byte[] bArray = ba.toByteArray();
                settings.setImage(_encoded);
                Log.d("IMG",_encoded);

                ImageView imgView = (ImageView) findViewById(R.id.userImage);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(imgMap);

            } else {
                Toast.makeText(this, "Please Select an Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Could not get image", Toast.LENGTH_LONG).show();
            Log.d("Fail",e.toString());
        }

    }

}
