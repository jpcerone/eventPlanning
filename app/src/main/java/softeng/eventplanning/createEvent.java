package softeng.eventplanning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.Arrays;

import API.CreateEventAPI;
import API.CreateUserAPI;

/**
 * Created by jpcerone on 10/3/16.
 */

public class createEvent extends AppCompatActivity {
private String mprivate;
private String malerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);


        final EditText description = (EditText) findViewById(R.id.eventDesc);
        final EditText eventTitle = (EditText) findViewById(R.id.eventTitle);
        final String[] create_event = new String[9];

        ImageButton send = (ImageButton) findViewById(R.id.done);
        Switch priv_event = (Switch) findViewById(R.id.switch1);
        Switch alerts = (Switch) findViewById(R.id.switch2);
        alerts.setChecked(false);
        priv_event.setChecked(false);
        mprivate = "no";
        malerts = "no";
        final String desc = description.getText().toString();
        final String title = eventTitle.getText().toString();

        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        description.setText(prefs.getString("autoSave", ""));
        eventTitle.setText(prefs.getString("autoSaveTitle", ""));


        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString("autoSave", s.toString()).commit();
            }
        });

        priv_event.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    mprivate = "yes";
                }
                    else{
                    mprivate = "no";
                }

            }
        });

        alerts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    malerts = "yes";
                }
                else{
                    malerts = "no";
                }

            }
        });

        eventTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString("autoSaveTitle", s.toString()).commit();
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();
                String date = bundle.getString("JSON_DATE");
                String time = bundle.getString("JSON_TIME");

                create_event[0] = date;
                create_event[1] = "12:34";
                create_event[2] = "location";
                create_event[3] = title;
                create_event[4] = desc;
                create_event[5] = "friends";
                create_event[6] =  "image";
                create_event[7] = "user";
                create_event[8] = malerts;
                Log.d("myTag", Arrays.toString(create_event));
                prefs.edit().clear().commit();
                final CreateEventAPI asyncT = createAsyncTask();
                asyncT.setsomething(create_event);

                asyncT.execute();



            }


        });

    }
    public void setLocation(View v){
        Intent eventLocationChange = new Intent(this, createEventLocation.class);
        startActivity(eventLocationChange);
    }
    public void setDate(View v){
        Intent eventDateChange = new Intent(this, createEventDate.class);
        startActivity(eventDateChange);
    }
    public void setTime(View v){
        Intent eventTimeChange = new Intent(this, createEventTime.class);
        startActivity(eventTimeChange);
    }

    private CreateEventAPI createAsyncTask(){
        CreateEventAPI api = new CreateEventAPI();
        api.signupActivity(this);
        return api;
    }


}