package softeng.eventplanning;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.util.Arrays;
import android.text.TextUtils;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
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
        final EditText re_password = (EditText) findViewById(R.id.passwordConf);

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
                    String re_enter = re_password.getText().toString();
                if (checkpassword(user_password, re_enter) && (isValidEmail(user_email))) {
                    create_user_info[0] = user_email;
                    create_user_info[1] = username;
                    create_user_info[2] = user_password;
                    Log.d("myTag", Arrays.toString(create_user_info));
                    asyncT.execute();
                }
                else{

                    Context context = getApplicationContext();
                    CharSequence password_error = "Passwords Do Not Match";
                    CharSequence email_error = "Not Valid Email";
                    int duration = Toast.LENGTH_SHORT;
                    if (checkpassword(user_password, re_enter)){
                        Toast.makeText(context, email_error, duration).show();
                    }
                    else{
                        Toast.makeText(context, password_error, duration).show();

                    }
                }
            }


        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean checkpassword(String password, String password_two){
        if (password.equals(password_two)) {
            return true;
        }
        else {
            return false;
        }
    }



}