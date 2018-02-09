package com.franspaco.tareamd;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GradingActivity extends AppCompatActivity {

    TextView tv_result;
    EditText et_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grading);
        tv_result = findViewById(R.id.result);
        et_grade = findViewById(R.id.gradeEditText);

        et_grade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //tryParse();
            }

            @Override
            public void afterTextChanged(Editable s) {
                tryParse();
            }
        });
    }

    private void tryParse(){
        String res_s = et_grade.getText().toString();
        try {
            int grade = Integer.parseInt(res_s);
            displayResult(grade);
        }
        catch (NumberFormatException ex){
            invalidInput("No ews un numero!");
        }
    }

    private void displayResult(int cal){
        if( cal < 10){
            // Invalid
            invalidInput("Invalido: Menor a 10!");
        }
        else if(cal <= 70){
            // Bad
            tv_result.setText("Malo");
            tv_result.setBackgroundColor(Color.rgb(255,0,0));
        }
        else if(cal <= 80){
            // Regular
            tv_result.setText("Regular");
            tv_result.setBackgroundColor(Color.rgb(255,140,0));
        }
        else if(cal <= 94){
            // Good
            tv_result.setText("Bueno");
            tv_result.setBackgroundColor(Color.rgb(140,255,0));
        }
        else if(cal <= 100){
            // Excellent
            tv_result.setText("Excelente");
            tv_result.setBackgroundColor(Color.rgb(0,255,0));
        }
        else {
            // Invalid
            invalidInput("Invalido: Mayor a 100!");
        }
    }

    private void invalidInput(String message){
        resetResult();
        tv_result.setText(message);
    }

    private void resetResult(){
        tv_result.setText("");
        tv_result.setBackgroundColor(Color.rgb(255,255,255));
    }
}
