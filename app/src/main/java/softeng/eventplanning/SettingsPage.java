package softeng.eventplanning;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import API.SettingsAPI;

/**
 * Created by josh on 11/2/16.
 */
public class SettingsPage extends AppCompatActivity {
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



    }
    public void updateSettingsButtonClicked(View v){
        EditText passCheck = (EditText) findViewById(R.id.passCheck1);
        String pass = passCheck.getText().toString();
        passCheck = (EditText) findViewById(R.id.passCheck2);
        if(pass.equals(passCheck.getText().toString())) {


            SettingsAPI settingsAPI = new SettingsAPI();
            settingsAPI.signupActivity(this);
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
            placeHolder = (EditText) findViewById(R.id.user_bio);
            accountSettings[6] = placeHolder.getText().toString();
            LoggedInUser.setBio(placeHolder.getText().toString());
            placeHolder = (EditText) findViewById(R.id.currentPass);
            accountSettings[7] = placeHolder.getText().toString();
            settingsAPI.accountArray(accountSettings);
            settingsAPI.execute();
        }
        else{
            int duration = Toast.LENGTH_SHORT;
            Context context = this.getApplication();
            Toast.makeText(context,"Invalid Password",duration).show();
        }

    }

}
