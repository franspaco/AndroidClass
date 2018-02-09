package com.franspaco.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Bundle extras = getIntent().getExtras();
        String tmp = extras.getString("show_text");
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(tmp);
    }
}
