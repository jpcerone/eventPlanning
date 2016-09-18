package softeng.eventplanning;
import android.content.Intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;

import android.view.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    HashMap<String, List<String>> Friends;
    List<String> Friend_list;
    ExpandableListView exp_list;
    friendadapter adapter2;
    String[] mobileArray = {"Event 1            Distance:","Event 2            Distance:","Event 3            Distance:","Event 4            Distance:","Event 5            Distance:","Event 6            Distance:","Event 7            Distance:","Event 8            Distance:"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TabHost tab = (TabHost) findViewById(R.id.mainTabs);
        tab.setup();
        TabHost.TabSpec spec1 = tab.newTabSpec("Current Event");
        spec1.setIndicator("Current Event");
        spec1.setContent(R.id.layout1);
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("TAB 2");
        spec2.setIndicator("TAB 2");
        spec2.setContent(R.id.layout2);
        tab.addTab(spec2);

        TabHost.TabSpec spec3 = tab.newTabSpec("Search");
        spec3.setIndicator("Search");
        spec3.setContent(R.id.layout3);
        tab.addTab(spec3);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.searchresultslist, mobileArray);
        ListView listView = (ListView) findViewById(R.id.searchresults);
        listView.setAdapter(adapter);

        exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Friends = ListData.getInfo();
        Friend_list = new ArrayList<String>(Friends.keySet());

        adapter2 = new friendadapter(this, Friends, Friend_list);
        exp_list.setAdapter(adapter2);


    }
    public void searchFilter(View view)
    {
        Intent intent = new Intent(this, sFilters.class);
        startActivity(intent);
    }


}

