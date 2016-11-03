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

//import softeng.eventplanning.WallActivity;
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

    //public void signupActivity(tabView a){activity = a;}
    public void tabviewActivity(tabView a){activity = a;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
/*
    public ArrayList getEvent_titles(){
        System.out.println(event_titles);
        return event_titles;
    }
    public ArrayList getEvent_pics(){
        System.out.println(event_pics);
        return event_pics;
    }
    public ArrayList getEvent_descs(){
        return event_descs;
    }

    public void setArrays(Map<String, Object> diction){
        //HashMap map = new HashMap<>();
        //HashMap map = (HashMap) diction.get("event");
        //ArrayList event_titles = new ArrayList();
        System.out.println(diction);
        diction = (Map<String, Object>) diction.get("event");
        System.out.println(diction);
        event_titles.add(diction.get("name"));
        System.out.println(event_titles+" API");
        event_pics.add(diction.get("image"));
        event_descs.add(diction.get("description"));
    }
*/


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
                //I/System.out: {code=200.0, event={arrivalNot=no, date=10/6/2016, description=Come have some fun, bring some chips!, id=1, image=Test, listofPart=Sally Billy Mandy Bert Katie, location=980 Railroad Drive, name=Block Party, owner=Katie, time=3:30}, message=Event Retrieved}
                // dict should be a map of objects where map[1] is a map event{} with all the event items
                //WallActivity wa = new WallActivity()
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
