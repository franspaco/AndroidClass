package com.franspaco.testapi;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView post_list;
    Gson gson;
    ProgressDialog progressDialog;
    List<Object> list;
    Map<String, Object> mapPost;
    Map<String, Object> mapTitle;
    int postID;
    String[] postTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post_list = findViewById(R.id.post_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                BackEnd.endpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        gson = new Gson();
                        list = gson.fromJson(response, List.class);
                        postTitle = new String[list.size()];
                        for (int i = 0; i < list.size(); i++){
                            mapPost = (Map<String, Object>) list.get(1);
                            mapTitle = (Map<String, Object>)mapPost.get("title");
                            postTitle[i] = (String) mapTitle.get("rendered");
                        }

                        post_list.setAdapter(new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, postTitle));
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
        );

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mapPost = (Map<String, Object>) list.get(position);
                postID = ((Double)mapPost.get("id")).intValue();

                Intent intent = new Intent(getApplicationContext(), Post.class);
                intent.putExtra("id", String.valueOf(postID));
                startActivity(intent);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
