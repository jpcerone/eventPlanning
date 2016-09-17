package softeng.eventplanning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * Created by brandonboyle on 9/15/16.
 */
public class SamplePage extends AppCompatActivity {

    String[] mobileArray = {"Shoquayquay", "Toby", "Fernando", "BillNye"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_event);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.expandlelist, mobileArray);
        ListView listView = (ListView) findViewById(R.id.expandableListView);
        listView.setAdapter(adapter);

    }
}
