package softeng.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jpcerone on 10/3/16.
 */

public class createEventTime  extends AppCompatActivity {
private String mtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_time);

        final TimePicker times;

        times = (TimePicker) findViewById(R.id.timePicker);
        times.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String hour = Integer.toString(hourOfDay);
                String minutes = Integer.toString(minute);
                mtime = hour + ':' + minutes;
            }
        });




    }




    public void back(View v){
        Intent createEventChange = new Intent(this,createEvent.class);
        createEventChange.putExtra("JSON_TIME", mtime);
        startActivity(createEventChange);
    }

}
