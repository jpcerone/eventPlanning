package softeng.eventplanning;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.Date;


public class sFilters extends AppCompatActivity {
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfilter);
        CalendarView cv = (CalendarView)findViewById(R.id.fromNowToCal);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int d = dayOfMonth;
                int m = month + 1;
                date = d + "/" + m + "/" + year;

            }
        });
        SeekBar sb = (SeekBar)findViewById(R.id.mileBar);
        sb.setMax(10);
    }
    public void filterBack(View view)
    {
        SeekBar s = (SeekBar)findViewById(R.id.mileBar);
        double radius = s.getProgress();
        Switch publicprivSwitch = (Switch)findViewById(R.id.publicPrivSwitch);
        int publicpriv = 0;
        if(publicprivSwitch.isChecked()){
            publicpriv = 1;

        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        //String date = sdf.format(new Date(cv.getDate()));
        Intent intent = new Intent(this, tabView.class);
        radius = radius * 10;
        intent.putExtra("radius",radius);
        intent.putExtra("publicpriv",publicpriv);
        intent.putExtra("date",date);
        startActivity(intent);
    }


}