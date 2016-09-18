package softeng.eventplanning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brandonboyle on 9/15/16.
 */
public class SamplePage extends AppCompatActivity {

    //HashMap<String, List<String>> Friends;
    //List<String> Friend_list;
    //ExpandableListView exp_list;
    //friendadapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_event);
        //exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        //Friends = ListData.getInfo();
        //Friend_list = new ArrayList<String>(Friends.keySet());
        //adapter = new friendadapter(this, Friends, Friend_list);
        //exp_list.setAdapter(adapter);

    }
}
