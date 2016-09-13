package softeng.eventplanning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
    }

    public void setLocation(View v){
        setContentView(R.layout.create_event_location);
    }

    public void setDate(View v){
        setContentView(R.layout.create_event_date);
    }

    public void setTime(View v){
        setContentView(R.layout.create_event_time);
    }

    public void done(View v){
        setContentView(R.layout.create_event);
    }
}
