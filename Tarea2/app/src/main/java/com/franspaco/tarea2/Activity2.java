package com.franspaco.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle extras = getIntent().getExtras();
        String tmp = extras.getString("show_text");
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(tmp);
    }
}
