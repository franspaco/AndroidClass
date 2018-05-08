package com.franspaco.testgraphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart chart = findViewById(R.id.chart);

        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1,2));
        entries.add(new BarEntry(2,4));
        entries.add(new BarEntry(3,6));
        entries.add(new BarEntry(4,8));

        BarDataSet dataSet = new BarDataSet(entries, "Stuff");

        BarData data = new BarData(dataSet);

        chart.setData(data);
        chart.invalidate();
    }
}
