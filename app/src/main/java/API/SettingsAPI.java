package API;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
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

import softeng.eventplanning.LoggedInUser;
import softeng.eventplanning.tabView;


public class SettingsAPI extends AsyncTask<String,String,String> {
    private  String[] accountSettings;
    private String userImage;
    private Activity activity;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }




    public void signupActivity(Activity a){activity = a;}

    public void accountArray(String[] s){accountSettings = s;}

    public void setImage(String s){userImage=s;}

    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String(API.serverIP+"/account-update/"+LoggedInUser.userid);

        DataOutputStream printout;
        JSONObject jsonobj = new JSONObject();


        try{
            jsonobj.put("username",accountSettings[0]);
            jsonobj.put("password",accountSettings[1]);
            jsonobj.put("dob",accountSettings[2]);
            jsonobj.put("phone",accountSettings[3]);
            jsonobj.put("fName",accountSettings[4]);
            jsonobj.put("lName",accountSettings[5]);
            jsonobj.put("bio",accountSettings[6]);
            jsonobj.put("pass",accountSettings[7]);
            jsonobj.put("userPic",userImage);

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
                LoggedInUser.setUsername(accountSettings[0]);
                LoggedInUser.setBirthday(accountSettings[2]);
                LoggedInUser.setPhone(accountSettings[3]);
                LoggedInUser.setfName(accountSettings[4]);
                LoggedInUser.setlName(accountSettings[5]);
                LoggedInUser.setBio(accountSettings[6]);
                byte[] imgByte = Base64.decode(userImage,Base64.DEFAULT);
                Bitmap img = BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);
                LoggedInUser.setEncoded(userImage);
                LoggedInUser.setImage(img);
                activity.startActivity(new Intent(activity,tabView.class));

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