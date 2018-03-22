package com.franspaco.tareacrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DBAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adaptor = new DBAdaptor(this);

    }

    public void onClick_send(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.button1:
                intent = new Intent(this, MainActivity1.class);
                break;
            case R.id.button2:
                intent = new Intent(this, MainActivity2.class);
                break;
            case R.id.button3:
                intent = new Intent(this, MainActivity3.class);
                break;
        }
        startActivity(intent);
    }
}
