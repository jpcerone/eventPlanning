package softeng.eventplanning;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

import API.CreateUserAPI;
import API.HomeWallAPI;
import API.LogInAPI;

/**
 * Created by David on 10/27/2016.
 */
public class wallItem{
    private String event_title;
    private int event_pic_id;
    private String event_desc;

    public wallItem(String event_title, int event_pic_id, String event_desc){
        this.event_title = event_title;
        this.event_pic_id = event_pic_id;
        this.event_desc = event_desc;
    }

    public String getEvent_title(){
        return event_title;
    }

    public int getEvent_pic_id(){
        return event_pic_id;
    }

    public String getEvent_desc(){
        return event_desc;
    }

    public void setEvent_title(String event_title){
        this.event_title = event_title;
    }

    public void setEvent_pic_id(int event_pic_id){
        this.event_pic_id = event_pic_id;
    }

    public void setEvent_desc(String event_desc){
        this.event_desc = event_desc;
    }
}
/*
public class createWallXML extends AppCompatActivity {

    public void onCreate(Bundle savedState) {

        super.onCreate(savedState);
        setContentView(R.layout.wall_page);
        //final String[] create_user_info = new String[3];
        //final EditText email = (EditText) findViewById(R.id.email);
        //final EditText password = (EditText) findViewById(R.id.password);
        //final EditText username_input = (EditText) findViewById(R.id.username);
        //final EditText re_password = (EditText) findViewById(R.id.passwordConf);


        final HomeWallAPI asyncT = createAsyncTask();
        //asyncT.setArray(create_user_info);
        if (asyncT.getStatus() != AsyncTask.Status.RUNNING) {
            //Log.d("CALLED", "yup");
            //String user_password = password.getText().toString();
            //String username = username_input.getText().toString();
            //create_user_info[0] = username;
            //create_user_info[1] = user_password;
            Log.d("myTag", "createWall called");
            asyncT.execute();
        }
    }

    private HomeWallAPI createAsyncTask(){
        HomeWallAPI api = new HomeWallAPI();
        api.displayActivity(this);
        return api;
    }

}
*/