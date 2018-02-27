package mx.itesm.csf.almacenamientosqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        AdaptadorDB adaptador = new AdaptadorDB(this);

        /*
        //--- Angregamos clientes ---
        adaptador.open();
        long id = adaptador.insertaClientes("Raúl Morales Salcedo", "raulms@itesm.mx");
        id = adaptador.insertaClientes("Felipe Calderón Hinojosa", "calderon@presidencia.com");
        adaptador.close();
        */

        /*
        //-- Obtenemos todos los clientes ---
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst())
        {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }
        adaptador.close();
        */

        /*
        //--- Obtenemos un cliente ---
        adaptador.open();
        Cursor c = adaptador.obtieneUnCliente(2);
        if (c.moveToFirst())
            DespliegaCliente(c);
        else
            Toast.makeText(this, "No se ha encontrado al cliente", Toast.LENGTH_LONG).show();
        adaptador.close();
        */

        /*
        //--- Actualiza un cliente ---
        adaptador.open();
        if (adaptador.actualizaCliente(2, "Andrés Manuelovich", "elpeje@presidencia.com"))
            Toast.makeText(this, "Actualización exitosa!.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Falló la actualización del registro.", Toast.LENGTH_LONG).show();
        adaptador.close();
        */


        //--- Borra un cliente ---
        adaptador.open();
        if (adaptador.borraCliente(1))
            Toast.makeText(this, "Registro eliminado exitosamente.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Falló al eliminar registro.", Toast.LENGTH_LONG).show();
        adaptador.close();


        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/basesdedatos";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();

                //---copy the db from the assets folder into
                // the databases folder---
                CopyDB(getBaseContext().getAssets().open("CRM"),
                        new FileOutputStream(destPath + "/CRM"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //--- Obtenemos todos los clientes ---
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst())
        {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }
        adaptador.close();
    }

    public void CopyDB(InputStream inputStream,
                       OutputStream outputStream) throws IOException {
        //--- Copiamos 1K bytes poco a poco ---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void DespliegaCliente(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Nombre: " + c.getString(1) + "\n" +
                        "Email:  " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }
}