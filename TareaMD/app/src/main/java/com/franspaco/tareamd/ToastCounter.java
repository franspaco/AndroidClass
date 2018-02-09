package com.franspaco.tareamd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ToastCounter extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_counter);
        count = 0;
    }

    public void onClick_counter(View view) {
        count++;
        Toast.makeText(this, Integer.toString(count), Toast.LENGTH_SHORT).show();
    }
}
