package com.franspaco.tareamd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.gc.materialdesign.views.ButtonRectangle;

public class CalculatorActivity extends AppCompatActivity {

    ButtonRectangle butAdd;
    ButtonRectangle butSub;
    ButtonRectangle butMul;
    ButtonRectangle butDiv;
    EditText etA;
    EditText etB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("LOL", "LOLLLLL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        butAdd = findViewById(R.id.butAdd);
        butSub = findViewById(R.id.butSub);
        butMul = findViewById(R.id.butMul);
        butDiv = findViewById(R.id.butDiv);
        etA = findViewById(R.id.valA);
        etB = findViewById(R.id.valB);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valA;
                double valB;
                try {
                    valA = Double.parseDouble(etA.getText().toString());
                    valB = Double.parseDouble(etB.getText().toString());

                    double result = 0;

                    Intent intent = new Intent(CalculatorActivity.this, DisplayActivity.class);
                    switch (v.getId()){
                        case R.id.butAdd:
                            result = valA + valB;
                            break;
                        case R.id.butSub:
                            result = valA - valB;
                            break;
                        case R.id.butMul:
                            result = valA * valB;
                            break;
                        case R.id.butDiv:
                            result = valA / valB;
                            break;
                    }
                    intent.putExtra("result", Double.toString(result));
                    startActivity(intent);
                }
                catch (NumberFormatException ex){
                    sendToast("Invalid data!");
                }

            }
        };

        butAdd.setOnClickListener(listener);
        butSub.setOnClickListener(listener);
        butMul.setOnClickListener(listener);
        butDiv.setOnClickListener(listener);
    }

    public void sendToast(String str){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, str, duration);
        toast.show();
    }
}
