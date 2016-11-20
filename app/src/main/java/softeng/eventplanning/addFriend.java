package softeng.eventplanning;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import API.AddFriendAPI;

public class addFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend);

    }
    public void addFriendUsernameButtonClicked(View v){
        ArrayList<String> fList = LoggedInUser.friendsList;
        EditText friendUserName = (EditText) findViewById(R.id.newFriendUsername);
        if(!fList.contains(friendUserName.getText().toString())) {
            AddFriendAPI friendAPI = new AddFriendAPI();
            friendAPI.signupActivity(this);

            System.out.println(friendUserName.getText().toString());
            System.out.println("LoggedInUser List:" + LoggedInUser.friendListRaw);
            friendAPI.setNewName(friendUserName.getText().toString());
            if (LoggedInUser.friendListRaw.equals("")) {
                friendAPI.setFriendList(friendUserName.getText().toString());
            } else {
                friendAPI.setFriendList(LoggedInUser.friendListRaw + " " + friendUserName.getText().toString());
            }
            friendAPI.execute();
        }
        else{
            int duration = Toast.LENGTH_SHORT;
            Log.d("SUP","false");
            Context context = this.getApplication();
            Toast.makeText(context,"This Friend is Already in your Friends List",duration).show();
        }

    }
}
