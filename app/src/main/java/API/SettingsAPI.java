package API;

import android.os.AsyncTask;

/**
 * Created by josh on 11/2/16.
 */
public class SettingsAPI extends AsyncTask<String,String,String> {

     @Override
     protected void onPreExecute() {
        super.onPreExecute();
    }

     @Override
     public String doInBackground(String ... params){
        return "hello";

     }

     @Override
     public void onPostExecute(String result){

     }
}
