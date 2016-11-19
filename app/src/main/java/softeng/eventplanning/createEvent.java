package softeng.eventplanning;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;


import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import API.CreateEventAPI;

/**
 * Created by brandonboyle on 11/3/16.
 */




public class createEvent extends AppCompatActivity  {




    Calendar Cal = Calendar.getInstance();
    protected static final String TAG = "CreateEvent";
        private String date1;
        private String time;
        private String Edesc;
        private String locS;
        private String event_name;

        private String listOfParts = "";

        private String pub;
        private static int RESULT_LOAD_IMG=0;
        private static int REQUEST_READ;
        private byte[] imgByte = null;
        String _encoded;
        private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        };



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.create_event_new);

            final String[] create_event = new String[13];
            final EditText description = (EditText) findViewById(R.id.Desc);
            final EditText eventTitle = (EditText) findViewById(R.id.eventT);
            final TextView date_v = (TextView) findViewById(R.id.date_view);
            final TextView time_v = (TextView) findViewById(R.id.time_view);
            final EditText location = (EditText) findViewById(R.id.loc);
            final String user = LoggedInUser.username;

            Edesc = description.getText().toString();
            event_name = eventTitle.getText().toString();
            locS = location.getText().toString();


            for(int i=0;i<LoggedInUser.friendsList.size();i++){
                CheckBox rbtn = new CheckBox(this);
                rbtn.setId(i);
                rbtn.setText(LoggedInUser.friendsList.get(i));
                ((ViewGroup)findViewById(R.id.checkBoxGroup)).addView(rbtn);

            }

            final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);


            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    Cal.set(Calendar.YEAR, year);
                    Cal.set(Calendar.MONTH, monthOfYear);
                    Cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(date_v);


                }
            };

            date_v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(createEvent.this, date, Cal
                            .get(Calendar.YEAR), Cal.get(Calendar.MONTH),
                            Cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            time_v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(createEvent.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            time_v.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                        }
                    }, hour, minute, false);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });

            Button go = (Button) findViewById(R.id.create);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateTime(time_v);
                    updateDate(date_v);
                    Log.d("date", date1);
                    Log.d("time", time);


                    if (checkBox.isChecked()) {
                        pub = "1";
                    } else {
                        pub = "0";
                    }
                    Edesc = description.getText().toString();
                    event_name = eventTitle.getText().toString();
                    locS = location.getText().toString();
                    LinearLayout layout = (LinearLayout)findViewById(R.id.checkBoxGroup);
                    formIsValid(layout);
                    if (CheckFields() == true) {
                        create_event[0] = date1;
                        create_event[1] = time;
                        create_event[2] = locS;
                        create_event[3] = event_name;
                        create_event[4] = Edesc;
                        create_event[5] = listOfParts;
                        create_event[6] = _encoded;
                        create_event[7] = user;
                        create_event[10] = "42.0";
                        create_event[11] = "15.63";
                        create_event[12] = pub;

                        final CreateEventAPI asyncT = createAsyncTask();
                        asyncT.setsomething(create_event);

                        asyncT.execute();
                    }

                }
            });
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



                ImageView imgView = (ImageView) findViewById(R.id.imageView6);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(imgMap);
            } else {
                Toast.makeText(this, "Please Select an Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Could not get image", Toast.LENGTH_LONG).show();
        }
    }


        private void updateDate(TextView views){
            date1 = views.getText().toString();
        }

        private void updateTime(TextView views){
            time = views.getText().toString();
        }

        private void updateLabel(TextView views) {
            String myFormat = "dd-MM-yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            views.setText(sdf.format(Cal.getTime()));
        }

     private CreateEventAPI createAsyncTask(){
        CreateEventAPI api = new CreateEventAPI();
        api.signupActivity(this);
        return api;
    }
    public void formIsValid(LinearLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            CheckBox v = (CheckBox) layout.getChildAt(i);
            if(listOfParts.equals("")){
                listOfParts = listOfParts + v.getText().toString();
            }
            else{
                listOfParts = listOfParts + " " + v.getText().toString();
            }

        }
    }

    private boolean CheckFields(){
        if(checkTitle() == false){
            Toast.makeText(this, "Enter an Event Title", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(checkDesc() == false){
            Toast.makeText(this, "Enter a Description", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(checkDate() == false){
            Toast.makeText(this, "Select a Date", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(checkTime() == false){
            Toast.makeText(this, "Select a Time", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(checkLocation() == false){
            Toast.makeText(this, "Enter Location", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkTitle(){
        if (event_name.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkDesc(){
        if (Edesc.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkDate(){
        if (date1.equals("Date")) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkTime(){
        if (time.equals("Time")) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkLocation(){
        if (locS.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }


}






