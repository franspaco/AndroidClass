package com.franspaco.tareasqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class MainActivity1 extends AppCompatActivity {

    private ActionBar toolbar;
    DBAdaptor adaptor;

    EditText name_et;
    EditText last_name_et;
    EditText email_et;
    RadioGroup sex_rg;
    EditText address_et;


    Button go_button;

    int status = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            clear();
            switch (item.getItemId()) {
                case R.id.navigation_insert:
                    toolbar.setTitle(R.string.title_p1);
                    go_button.setText(R.string.title_p1);
                    status = 0;
                    return true;
                case R.id.navigation_search_name:
                    toolbar.setTitle(R.string.title_p2);
                    go_button.setText(R.string.title_p2);
                    status = 1;
                    return true;
                case R.id.navigation_search_last_name:
                    toolbar.setTitle(R.string.title_p3);
                    go_button.setText(R.string.title_p3);
                    status = 2;
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.title_p1);

        adaptor = new DBAdaptor(this);

        name_et = findViewById(R.id.id_name);
        last_name_et = findViewById(R.id.id_last_name);
        email_et = findViewById(R.id.id_email);
        sex_rg = findViewById(R.id.id_sex);
        address_et = findViewById(R.id.id_address);

        go_button = findViewById(R.id.go_button);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onClick_go_Button(View view) {
        switch (status){
            case 0:
                do_insert();
                break;
            case 1:
                search(true);
                break;
            case 2:
                search(false);
                break;
        }
    }

    private void search(boolean nameSearch){
        try {
            adaptor.open();
            Cursor cur;
            if(nameSearch){
                String name = name_et.getText().toString();
                cur = adaptor.clientByName(name);
            }
            else {
                String last_name = last_name_et.getText().toString();
                cur = adaptor.clientByLastName(last_name);
            }
            if(cur == null){
                toast("No encontrado!");
                return;
            }
            name_et.setText(cur.getString(0));
            last_name_et.setText(cur.getString(1));
            email_et.setText(cur.getString(2));
            ((RadioButton)sex_rg.getChildAt(cur.getInt(3))).setChecked(true);
            address_et.setText(cur.getString(4));
            toast("Data loaded!");
            hideSoftKeyboard(this);
        }
        catch (SQLException ex){
            Log.e("SQL-EX", ex.toString());
        }
        finally {
            adaptor.close();
        }
    }

    private void do_insert(){
        try {
            adaptor.open();
            adaptor.insertClient(
                    name_et.getText().toString(),
                    last_name_et.getText().toString(),
                    email_et.getText().toString(),
                    sex_rg.indexOfChild(findViewById(sex_rg.getCheckedRadioButtonId())),
                    address_et.getText().toString());
            toast("Insertado!");
        }
        catch (SQLException ex){
            Log.e("SQL-EX", ex.toString());
        }
        finally {
            adaptor.close();
        }
    }

    private void clear(){
        name_et.setText("");
        last_name_et.setText("");
        email_et.setText("");
        sex_rg.check(-1);
        address_et.setText("");
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
