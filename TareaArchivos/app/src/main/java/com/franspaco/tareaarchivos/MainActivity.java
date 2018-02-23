package com.franspaco.tareaarchivos;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText key;
    EditText p0;
    RadioGroup p1;
    RadioGroup p2;
    CheckBox p3;
    CheckBox p4;
    CheckBox p5;
    ToggleButton p6;

    ToggleButton searchType;

    private final String path = "/testApp/";
    private final String file_ex = ".txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        key = findViewById(R.id.key);
        p0 = findViewById(R.id.p0);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);

        searchType = findViewById(R.id.searchType);
    }

    // * * * * * * * * * * * * Write * * * * * * * * * * * * * * * * *
    private String getKey(){
        return key.getText().toString().replaceAll("\\s+", "_");
    }

    private String getPhone(){
        return p0.getText().toString();
    }

    private String getGender(){
        return String.valueOf(p1.indexOfChild(findViewById(p1.getCheckedRadioButtonId())));
    }

    private String getPolicy(){
        return String.valueOf(p2.indexOfChild(findViewById(p2.getCheckedRadioButtonId())));
    }

    private String getCB1(){
        return p3.isChecked()? "true":"false";
    }

    private String getCB2(){
        return p4.isChecked()? "true":"false";
    }

    private String getCB3(){
        return p5.isChecked()? "true":"false";
    }

    private String getWaranty(){
        return p6.isChecked()? "true":"false";
    }

    public void onClick_save(View view) {
        String key = getKey() + file_ex;
        StringBuilder sb = new StringBuilder();
        sb.append(getPhone()).append(',');
        sb.append(getGender()).append(',');
        sb.append(getPolicy()).append(',');
        sb.append(getCB1()).append(',');
        sb.append(getCB2()).append(',');
        sb.append(getCB3()).append(',');
        sb.append(getWaranty()).append('\n');

        String data = sb.toString();

        save_file(key, data);
    }

    private void save_file(String filename, String data){
        File dir = new File(getExternalFilesDir(null), "DemoFile.txt");
        BufferedWriter writer = null;
        Log.v("FILE", dir.toString());
        try {
            File file = new File(getExternalFilesDir(null), filename);

            writer = new BufferedWriter(new FileWriter(file));
            writer.write(data);
            Toast.makeText(this, "Data saved!", Toast.LENGTH_LONG).show();

        }
        catch (FileNotFoundException ex){
            Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
        }
        catch (IOException ex){
            Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
        }
        finally {
            close(writer);
        }
    }

    // * * * * * * * * * * * * READ * * * * * * * * * * * * * * * * *

    public void onClick_search(View view) {
        if(searchType.isChecked()) {
            String key = getKey() + file_ex;
            populateFromFile(key);
        }
        else{
            phone_search();
        }
    }

    private void phone_search() {
        File dir = getExternalFilesDir(null);
        File[] files = dir.listFiles();
        String phone = getPhone();
        for(File f: files){
            if(!f.isFile())
                continue;
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(f));
                String line = reader.readLine();
                Log.v("ReadData:", line);
                String[] tokens = line.split(",");
                if(phone.equals(tokens[0])){
                    populateFromFile(f.getName());
                    setName(f.getName());
                    return;
                }
            }
            catch (IOException ex){
                Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
            }
        }
        Toast.makeText(this, "Record not found!", Toast.LENGTH_LONG).show();
    }

    private void populateFromFile(String filename){
        String[] values = readTokens(filename);
        if(values == null || values.length != 7){
            return;
        }
        setPhone(values[0]);
        setGender(values[1]);
        setPolicy(values[2]);
        setCB1(values[3]);
        setCB2(values[4]);
        setCB3(values[5]);
        setWaranty(values[6]);
    }

    private void setName(String s_key){
        key.setText(s_key.replace(file_ex, "").replace("_", " "));
    }

    private void setPhone(String phone){
        p0.setText(phone);
    }

    private void setGender(String data){
        int indx = Integer.valueOf(data);
        if(indx >= -1) {
            ((RadioButton) p1.getChildAt(indx)).setChecked(true);
        }
        else{
            p1.clearCheck();
        }
    }

    private void setPolicy(String data){
        int indx = Integer.valueOf(data);
        if(indx >= -1) {
            ((RadioButton) p2.getChildAt(indx)).setChecked(true);
        }
        else{
            p2.clearCheck();
        }
    }

    private void setCB1(String data){
        p3.setChecked(Boolean.valueOf(data));
    }

    private void setCB2(String data){
        p4.setChecked(Boolean.valueOf(data));
    }

    private void setCB3(String data){
        p5.setChecked(Boolean.valueOf(data));
    }

    private void setWaranty(String data){
        p6.setChecked(Boolean.valueOf(data));
    }

    private String[] readTokens(String filename){
        BufferedReader reader = null;
        try {

            File file = new File(getExternalFilesDir(null), filename);

            reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            Log.v("ReadData:", line);

            return line.split(",");

        }
        catch (FileNotFoundException ex){
            Toast.makeText(this, "Record not found!", Toast.LENGTH_LONG).show();
        }
        catch (IOException ex){
            Toast.makeText(this, "File error!", Toast.LENGTH_LONG).show();
        }
        finally {
            close(reader);
        }
        return null;
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
