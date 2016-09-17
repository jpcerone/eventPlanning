package softeng.eventplanning;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_event);
        Intent intent = new Intent(this, SamplePage.class);
        startActivity(intent);
    }
}
