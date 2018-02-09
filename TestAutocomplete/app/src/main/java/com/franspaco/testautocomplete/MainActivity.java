package com.franspaco.testautocomplete;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    String marcas[] = {
            "marca1",
            "marca2",
            "marca3",
            "marca4",
            "marca5",
            "marca6",
            "marca7",
            "marca8",
            "marca9"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, marcas);

        AutoCompleteTextView atv = findViewById(R.id.txtMarcas);

        atv.setThreshold(3);
        atv.setAdapter(adaptador);
    }

    public void onClick_openNext(View view) {
        Intent intent = new Intent(this, DataStorage.class);
        startActivity(intent);
    }
}
