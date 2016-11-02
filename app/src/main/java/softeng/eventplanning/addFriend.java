package softeng.eventplanning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

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
        System.out.println("Worked!");
        AddFriendAPI friendAPI = new AddFriendAPI();
        friendAPI.signupActivity(this);
        EditText friendUserName = (EditText) findViewById(R.id.newFriendUsername);
        System.out.println(friendUserName.getText().toString());
        System.out.println("LoggedInUser List:"+LoggedInUser.friendListRaw);
        friendAPI.setNewName(friendUserName.getText().toString());
        if(LoggedInUser.friendListRaw.equals("")){
            friendAPI.setFriendList(friendUserName.getText().toString());
        }
        else {
            friendAPI.setFriendList(LoggedInUser.friendListRaw + " " + friendUserName.getText().toString());
        }
        friendAPI.execute();

    }
}
