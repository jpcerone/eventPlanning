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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import API.EventAPI;

/**
 * Created by brandonboyle on 9/15/16.
 */
public class SamplePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_event);
        if(extras!=null){
            EventAPI ep = new EventAPI();
            ep.setsomething((int)extras.get("id"));
            ep.eventActivity(this);
            ep.execute();
        }


    }
    public void updateEvent(Map<String,Object> eventInfo){
        String[] friends = {"Test"};
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
}
