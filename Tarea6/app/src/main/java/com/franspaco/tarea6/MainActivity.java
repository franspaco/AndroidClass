package com.franspaco.tarea6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Log.v("DEBUG", "Fagment Loaded");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_page1:
                    toolbar.setTitle("Autocomplete");
                    fragment =  new Page1();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_page2:
                    toolbar.setTitle("Shared preferences");
                    fragment =  new Page2();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_page3:
                    toolbar.setTitle("Combinado");
                    fragment =  new Page3();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_page4:
                    toolbar.setTitle("Cuestionario");
                    fragment =  new Page4();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        toolbar.setTitle("Autocomplete");

        Fragment fragment =  new Page1();
        loadFragment(fragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onClick_p2_save(View view) {
        String data = ((EditText)findViewById(R.id.p2_input)).getText().toString();
        SharedPreferences preferences = getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("secret-key", data);
        editor.commit();
        Toast.makeText(this, "Saved data!", Toast.LENGTH_LONG).show();
    }

    public void onClick_p2_load(View view) {
        EditText ed = findViewById(R.id.p2_result);
        SharedPreferences preferences = getSharedPreferences("crm", Context.MODE_PRIVATE);
        String data = preferences.getString("secret-key", "");
        if(data.length()==0){
            ed.setText("");
            Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show();
        }
        else {
            ed.setText(data);
        }
    }
}
