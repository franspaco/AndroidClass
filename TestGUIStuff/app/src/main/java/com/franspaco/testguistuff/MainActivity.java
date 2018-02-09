package com.franspaco.testguistuff;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnSave_onClick(View view) {
        sendToast("Click " + ((Button)findViewById(view.getId())).getText().toString());
    }

    public void sendToast(String str){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, str, duration);
        toast.show();
    }

    public void autoSave_onClick(View view) {
        sendToast("Checkbox is " + ((CheckBox)view).isChecked());
        ((CheckBox)findViewById(R.id.checkBox2)).setChecked(true);

    }
}
