package softeng.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jpcerone on 10/3/16.
 */

public class createEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

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

}