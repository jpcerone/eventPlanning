package softeng.eventplanning;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import API.CreateEventAPI;

/**
 * Created by brandonboyle on 11/3/16.
 */




public class createEvent extends AppCompatActivity  {

        Calendar Cal = Calendar.getInstance();
        private String date1;
        private String time;
        private String Edesc;
        private String locS;
        private String event_name;

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


            Edesc = description.getText().toString();
            event_name = eventTitle.getText().toString();
            locS = location.getText().toString();

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

                    create_event[0] = date1;
                    create_event[1] = time;
                    create_event[2] = locS;
                    create_event[3] = event_name;
                    create_event[4] = Edesc;
                    create_event[5] = "friends";
                    create_event[6] = "image";
                    create_event[7] = "owner";
                    create_event[8] = "arrival not";
                    create_event[9] =  "user id";
                    create_event[10] = "lat";
                    create_event[11] = "long";
                    create_event[12] = "pub_priv";

                    final CreateEventAPI asyncT = createAsyncTask();
                    asyncT.setsomething(create_event);

                    asyncT.execute();
                }


            });
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

}






