package com.franspaco.tareacomponentes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityThankYou extends AppCompatActivity {

    TextView tv;

    String[] p1 = {"Si", "Ok", "No se", "No"};
    String[] p2 = {"Muy profesional", "Profesional", "Poco Profesional", "Nada profesional"};
    String[] p4 = new String[5];
    String[] p5 = {"Malo", "Bueno"};
    String[] p8 = {"Si", "No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        p4[0] = getResources().getString(R.string.p4_1);
        p4[1] = getResources().getString(R.string.p4_2);
        p4[2] = getResources().getString(R.string.p4_3);
        p4[3] = getResources().getString(R.string.p4_4);
        p4[4] = getResources().getString(R.string.p4_5);

        tv = findViewById(R.id.result);

        StringBuilder sb = new StringBuilder();

        Responses response = getIntent().getParcelableExtra("responses");

        sb.append(getResources().getString(R.string.p1)).append("\n").append(p1[response.p1]).append("\n\n");

        sb.append(getResources().getString(R.string.p2)).append("\n").append(p2[response.p2]).append("\n\n");

        sb.append(getResources().getString(R.string.p3)).append("\n").append(response.p3).append("\n\n");
        sb.append(getResources().getString(R.string.p4)).append("\n").append(p4[response.p4]).append("\n\n");
        sb.append(getResources().getString(R.string.p5)).append("\n").append(p5[response.p5]).append("\n\n");
        sb.append(getResources().getString(R.string.p6)).append("\n").append(response.p6).append(" estrellas.\n\n");
        sb.append(getResources().getString(R.string.p7)).append("\n").append(response.p6).append(" estrellas.\n\n");
        sb.append(getResources().getString(R.string.p8)).append("\n").append(p8[response.p8]).append("\n\n");
        sb.append(getResources().getString(R.string.p9)).append("\n").append(response.p9);

        tv.setText(sb.toString());
    }
}
