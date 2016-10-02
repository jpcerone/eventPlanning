package softeng.eventplanning;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;


public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
    }

    public void createAccount(View v){
        Intent intent = new Intent(this, CreateNewUser.class);
        startActivity(intent);


}