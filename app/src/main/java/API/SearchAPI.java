package API;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import softeng.eventplanning.tabView;


public class SearchAPI extends AsyncTask<String,String,String> {
    private String[] marray;
    private String title;
    private double dist;
    private String timeTo;
    private String timeFrom;
    private int publicpriv;
    private  Map<String,Object> eventMap;
    private tabView activity;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public  void setTitle(String s){
        title = s;
    }
    public  void setDist(double s){
        dist = s;
    }
    public  void setTimeTo(String s){
        timeTo = s;
    }
    public  void setTimeFrom(String s){
        timeFrom = s;
    }
    public  void setPublicpriv(int s){
        publicpriv = s;
    }

    public void currentActivity(tabView a){activity = a;}

    public Map<String,Object> getEventData(){
        return eventMap;
    }

    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String(API.serverIP+"/search");
        DataOutputStream printout;
        JSONObject jsonobj = new JSONObject();


        try{
            jsonobj.put("dist",dist/10);
            jsonobj.put("time_to",timeTo);
            jsonobj.put("time_frm",timeFrom);
            jsonobj.put("public",publicpriv);
            jsonobj.put("title",title);
            jsonobj.put("lat",70.1);
            jsonobj.put("long",60.0);




            String urlparam;
            urlparam = jsonobj.toString();

            System.out.println(urlparam);

            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent","Mozilla/5.0");
            connection.setRequestProperty("Accept-Language","en-US,en;q=0.5");
            connection.setDoInput(true);

            printout = new DataOutputStream(connection.getOutputStream());

            printout.writeBytes(urlparam);
            printout.flush ();
            printout.close ();

            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder out = new StringBuilder();
            String line = new String();

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
        System.out.println(result);
        try{
            JSONObject response = new JSONObject(result);

            if(response.getInt("code") == 200){
                Type mapType = new TypeToken<Map<String,Object>>(){}.getType();
                Gson gson = new Gson();
                JSONArray data = response.getJSONArray("data");
                //eventMap = gson.fromJson(response.getString("data"),mapType);
                activity.updateSearch(data);

            }
            else{
                int duration = Toast.LENGTH_SHORT;
                Log.d("SUP","false");
                Context context = activity.getApplication();
                Toast.makeText(context,response.getString("message"),duration).show();

            }

        }
        catch(Exception e){
            Log.d("fail","Failed to get JSON Object");
        }
    }

}

