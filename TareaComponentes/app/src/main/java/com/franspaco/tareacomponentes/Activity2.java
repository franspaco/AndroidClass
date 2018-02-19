package com.franspaco.tareacomponentes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ToggleButton;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void onClick_Next(View view) {
        Intent intent = new Intent(this, Activity3.class);

        Responses response = getIntent().getParcelableExtra("responses");

        response.p3 = ((EditText)findViewById(R.id.p3)).getText().toString();
        Log.v("SAVED", String.valueOf(response.p3));
        RadioGroup rgp4 = findViewById(R.id.rgp4);
        response.p4 = (byte)rgp4.indexOfChild(findViewById(rgp4.getCheckedRadioButtonId()));
        Log.v("SAVED", String.valueOf(response.p4));

        response.p5 = (byte)(((ToggleButton)findViewById(R.id.p5)).isChecked() ? 1 : 0);
        Log.v("SAVED", String.valueOf(response.p5));

        response.p6 = (byte)((RatingBar)findViewById(R.id.p6)).getRating();
        Log.v("SAVED", String.valueOf(response.p6));

        response.p7 = (byte)((RatingBar)findViewById(R.id.p7)).getRating();
        Log.v("SAVED", String.valueOf(response.p7));

        intent.putExtra("responses", response);
        finish();
        startActivity(intent);
    }
}
