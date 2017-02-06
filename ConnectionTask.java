import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConnectionTask extends AsyncTask<String, Void, String> {

    private EditText nombre;
    private EditText apellido;
    private EditText telefono;

    private Context ctx;

    private JSONArray ja;

    public boolean focus;

    public ConnectionTask(EditText nombre, EditText apellido, EditText telefono, Context context){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.focus = false;
        this.ctx = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String mensaje ="";
        if(!focus){
            try {
                httpGetData(url);
                mensaje = "El dato ha sido enviado correctamente";

            } catch (Exception e) {
                mensaje = "Error en el envio de la informacion, verifique su conexion a internet y vuelva a intentarlo.";

            }
        }

        else{
            ja = null;
            try {
                String data;
                data = httpGetData(url);
                if (data.length() > 1)
                    ja = new JSONArray(data);


            } catch (JSONException e) {
                e.printStackTrace();
                mensaje = "Error recuperando la informacion del servidor, verifique su conexion a internet y vuelva a intentarlo.";

            }

        }
        return mensaje;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != ""){
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }

        if(focus){
            try {
                nombre.setText(ja.getString(1));
                apellido.setText(ja.getString(2));
                telefono.setText(ja.getString(3));
            } catch (Exception e) {

                nombre.setText("");
                apellido.setText("");
                telefono.setText("");
            }
        }
    }

    public String httpGetData(String mURL){
        String data ="";
        HttpURLConnection aHttpURLConnection = null;
        BufferedReader reader = null;
        try {
            URL aURL = new URL(mURL);
            try {
                aHttpURLConnection = (HttpURLConnection) aURL.openConnection();
                aHttpURLConnection.connect();
                InputStream aInputStream = aHttpURLConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(aInputStream));

                StringBuffer buffer = new StringBuffer();

                String line ="";

                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                data = buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally{
            if(aHttpURLConnection != null){
                aHttpURLConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return data;

    }


}