package mx.itesm.csf.parseojson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;



public class ParseaObjetoArreglo extends AppCompatActivity {

    private static final String TAG = "ParseaObjetoJSON";

    // definimos elementos de la app
    private Auto miAuto;
    private TextView TextViewMarca;
    private TextView TextViewAuto;
    private ImageView ImageViewUrl;
    private String imagen;

    private final String OBJETO_JSON_EXTRA = "objetoAuto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsea_objeto_arreglo);

        miAuto = (Auto) getIntent().getSerializableExtra(OBJETO_JSON_EXTRA);

        // mapeamos componentes de la interfaz
        TextViewMarca = (TextView) findViewById(R.id.edit_text_marca);
        TextViewAuto = (TextView) findViewById(R.id.edit_text_auto);
        ImageViewUrl = (ImageView) findViewById(R.id.image_view_auto);

        final ProgressDialog barraDeProgreso = new ProgressDialog(ParseaObjetoArreglo.this);
        barraDeProgreso.setMessage("Cargando datos...");
        barraDeProgreso.show();

        // y luego definimos los valores que nos regresa a un textview y a un
        // imageview
        TextViewMarca.setText("Marca :" + miAuto.getMarca());
        TextViewAuto.setText("Auto :" + miAuto.getAuto());
        imagen = (miAuto.getimage());

        ImageLoader cargaImagen = new ImageLoader(Volley.newRequestQueue(getApplicationContext()),
                new mx.itesm.csf.parseojson.ImageLoader());

        // Si estamos usando un ImageView normal, entonces...
        cargaImagen.get(imagen, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error al cargar imagen: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // carga la imagen en el  imageview
                    ImageViewUrl.setImageBitmap(response.getBitmap());
                    barraDeProgreso.hide();
                }
            }
        });
    }
}

