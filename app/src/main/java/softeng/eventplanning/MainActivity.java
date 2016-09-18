package softeng.eventplanning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TabHost;

import android.view.Window;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TabHost tab = (TabHost) findViewById(R.id.mainTabs);
        tab.setup();
        TabHost.TabSpec spec1 = tab.newTabSpec("TAB 1");
        spec1.setIndicator("TAB 1");
        spec1.setContent(R.id.layout1);
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("TAB 2");
        spec2.setIndicator("TAB 2");
        spec2.setContent(R.id.layout2);
        tab.addTab(spec2);

        TabHost.TabSpec spec3 = tab.newTabSpec("TAB 3");
        spec3.setIndicator("TAB 3");
        spec3.setContent(R.id.layout3);
        tab.addTab(spec3);


    }


}
