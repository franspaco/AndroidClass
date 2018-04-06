package com.franspaco.tareacms2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchModel extends LayeredFragment {

    View rootview;
    ListView results;
    RequestQueue requestQueue;
    Car[] cars;

    public SearchModel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_search_model, container, false);

        results = rootview.findViewById(R.id.results_list2);

        EditText et = rootview.findViewById(R.id.modelNameSearch);
        requestQueue = Volley.newRequestQueue(rootview.getContext());

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StringRequest request = new StringRequest(
                        BackEnd.qryModel + s,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Gson gson = new Gson();
                                cars = gson.fromJson(s, Car[].class);
                                String[] names = new String[cars.length];
                                for (int i = 0; i < cars.length; i++) {
                                    names[i] = cars[i].brand + " " + cars[i].model;
                                }
                                results.setAdapter(new ArrayAdapter(
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
                requestQueue.add(request);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(rootview.getContext(), CarActivity.class);
                intent.putExtra("car-id", String.valueOf(cars[position].id));
                startActivity(intent);
            }
        });

        return rootview;
    }

}
