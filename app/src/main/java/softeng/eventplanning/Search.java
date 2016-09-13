package softeng.eventplanning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Search extends AppCompatActivity {

    // Array of strings...
    String[] mobileArray = {"Event 1            Distance:","Event 2            Distance:","Event 3            Distance:","Event 4            Distance:","Event 5            Distance:","Event 6            Distance:","Event 7            Distance:","Event 8            Distance:"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.searchresultslist, mobileArray);
        ListView listView = (ListView) findViewById(R.id.searchresults);
        listView.setAdapter(adapter);
    }
    public void searchFilter(View view)
    {
        Intent intent = new Intent(this, sFilters.class);
        startActivity(intent);
    }
}
