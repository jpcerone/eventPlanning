package API;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import softeng.eventplanning.tabView;


public class LogInAPI extends AsyncTask<String,String,String> {
    private  String[] marray;
    private Activity activity;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public  void setsomething(String[] array){
        marray = array;
    }

    public void signupActivity(Activity a){activity = a;}

    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String("http://10.0.2.2:5000/login");
        DataOutputStream printout;
        JSONObject jsonobj = new JSONObject();


        try{

            jsonobj.put("username",marray[0]);
            jsonobj.put("password",marray[1]);

            String urlparam = new String();
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
        System.out.println(result);
        try{
            JSONObject response = new JSONObject(result);
             if(response.getInt("code") == 200){
                 Log.d("SUP","true");
                 activity.startActivity(new Intent(activity,tabView.class));

             }
             else{
                 int duration = Toast.LENGTH_SHORT;
                 Context context = activity.getApplication();
                 Toast.makeText(context,response.getString("message"),duration).show();

                 Log.d("SUP","false");

             }

         }
        catch(Exception e){
           Log.d("fail", ""+ e);
        }
    }

}