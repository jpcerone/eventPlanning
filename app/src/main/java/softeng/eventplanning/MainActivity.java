package softeng.eventplanning;
import android.content.Intent;
import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Button;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.view.View;
import java.io.DataOutputStream;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.view.Window;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        getSupportActionBar().hide();

        /*
        setContentView(R.layout.create_user);
        final String[] create_user_info = new String[3];
        final EditText email = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText2);
        final EditText username_input = (EditText) findViewById(R.id.editText3);


        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                String username = username_input.getText().toString();
                create_user_info[0] = user_email;
                create_user_info[1] = username;
                create_user_info[2] = user_password;
                Log.d("myTag", Arrays.toString(create_user_info));

                CreateAPI asyncT = new CreateAPI();
                asyncT.setsomething(create_user_info);
                asyncT.execute();
            }
        });
        */
    }

    @Override
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

    public void eventClicked(View view){
        // TODO open event's page when clicked
    }

    public void settingsClicked(View view){
        // TODO open settings view when clicked


    }
    public void createEventClicked(MenuItem item){
        setContentView(R.layout.create_event);
    }

    public void logoutClicked(MenuItem item){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

    public void back(View v){
        setContentView(R.layout.create_event);

    }

    public void searchFilter(View view)
    {
        Intent intent = new Intent(this, sFilters.class);
        startActivity(intent);
    }

    public void createAccount(View v){
        setContentView(R.layout.create_user);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] create_user_info = new String[3];
                final EditText email = (EditText) findViewById(R.id.editText);
                final EditText password = (EditText) findViewById(R.id.editText2);
                final EditText username_input = (EditText) findViewById(R.id.editText3);

                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                String username = username_input.getText().toString();
                create_user_info[0] = user_email;
                create_user_info[1] = username;
                create_user_info[2] = user_password;
                Log.d("myTag", Arrays.toString(create_user_info));

                CreateAPI asyncT = new CreateAPI();
                asyncT.setsomething(create_user_info);
                asyncT.execute();
                setContentView(R.layout.signin_page);
            }

        });
        //Intent intent = new Intent(this, CreateNewUser.class);
        //startActivity(intent);
    }

    public void sendLogin(View v) {
        //public final static String EXTRA_MESSAGE = "softeng.eventplanning.MESSAGE";
        /*
        Intent intent = new Intent(this, sendLogin.class);
        EditText getUsername = (EditText) findViewById(R.id.edit_username);
        EditText getPassword = (EditText) findViewById(R.id.edit_password);
        String username = getUsername.getText().toString();
        String password = getPassword.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, username,password);
        intent.putExtra(username,password);
        startActivity(intent);
        */
        // TODO add try catch if user login was successful to call below
        setupTabsView(v);
    }

    public void setupTabsView(View v) {

        setContentView(R.layout.activity_main);
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

}


