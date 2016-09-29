package softeng.eventplanning;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CreateAPI extends AsyncTask<String,String,String> {
    private  String[] marray;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public  void setsomething(String[] array){
        marray = array;
    }

    @Override
    protected String doInBackground(String ... params) {
        String urlstring = new String("http://10.0.2.2:5000/");
        DataOutputStream printout;
        JSONObject jsonobj = new JSONObject();


        try{

            jsonobj.put("email",marray[0]);
            jsonobj.put("username",marray[1]);
            jsonobj.put("email",marray[2]);
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            printout = new DataOutputStream(connection.getOutputStream ());

            printout.writeBytes(URLEncoder.encode(jsonobj.toString(),"UTF-8"));
            printout.flush ();
            printout.close ();
            String printy;
            Log.d("completed", printy= "POSted");


        }
        catch (Exception e){
            Log.d("fail", e.toString());        }


        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        String okay;
        Log.d("completed", okay= "okay");
    }
}
