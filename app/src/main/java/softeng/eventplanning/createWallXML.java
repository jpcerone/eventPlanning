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
