package softeng.eventplanning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import API.FeedAPI;
import API.EventAPI;
import API.UserAPI;

/**
 * Created by jpcerone on 10/3/16.
 */

public class tabView extends MainActivity {
    String[] friends = {"Test"};
    String[] mobileArray = {"Event 1            Distance:","Event 2            Distance:","Event 3            Distance:","Event 4            Distance:","Event 5            Distance:","Event 6            Distance:","Event 7            Distance:","Event 8            Distance:"};
    ArrayList event_titles = new ArrayList();
    ArrayList event_pics = new ArrayList();
    ArrayList event_descs = new ArrayList();
    ArrayList wallItems = new ArrayList<WallItem>();
    //public ListView listView;


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

        EventAPI event = new EventAPI();
        event.tabviewActivity(this);
        event.setsomething(1);//NEED TO CHANGE!!
        event.execute();

        FeedAPI feed = new FeedAPI();
        feed.tabviewActivity(this);
        feed.execute();


    }
    public void searchFilter(View view)
    {
        Intent intent = new Intent(this, sFilters.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
    }

    public void updateEvent(Map<String,Object> eventInfo){

        TextView name = (TextView) findViewById(R.id.eventName);
        name.setText("" + eventInfo.get("name"));
        TextView owner = (TextView) findViewById(R.id.eventOwner);
        owner.setText("" + eventInfo.get("owner"));
        TextView date = (TextView) findViewById(R.id.eventDate);
        date.setText("" + eventInfo.get("date"));
        TextView time = (TextView) findViewById(R.id.eventTime);
        time.setText("" + eventInfo.get("time"));
        TextView descr = (TextView) findViewById(R.id.eventDescr);
        descr.setText("" + eventInfo.get("description"));
        String list = (String) eventInfo.get("listofPart");
        friends = list.split("\\s+");
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.event_friend_list, friends);
        ListView listView2 = (ListView) findViewById(R.id.eventFriendsList);
        listView2.setAdapter(adapter2);

    }
    public void friendClicked(View view){
        TextView temp = (TextView) view;
        Intent intent = new Intent(this,userPage.class);
        intent.putExtra("Username",temp.getText().toString());
        startActivity(intent);


    }


    public void setFeedArrays(Map<String,Object> eventInfo){

        System.out.println("1 "+eventInfo);
        event_titles.add(eventInfo.get("name"));
        System.out.println(event_titles+" setFeedArray");
        event_pics.add(eventInfo.get("image"));
        System.out.println(event_pics+" setFeedArray");
        event_descs.add(eventInfo.get("description"));
        System.out.println(event_descs+" setFeedArray");

        for (int i = 0; i < event_titles.size(); i++) {
            WallItem item = new WallItem(event_titles.get(i), event_pics.get(i),
                    event_descs.get(i));
            System.out.println("Wall Item title: "+item.getEvent_title());
            wallItems.add(item);
        }


        ListView listView = (ListView) findViewById(R.id.feed_listView);
        WallAdapter wAdapter = new WallAdapter(this, wallItems);
        //WallAdapter wAdapter = new WallAdapter(getApplicationContext(), R.layout.feed_list, wallItems);
        listView.setAdapter(wAdapter);
        //TODO clickng the wall item takes you to event page.
        //listView.setOnClickListener(this);
    }
}
