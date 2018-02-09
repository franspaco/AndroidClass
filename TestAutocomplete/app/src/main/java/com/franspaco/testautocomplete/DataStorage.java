package com.franspaco.testautocomplete;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataStorage extends AppCompatActivity {

    private EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);

    }

    public void onClick_Save(View view) {
        String name = ed1.getText().toString();
        String contact = ed2.getText().toString();
        SharedPreferences preferences = getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, contact);
        editor.commit();
        Toast.makeText(this, "Saved contact data!", Toast.LENGTH_LONG).show();
    }

    public void onClick_Load(View view) {
        String name = ed1.getText().toString();
        SharedPreferences preferences = getSharedPreferences("crm", Context.MODE_PRIVATE);
        String data = preferences.getString(name, "");
        if(data.length()==0){
            ed2.setText("");
            Toast.makeText(this, "Contact not found", Toast.LENGTH_LONG);
        }
        else {
            ed2.setText(data);
        }

    }
}
