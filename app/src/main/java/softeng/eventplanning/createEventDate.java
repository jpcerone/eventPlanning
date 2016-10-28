package softeng.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by jpcerone on 10/3/16.
 */

public class createEventDate extends AppCompatActivity {
private String mjson_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_date);
        final CalendarView calendar;

        final TextView cur_date;
        calendar = (CalendarView) findViewById(R.id.calendarView);
        cur_date = (TextView) findViewById(R.id.dateview);
        long date = calendar.getDate();
        calendar.setDate(date);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                cur_date.setText((month + 1) + " / " + dayOfMonth + " / " + year);
                String years = Integer.toString(year);
                String months = Integer.toString(month+1);
                String days =  Integer.toString((dayOfMonth));
                mjson_date = months + '-' +  days + '-' + years;
            }
        });



    }
    public void back(View v){

        Intent createEventChange = new Intent(this,createEvent.class);
        createEventChange.putExtra("JSON_DATE", mjson_date);

        startActivity(createEventChange);
    }
}
