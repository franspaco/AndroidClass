package com.franspaco.tareacomponentes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
    }

    public void onClick_Next(View view) {
        Responses response = (Responses) getIntent().getParcelableExtra("responses");

        response.p8 = (byte)(((ToggleButton)findViewById(R.id.p8)).isChecked() ? 1 : 0);
        Log.v("SAVED", String.valueOf(response.p8));

        response.p9 = ((EditText)findViewById(R.id.p9)).getText().toString();
        Log.v("SAVED", String.valueOf(response.p9));

        Intent intent;

        if(response.p1 == 3 ||
                response.p2 == 3 ||
                response.p4 == 4 ||
                response.p5 == 0 ||
                response.p6 <= 2 ||
                response.p7 <= 2 ||
                response.p8 == 0){
            intent = new Intent(this, ActivityContact.class);
        }
        else {
            intent = new Intent(this, ActivityThankYou.class);
        }

        intent.putExtra("responses", response);

        finish();
        startActivity(intent);
    }
}
