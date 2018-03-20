package com.franspaco.tarearest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class PostsView extends AppCompatActivity {

    ListView post_list;
    ProgressDialog progressDialog;

    int[] postIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        final String cat = getIntent().getExtras().getString("cat-id");
        String uri = BackEnd.endpointPosts + "?categories=" + cat;

        post_list = findViewById(R.id.post_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        JsonArrayRequest requestList = new JsonArrayRequest(
                uri,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String[] titles = new String[response.length()];
                        postIds = new int[response.length()];

                        for(int i = 0; i < response.length(); i++){
                            try {
                                titles[i] = response.getJSONObject(i).getJSONObject("title").getString("rendered");
                                postIds[i] = response.getJSONObject(i).getInt("id");
                            }
                            catch (JSONException ex){
                                Log.v("ERROR", "Error parsing element #" + i);
                            }
                        }
                        post_list.setAdapter(
                                new ArrayAdapter(
                                        PostsView.this,
                                        android.R.layout.simple_list_item_1,
                                        titles
                                )
                        );
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(PostsView.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
        );

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Post.class);
                intent.putExtra("id", String.valueOf(postIds[position]));
                startActivity(intent);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestList);
    }
}
