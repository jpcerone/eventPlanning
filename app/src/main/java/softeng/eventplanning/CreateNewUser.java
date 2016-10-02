package softeng.eventplanning;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.URL;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
/**
 * Created by brandonboyle on 9/22/16.
 */
public class CreateNewUser {


/*
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view = inflater.inflate(R.layout.create_user, container, false);
        return view;

        setContentView(R.layout.create_user);
        final String[] create_user_info = new String[3];
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText username_input = (EditText) findViewById(R.id.username_input);
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String response = new String();
                            response = createRequest();
                            Log.d("success", response);
                        }
                        catch(Exception e){
                            Log.d("fail", e.toString());
                        }
                    }
                }).start();

                setContentView(R.layout.create_user);

            }
            private String createRequest() throws Exception{
                URL url = new URL("http://10.0.2.2:5000/login");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                StringBuilder out = new StringBuilder();
                String line = new String();

                while((line = reader.readLine()) != null){
                    out.append(line);
                }


                reader.close();

                return out.toString();
            }
        }


        });

    }





*/


}