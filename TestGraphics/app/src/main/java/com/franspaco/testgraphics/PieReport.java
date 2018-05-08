package com.franspaco.testgraphics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PieReport extends AppCompatActivity {

    String url = "http://docker-azure.cloudapp.net/reports/inv";

    JSONObject js = new JSONObject();
    PieChart pieChart;

    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_report);

        pieChart = findViewById(R.id.inv_chart);
        try {
            js.put("token", "bda95581-8ae3-4623-bfc3-e380ba53f83c");
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        Log.v("DEBUG", js.toString());
        Log.v("DEBUG", "lol");

        request = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            List<PieEntry> entries = new ArrayList<>();
                            JSONArray array = response.getJSONArray("data");
                            for(int i = 0; i < array.length(); i++){
                                entries.add(new PieEntry(
                                        (float)array.getJSONObject(i).getDouble("count"),
                                        array.getJSONObject(i).getString("name")
                                ));
                            }
                            PieDataSet set = new PieDataSet(entries, "Consumo de últimas 24hrs");
                            set.setColors(new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4}, PieReport.this);
                            PieData data = new PieData(set);
                            pieChart.setData(data);
                            pieChart.invalidate();
                            Toast.makeText(PieReport.this, "Done!", Toast.LENGTH_SHORT).show();
                        }
                        catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERR", new String(error.networkResponse.data));
                        Log.e("ERR", js.toString());
                        Toast.makeText(PieReport.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Volley.newRequestQueue(this).add(request);
    }

    public void refresh(View view) {
        Volley.newRequestQueue(this).add(request);
    }
}
