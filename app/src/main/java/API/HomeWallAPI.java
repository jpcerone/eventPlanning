package API;

import android.app.Activity;
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
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import softeng.eventplanning.tabView;

/**
 * Created by David on 10/27/2016.
 */

public class HomeWallAPI extends AsyncTask<String,String,String> {
    //private  String[] marray;
    private Activity activity;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    public void addToWall(Map<String, Object> map){
        //TODO get values from map[event[item]]
    }
    public void displayActivity(Activity a){activity = a;}
    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String("http://128.205.44.21:1032/get-event/1");
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

                Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
                Gson gson = new Gson();
                Map<String, Object> dict = gson.fromJson(result, mapType );
                System.out.println(dict);
                //I/System.out: {code=200.0, event={arrivalNot=no, date=10/6/2016, description=Come have some fun, bring some chips!, id=1, image=Test, listofPart=Sally Billy Mandy Bert Katie, location=980 Railroad Drive, name=Block Party, owner=Katie, time=3:30}, message=Event Retrieved}
                // dict should be a map of objects where map[1] is a map event{} with all the event items
                addToWall(dict);

                // below call causes infinite loop dont think i need it anyways
                //activity.startActivity(new Intent(activity,tabView.class));


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
