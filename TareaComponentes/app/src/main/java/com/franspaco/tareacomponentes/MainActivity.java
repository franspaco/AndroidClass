package com.franspaco.tareacomponentes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_Next(View view) {
        Intent intent = new Intent(this, Activity2.class);

        Responses response = new Responses();
        response.name = ((EditText)findViewById(R.id.nameEdit)).getText().toString();
        response.email = ((EditText)findViewById(R.id.emailEdit)).getText().toString();
        response.phone = ((EditText)findViewById(R.id.phoneEdit)).getText().toString();

        RadioGroup rgp1 = findViewById(R.id.rgp1);
        response.p1 = (byte)rgp1.indexOfChild(findViewById(rgp1.getCheckedRadioButtonId()));
        Log.v("SAVED", String.valueOf(response.p1));

        RadioGroup rgp2= findViewById(R.id.rgp2);
        response.p2 = (byte)rgp2.indexOfChild(findViewById(rgp2.getCheckedRadioButtonId()));
        Log.v("SAVED", String.valueOf(response.p2));

        intent.putExtra("responses", response);
        finish();
        startActivity(intent);
    }
}
