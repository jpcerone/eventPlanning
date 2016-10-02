
package softeng.eventplanning;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLConnection;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.TextView;
/**
 * Created by David on 9/29/2016.
 */
public class sendLogin extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedState){
        super.onCreate(savedState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    String response = new String();
                    response = createRequest();
                    Log.d("success", response);
                    TextView view = (TextView) findViewById(R.id.textView11);
                    view.setText(response);

                }
                catch(Exception e){
                    Log.d("fail", e.toString());
                }
            }
        }).start();

        setContentView(R.layout.activity_main);
    }

    private String createRequest() throws Exception{
        URL  url = new URL("http://10.0.2.2:5000/login");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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
}