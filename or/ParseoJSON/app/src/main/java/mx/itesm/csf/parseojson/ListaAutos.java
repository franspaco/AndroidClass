package mx.itesm.csf.parseojson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import java.util.ArrayList;

public class ListaAutos extends ListFragment {

    private final String TAG = "ListaAutos";
    private ArrayList<Auto>  miListaDeAutos;
    String url = "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/Arreglo.exe?count=9";
    private final String EXTRA_JSON_OBJECT = "objetoAuto";

    // En el onCreate realizamos una petición de red a través de Volley
    // para obtener la petición del servicio. Al recibir la respuesta, la analizamos
    // y obtenemos  miListaDeAutos para luego establer el adaptador en ella.
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final   ProgressDialog barraDeProgreso = new ProgressDialog(getActivity());
        barraDeProgreso.setMessage("Cargando datos...");
        barraDeProgreso.show();

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG,response.toString());
                        Log.d(TAG,"Tamaño "+response.length());
                        miListaDeAutos = parserJSON.parseaArreglo(response);
                        barraDeProgreso.hide();

                        // creamos un adaptador personalizado para la lista de vehiculos
                        AdaptadorAuto adapter = new AdaptadorAuto(miListaDeAutos);
                        setListAdapter(adapter);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error en: " + error.getMessage());
                // oculta la barra de progreso
                barraDeProgreso.hide();
            }
        });
        // Anexamos el request a la cola
        Volley.newRequestQueue(getActivity()).add(jsonArrayReq);
    }


    private class AdaptadorAuto extends ArrayAdapter<Auto> {
        public AdaptadorAuto(ArrayList<Auto> autos) {
            super(getActivity(), 0, autos);
        }

        // sobre cargamos el metodo getView() en el que inflamos el archivo layout_lista_categorias.xml
        // en el que mostraremos los autos. Obtenemos el elemento Auto en la posición correspondiente de la lista miListaDeAutos y
        // devolvemos la vista.
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Por si no estamos en la vista, infla una
            Log.d(TAG,"posición "+position);
            if (convertView == null) {
                // Inflaremos el siguiente layout para mostrar los vehiculos. Así que debemos crear un
                // layout con un TextView dentro de un LinearLayout.
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.layout_lista_categorias, null);
            }
            Auto c = miListaDeAutos.get(position);


            TextView nameTextView =
                    (TextView) convertView.findViewById(R.id.textview_nombre);
            nameTextView.setText(c.getMarca());

            nameTextView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    Intent i = new Intent(getActivity(),ParseaObjetoArreglo.class);
                    Bundle args = new Bundle();
                    //args.putSerializable(EXTRA_OBJETO_JSON_AUTO, miListaDeAutos.get(position));
                    i.putExtra(EXTRA_JSON_OBJECT, miListaDeAutos.get(position));
                    startActivity(i);

                }
            });
            return convertView;
        }

    }

}


