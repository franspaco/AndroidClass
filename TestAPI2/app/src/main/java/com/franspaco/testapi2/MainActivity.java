package com.franspaco.testapi2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Gson gson;

    String string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Bitmap bitmap;

    TextView text;
    TextView json;
    TextView array;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);
        json = findViewById(R.id.textView2);
        array = findViewById(R.id.textView3);
        image = findViewById(R.id.imageView);


        final StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/string.app",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("DEBUG", "Got text");
                        text.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("ERROR", "Volley error text");
                    }
                }
        );

        JsonObjectRequest request1 = new JsonObjectRequest(
                "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/ObjetoJSON.app",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("DEBUG", "Got json");
                        jsonObject = response;
                        json.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("ERROR", "Volley error json");
                    }
                }
        );

        JsonArrayRequest request2 = new JsonArrayRequest(
                "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/ArregloJSON.app?count=2",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("DEBUG", "Got array");
                        jsonArray = response;
                        array.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("ERROR", "Volley error array");
                    }
                }
        );

        ImageRequest request3 = new ImageRequest(
                "http://ubiquitous.csf.itesm.mx/~raulms/images/people/Frida.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.v("DEBUG", "Got image");
                        image.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("ERROR", "Volley error image:\n" + error.toString());
                        error.printStackTrace();
                    }
                }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        requestQueue.add(request1);
        requestQueue.add(request2);
        requestQueue.add(request3);
    }
}
