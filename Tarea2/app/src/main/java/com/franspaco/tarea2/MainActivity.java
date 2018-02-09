package com.franspaco.tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button)findViewById(R.id.button);
        Button b2 = (Button)findViewById(R.id.button2);
        Button b3 = (Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                String tmp = ((EditText) findViewById(R.id.editText)).getText().toString();
                Log.v("Debug", tmp);
                Intent intent = new Intent().setClass(MainActivity.this, Activity1.class);
                intent.putExtra("show_text", tmp);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                String tmp = ((EditText) findViewById(R.id.editText)).getText().toString();
                Log.v("Debug", tmp);
                Intent intent = new Intent().setClass(MainActivity.this, Activity2.class);
                intent.putExtra("show_text", tmp);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                String tmp = ((EditText) findViewById(R.id.editText)).getText().toString();
                Log.v("Debug", tmp);
                Intent intent = new Intent().setClass(MainActivity.this, Activity3.class);
                intent.putExtra("show_text", tmp);
                startActivity(intent);
            }
        });
    }
}
