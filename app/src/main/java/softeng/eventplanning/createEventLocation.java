package softeng.eventplanning;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.InputStream;
import java.net.URL;

import API.MapCoordAPI;

/**
 * Created by jpcerone on 10/3/16.
 */

public class createEventLocation extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_location);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void back(View v) {
        Intent createEventChange = new Intent(this, createEvent.class);
        startActivity(createEventChange);
    }

    //Handles setting the url associated with the location selected
    public void locationSetCoord(View v) {
        //TODO: Set actual event location, using generic for now
        double lat = 43.000881;
        double lng = -78.789139;

        MapCoordAPI setMap = new MapCoordAPI();
        setMap.setImageView((ImageView)findViewById(R.id.imageView2));
        setMap.setLat(lat);
        setMap.setLong(lng);
        setMap.setSize("400x300");
        setMap.execute();
    }
}
