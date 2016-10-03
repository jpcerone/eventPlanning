package softeng.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jpcerone on 10/3/16.
 */

public class createEventTime  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_time);
    }
    public void back(View v){
        Intent createEventChange = new Intent(this,createEvent.class);
        startActivity(createEventChange);
    }

}
