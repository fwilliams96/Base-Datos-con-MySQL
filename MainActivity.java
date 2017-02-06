import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1=(Button)findViewById(R.id.enviar);
        final EditText cc=(EditText)findViewById(R.id.cedula);
        final EditText nombre=(EditText)findViewById(R.id.nombre);
        final EditText apellido=(EditText)findViewById(R.id.apellidos);
        final EditText telefono=(EditText)findViewById(R.id.telefono);

        b1.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    httpGetData("http://127.0.0.1/prueba/registrarUsuario.php?cc=" + cc.getText() +
                            "&nombre=" + nombre.getText() + "&apellido=" + apellido.getText() +
                            "&telefono=" + telefono.getText());
                    Toast.makeText(getApplicationContext(), "El dato ha sido enviado correctamente", 1000).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error en el envio de la informacion, verifique su conexion a internet y vuelva a intentarlo.", 1000).show();

                }

            }
        });

        cc.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus == false) {
                    JSONArray ja = null;
                    try {
                        String data;
                        data = httpGetData("http://10.0.2.2/prueba/consultarUsuario.php?cc=" + cc.getText());
                        if (data.length() > 1)
                            ja = new JSONArray(data);


                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error recuperando la informacion del servidor, verifique su conexion a internet y vuelva a intentarlo.", 1000).show();

                    }
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
        });


    }

    public String httpGetData(String mURL) {
        String response="";
        mURL=mURL.replace(" ", "%20");
        Log.i("LocAndroid Response HTTP Threas", "Ejecutando get 0: " + mURL);
        HttpClient httpclient = new DefaultHttpClient();

        Log.i("LocAndroid Response HTTP Thread","Ejecutando get 1");
        HttpGet httppost = new HttpGet(mURL);
        Log.i("LocAndroid Response HTTP Thread","Ejecutando get 2");
        try {


            Log.i("LocAndroid Response HTTP","Ejecutando get");
            // Execute HTTP Post Request
            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            response = httpclient.execute(httppost,responseHandler);
            Log.i("LocAndroid Response HTTP",response);
        } catch (ClientProtocolException e) {
            Log.i("LocAndroid Response HTTP ERROR 1",e.getMessage());
            // TODO Auto-generated catch block
        } catch (IOException e) {

            Log.i("LocAndroid Response HTTP ERROR 2",e.getMessage());
            // TODO Auto-generated catch block
        }
        return response;

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/


}