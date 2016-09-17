package softeng.eventplanning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }
}
