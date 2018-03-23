package mx.itesm.csf.parseojson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class ParseaArregloJSON extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsea_arreglo_json);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentoContenedor);

        if (fragment == null) {
            fragment = new ListaAutos();

            fm.beginTransaction()
                    .add(R.id.fragmentoContenedor, fragment)
                    .commit();
        }

    }
}
