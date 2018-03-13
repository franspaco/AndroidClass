package com.franspaco.testapi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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

public class Post extends AppCompatActivity {

    TextView title;

    WebView content;

    ProgressDialog progressDialog;

    Gson gson;

    Map<String, Object> mapPost;
    Map<String, Object> mapTitle;
    Map<String, Object> mapContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final String id = getIntent().getExtras().getString("id");

        title = findViewById(R.id.post_title);
        content = findViewById(R.id.post_content);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String uri = BackEnd.endpoint + "/" + id + "?fields=title,content";

        Log.v("DEBUG", uri);

        Log.v("DEBUG", id);


        StringRequest request = new StringRequest(
                Request.Method.GET,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        gson = new Gson();
                        Log.v("DEBUG", response);
                        mapPost = (Map<String, Object>) gson.fromJson(response, Map.class);
                        mapTitle = (Map<String, Object>) mapPost.get("title");
                        mapContent = (Map<String, Object>) mapPost.get("content");

                        title.setText(mapTitle.get("rendered").toString());
                        content.loadData(mapContent.get("rendered").toString(), "text/html", "UTF-8");

                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Post.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
