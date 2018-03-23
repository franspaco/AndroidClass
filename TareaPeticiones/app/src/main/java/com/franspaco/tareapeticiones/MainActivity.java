package com.franspaco.tareapeticiones;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    final StringRequest requestString = new StringRequest(
            Request.Method.GET,
            "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/string.app",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("DEBUG", "Got text");
                    dialogHelper("String", response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("ERROR", "Volley error text");
                }
            }
    );

    JsonObjectRequest requestJson = new JsonObjectRequest(
            "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/ObjetoJSON.app",
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.v("DEBUG", "Got json");
                    dialogHelper("JSON Object", response.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("ERROR", "Volley error json");
                }
            }
    );

    JsonArrayRequest requestJsonArray = new JsonArrayRequest(
            "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/ArregloJSON.app?count=2",
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.v("DEBUG", "Got array");
                    dialogHelper("JSON Array", response.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("ERROR", "Volley error array");
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

    }

    private void snackbarHelper(String message){
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.mainView), message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();

    }

    private void dialogHelper(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).show();
    }

    private void imageDialogHelper(String title, Bitmap image){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        AlertDialog dialog = builder.create();

        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_image, null);
        ImageView imageView = dialogLayout.findViewById(R.id.imageViewMenu);
        imageView.setImageBitmap(image);

        dialog.setView(dialogLayout);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.show();
    }

    public void onClick_btn1(View view) {
        snackbarHelper("Fetching String");
        requestQueue.add(requestString);
    }
    public void onClick_btn2(View view) {
        snackbarHelper("Fetching JSON Object");
        requestQueue.add(requestJson);
    }
    public void onClick_btn3(View view) {
        snackbarHelper("Fetching JSON Array");
        requestQueue.add(requestJsonArray);
    }
    public void onClick_btn4(View view) {
        snackbarHelper("Fetching Image");
        Log.v("DEBUG", "Fetching img");
        ImageRequest requestImage = new ImageRequest(
                "http://ubiquitous.csf.itesm.mx/~raulms/images/people/Frida.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.v("DEBUG", "Got image");
                        imageDialogHelper("Image", response);
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
