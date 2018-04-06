package com.franspaco.tareacms2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandsFragment extends LayeredFragment {

    View rootview;

    int[] ids;

    public BrandsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_brands, container, false);
        RequestQueue requestQueue = Volley.newRequestQueue(rootview.getContext());

        final ListView brand_list = rootview.findViewById(R.id.brands_list);



        JsonArrayRequest request = new JsonArrayRequest(
                BackEnd.brandsUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String[] names = new String[response.length()];
                        ids = new int[response.length()];

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                names[i] = response.getJSONObject(i).getString("brand");
                                ids[i] = response.getJSONObject(i).getInt("id");
                            }
                            catch (JSONException ex){
                                Log.v("ERROR", "Error parsing element #" + i);
                            }
                        }
                        brand_list.setAdapter(new ArrayAdapter(
                                rootview.getContext(),
                                android.R.layout.simple_list_item_1,
                                names
                        ));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(rootview.getContext(), "Error :(", Toast.LENGTH_LONG).show();
                    }
                }
        );

        brand_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(layer == 0) {
                    JsonArrayRequest request1 = new JsonArrayRequest(
                            BackEnd.qryBrandId + String.valueOf(ids[position]),
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    String[] names = new String[response.length()];
                                    ids = new int[response.length()];

                                    for (int i = 0; i < response.length(); i++) {
                                        try {
                                            names[i] = response.getJSONObject(i).getString("model");
                                            ids[i] = response.getJSONObject(i).getInt("id");
                                        } catch (JSONException ex) {
                                            Log.v("ERROR", "Error parsing element #" + i);
                                        }
                                    }
                                    brand_list.setAdapter(new ArrayAdapter(
                                            rootview.getContext(),
                                            android.R.layout.simple_list_item_1,
                                            names
                                    ));
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(rootview.getContext(), "Error :(", Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                    Volley.newRequestQueue(rootview.getContext()).add(request1);
                    layer = 1;
                }
                else if(layer == 1){
                    Intent intent = new Intent(rootview.getContext(), CarActivity.class);
                    intent.putExtra("car-id", String.valueOf(ids[position]));
                    startActivity(intent);
                }
            }
        });


        requestQueue.add(request);
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
