package API;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import softeng.eventplanning.R;

/**
 * Created by josh on 10/27/16.
 */
public class MapCoordAPI extends AsyncTask<String,String,String> {

    ImageView _imageView;
    Drawable _map;
    double _lat;
    double _long;
    String _size;

    public void setImageView(ImageView v){_imageView = v;}

    public void setLat(double lat){_lat = lat;};

    public void setLong(double lng){_long=lng;}

    //Size string should be of the format width,length
    //eg. "400x300"
    public void setSize(String size){_size=size;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String ... params){
        String url = "https://maps.googleapis.com/maps/api/staticmap?center=" + _lat + "," + _long + "&zoom=15&size="+_size+"&markers=color:blue%7Clabel:A%7C" + _lat + "," + _long + "&key=AIzaSyDJ24XZMQKWYKRnQS_Lr4CRVkoybZSk2Rw";
        _map = loadImg(url);

        return "Ok";

    }

    @Override
    protected void onPostExecute(String result) {
        try{
            _imageView.setImageDrawable(_map);
        }
        catch(Exception e){
            Log.d("E",e.toString());
        }

    }

    public static Drawable loadImg(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;

        } catch (Exception e) {
            Log.d("E", e.toString());
            return null;
        }
    }

}


