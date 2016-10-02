package softeng.eventplanning;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        //getSupportActionBar();
        //getSupportActionBar().hide();
    }

    /*********** Add this code when merged with Brandon ***********
     public void createAccount(View v){
     Intent intent = new Intent(this, CreateNewUser.class);
     startActivity(intent);
     }
     */
}