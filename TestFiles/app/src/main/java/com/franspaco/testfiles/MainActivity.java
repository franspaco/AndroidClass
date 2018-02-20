package com.franspaco.testfiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText inputField;

    final int BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);

    }

    public void onClick_Save(View view) {
        String txt = inputField.getText().toString();
        FileOutputStream fOut = null;
        try {
            fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            try {
                osw.write(txt);
            }
            catch (IOException ex){
                Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
            }
            fOut.flush();
            Toast.makeText(this, "Data saved!", Toast.LENGTH_LONG).show();

        }
        catch (IOException ex){
            Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
        }
        finally {
            close(fOut);
        }
    }

    public void onCLick_Read(View view) {
        FileInputStream fIn = null;

        try {
            fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[BLOCK_SIZE];
            StringBuilder s = new StringBuilder();
            int charRead;

            while ((charRead = isr.read()) > 0) {
                s.append(String.valueOf(charRead));
            }

            String text = s.toString();

            inputField.setText(text);

            Toast.makeText(this, "Data read!", Toast.LENGTH_LONG).show();
        }
        catch (IOException ex){
            Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
        }
        finally {
            close(fIn);
        }
    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            //log the exception
        }
    }
}
