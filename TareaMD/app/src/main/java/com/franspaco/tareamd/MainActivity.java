package com.franspaco.tareamd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_Toaster(View view) {
        Intent intent = new Intent(this, ToastCounter.class);
        startActivity(intent);
    }

    public void onClick_WebView(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }

    public void onClick_Calculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void onClick_Grades(View view) {
        Intent intent = new Intent(this, GradingActivity.class);
        startActivity(intent);
    }
}
