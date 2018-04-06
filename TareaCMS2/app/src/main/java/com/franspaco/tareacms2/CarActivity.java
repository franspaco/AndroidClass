package com.franspaco.tareacms2;

import android.app.VoiceInteractor;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class CarActivity extends AppCompatActivity {
    private ActionBar toolbar;

    private Car car;

    RequestQueue requestQueue;

    TextView brand;
    TextView model;

    LinearLayout photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        final String id = getIntent().getExtras().getString("car-id");

        toolbar = getSupportActionBar();
        requestQueue = Volley.newRequestQueue(this);

        brand = findViewById(R.id.brand_name);
        model = findViewById(R.id.model_name);
        photos = findViewById(R.id.photo_viewer);

        StringRequest request = new StringRequest(
                BackEnd.qryId + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.v("DEBUG",s);
                        Gson gson = new Gson();
                        car = gson.fromJson(s, Car.class);
                        toolbar.setTitle(car.brand + " " + car.model);
                        brand.setText(car.brand);
                        model.setText(car.model);
                        fetchImages();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );

        requestQueue.add(request);
    }

    private void fetchImages(){
        if(car == null){
            return;
        }
        for(String s: car.imgs){
            ImageRequest requestImage = new ImageRequest(
                    BackEnd.imgUrl + s,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            Log.v("DEBUG", "Got image");
                            ImageView iv = new ImageView(getApplicationContext());
                            iv.setImageBitmap(response);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            iv.setLayoutParams(lp);
                            photos.addView(iv);
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
            requestQueue.add(requestImage);
        }
    }
}
