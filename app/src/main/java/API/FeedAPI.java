package API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import softeng.eventplanning.tabView;

/**
 * Created by David on 10/27/2016.
 */

public class FeedAPI extends AsyncTask<String,String,String> {
    ArrayList event_titles = new ArrayList();
    ArrayList event_pics = new ArrayList();
    ArrayList event_descs = new ArrayList();
    private Map<String, Object> feedMap;
    private tabView activity;

    public void tabviewActivity(tabView a){activity = a;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String(API.serverIP+"/get-event/1");
        DataOutputStream printout;

        try{
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent","Mozilla/5.0");
            connection.setRequestProperty("Accept-Language","en-US,en;q=0.5");
            connection.setDoInput(true);

            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder out = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                out.append(line);
            }

            reader.close();
            return out.toString();
        }
        catch (Exception e){
            Log.d("fail", e.toString());        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("what's this"+result);
        try{
            JSONObject response = new JSONObject(result);
            if(response.getInt("code") == 200){
                Log.d("SUP","true");
                Log.d("myTag", response.toString());

                Type mapType = new TypeToken<HashMap<String, Object>>(){}.getType();
                Gson gson = new Gson();
                feedMap = gson.fromJson(response.getString("event"), mapType);
                System.out.println("API "+ feedMap);
                activity.setFeedArrays(feedMap);
            }
            else{
                int duration = Toast.LENGTH_SHORT;
                Context context = activity.getApplication();
                Toast.makeText(context,response.getString("message"),duration).show();

                Log.d("SUP","false");
            }
        }
        catch(Exception e){
            Log.d("fail", e.toString());
        }
    }
}
