package softeng.eventplanning;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.util.Arrays;

import android.os.Bundle;

import API.CreateUserAPI;

/**
 * Created by brandonboyle on 9/22/16.
 */
public class CreateNewUser extends AppCompatActivity{

    public void onCreate(Bundle savedState) {

        super.onCreate(savedState);
        setContentView(R.layout.create_user);
        final String[] create_user_info = new String[3];
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText username_input = (EditText) findViewById(R.id.username);

        Button b = (Button) findViewById(R.id.button);
        final CreateUserAPI asyncT = new CreateUserAPI();
        asyncT.setsomething(create_user_info);
        asyncT.signupActivity(this);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("CALLED", "yup");
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                String username = username_input.getText().toString();
                create_user_info[0] = user_email;
                create_user_info[1] = username;
                create_user_info[2] = user_password;
                Log.d("myTag", Arrays.toString(create_user_info));
                asyncT.execute();

            }


        });
    }

}