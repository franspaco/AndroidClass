package com.franspaco.tarearest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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


public class CatsView extends AppCompatActivity {

    ListView post_list;
    ProgressDialog progressDialog;

    int[] catIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);

        post_list = findViewById(R.id.cats_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        JsonArrayRequest requestList = new JsonArrayRequest(
                BackEnd.endpointCats,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String[] names = new String[response.length()];
                        catIds = new int[response.length()];

                        for(int i = 0; i < response.length(); i++){
                            try {
                                names[i] = response.getJSONObject(i).getString("name");
                                catIds[i] = response.getJSONObject(i).getInt("id");
                            }
                            catch (JSONException ex){
                                Log.v("ERROR", "Error parsing element #" + i);
                            }
                        }
                        post_list.setAdapter(
                                new ArrayAdapter(
                                        CatsView.this,
                                        android.R.layout.simple_list_item_1,
                                        names
                                )
                        );
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(CatsView.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
        );

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PostsView.class);
                intent.putExtra("cat-id", String.valueOf(catIds[position]));
                startActivity(intent);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestList);
    }
}
