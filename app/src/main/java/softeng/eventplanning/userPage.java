package softeng.eventplanning;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import API.SettingsAPI;


public class userPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);




    }
    public void updateUserPage(String[] info){
        TextView editTextPlace;

        editTextPlace = (TextView) findViewById(R.id.user_bday);
        editTextPlace.setText(info[0]);
        editTextPlace = (TextView) findViewById(R.id.user_fname);
        editTextPlace.setText(info[1]);
        editTextPlace = (TextView) findViewById(R.id.user_lname);
        editTextPlace.setText(info[2]);
        editTextPlace = (TextView) findViewById(R.id.user_bio);
        editTextPlace.setText(info[3]);
        editTextPlace = (TextView) findViewById(R.id.user_phone);
        editTextPlace.setText(info[4]);
        editTextPlace = (TextView) findViewById(R.id.user_username);
        editTextPlace.setText(info[5]);
    }


}
