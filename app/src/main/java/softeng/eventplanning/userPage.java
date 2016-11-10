package softeng.eventplanning;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import API.SettingsAPI;
import API.UserAPI;


public class userPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle b = getIntent().getExtras();
        String username = "";
        if(b != null){
            username = (String) b.get("Username");
            UserAPI user = new UserAPI();
            user.setUsername(username);
            user.userPageActivity(this);
            user.execute();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
    }
    public void updateUserPage(Map<String,Object> info){
        System.out.println("updateUserPage Called");
        TextView editTextPlace;
        String[] friends;
        editTextPlace = (TextView) findViewById(R.id.userBday);
        editTextPlace.setText((String)info.get("dob"));
        editTextPlace = (TextView) findViewById(R.id.userFname);
        editTextPlace.setText((String)info.get("fName"));
        editTextPlace = (TextView) findViewById(R.id.userLname);
        editTextPlace.setText((String)info.get("lName"));
        editTextPlace = (TextView) findViewById(R.id.userBio);
        editTextPlace.setText((String)info.get("bio"));
        editTextPlace = (TextView) findViewById(R.id.userPhone);
        editTextPlace.setText((String)info.get("phone"));
        editTextPlace = (TextView) findViewById(R.id.userUsername);
        editTextPlace.setText((String)info.get("username"));
        String list = (String) info.get("friendsList");
        friends = list.split("\\s+");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.user_friend_list, friends);
        ListView listView = (ListView) findViewById(R.id.userFriendList);
        listView.setAdapter(adapter);
        if(!(LoggedInUser.username.equals(info.get("username").toString()))){
                Button editButton = (Button) findViewById(R.id.editButton);
                editButton.setVisibility(View.GONE);

        }
    }
    public void getUserPage(View view){
        TextView temp = (TextView) view;
        UserAPI user = new UserAPI();
        user.setUsername((String)temp.getText());
        user.userPageActivity(this);
        user.execute();
        //Intent intent = new Intent(this,userPage.class);
        //startActivity(intent);

    }
    public void editButtonClicked(View view){
        Intent settings = new Intent(this, SettingsPage.class);
        startActivity(settings);

    }



}
