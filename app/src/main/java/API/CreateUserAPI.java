package API;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CreateUserAPI extends AsyncTask<String,String,String> {
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
        String urlstring = new String("http://10.0.2.2:5000/create-user");
        DataOutputStream printout;
        JSONObject jsonobj = new JSONObject();


        try{

            jsonobj.put("email",marray[0]);
            jsonobj.put("username",marray[1]);
            jsonobj.put("password",marray[2]);

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
            String line = new String();

            while((line = reader.readLine()) != null){
                out.append(line);
            }

            System.out.println(out.toString());
            reader.close();



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
