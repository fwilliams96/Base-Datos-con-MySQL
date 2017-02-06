import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                String url = "http://10.0.2.2/prueba/registrarUsuario.php?cc=" + cc.getText() +
                        "&nombre=" + nombre.getText() + "&apellido=" + apellido.getText() +
                        "&telefono=" + telefono.getText();

                ConnectionTask connectionTask = new ConnectionTask(nombre,apellido,telefono,getApplicationContext());
                connectionTask.focus = false;
                connectionTask.execute(url);

            }
        });

        cc.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus == false) {
                    String url = "http://10.0.2.2/prueba/consultarUsuario.php?cc=" + cc.getText();

                    ConnectionTask connectionTask = new ConnectionTask(nombre, apellido, telefono, getApplicationContext());
                    connectionTask.focus = true;
                    connectionTask.execute(url);

                }

            }
        });


    }



}