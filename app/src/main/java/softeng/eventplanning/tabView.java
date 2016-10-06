package softeng.eventplanning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jpcerone on 10/3/16.
 */

public class tabView extends AppCompatActivity {
    String[] mobileArray = {"Event 1            Distance:","Event 2            Distance:","Event 3            Distance:","Event 4            Distance:","Event 5            Distance:","Event 6            Distance:","Event 7            Distance:","Event 8            Distance:"};
    HashMap<String, List<String>> Friends;
    List<String> Friend_list;
    ExpandableListView exp_list;
    friendadapter adapter2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabed_view);
        getSupportActionBar().show();

        TabHost tab = (TabHost) findViewById(R.id.mainTabs);
        tab.setup();

        TabHost.TabSpec spec2 = tab.newTabSpec("Home");
        spec2.setIndicator("Home");
        spec2.setContent(R.id.layout2);
        tab.addTab(spec2);

        TabHost.TabSpec spec1 = tab.newTabSpec("Current Event");
        spec1.setIndicator("Current Event");
        spec1.setContent(R.id.layout1);
        tab.addTab(spec1);

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
    @Override
    public void onBackPressed() {
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.settings_notifications:
                // TODO go to notifications page
                Toast.makeText(getApplicationContext(),
                        "Settings Updated",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_themes:
                // TODO go to theme page
                Toast.makeText(getApplicationContext(),
                        "Settings Updated",
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/

}
