package API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

import softeng.eventplanning.userPage;


public class UserAPI extends AsyncTask<String,String,String> {
    private String username;
    private  Map<String,Object> userMap;
    private userPage activity;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public  void setUsername(String iusername){username = iusername;}

    public void userPageActivity(userPage a){activity = a;}

    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String(API.serverIP+"/get-user/"+username);
        DataOutputStream printout;
        JSONObject jsonobj = new JSONObject();


        try{



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
                userMap = gson.fromJson(response.getString("user"),mapType);
                activity.updateUserPage(userMap);

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
