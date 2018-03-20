package com.franspaco.tareacrud;

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

public class MainActivity3 extends AppCompatActivity {

    private ActionBar toolbar;
    DBAdaptor adaptor;

    EditText name_et;
    EditText address_et;


    Button go_button;
    Button delete;
    Button update;

    int status = 0;

    Integer dealership = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            clear();
            switch (item.getItemId()) {
                case R.id.navigation_insert_2:
                    toolbar.setTitle(R.string.title_p1);
                    go_button.setText(R.string.title_p1);
                    delete.setVisibility(View.INVISIBLE);
                    update.setVisibility(View.INVISIBLE);
                    status = 0;
                    return true;
                case R.id.navigation_search_name_2:
                    toolbar.setTitle(R.string.title_p2);
                    go_button.setText(R.string.title_p2);
                    status = 1;
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.title_p1);

        adaptor = new DBAdaptor(this);

        name_et = findViewById(R.id.id_name_3);
        address_et = findViewById(R.id.id_address_3);

        go_button = findViewById(R.id.go_button_3);
        delete = findViewById(R.id.id_delete_3);
        update = findViewById(R.id.id_update_3);

        delete.setVisibility(View.INVISIBLE);
        update.setVisibility(View.INVISIBLE);

        Log.v("DEBUG","200");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_3);
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
                search();
                break;
        }
    }

    private void search(){
        try {
            adaptor.open();
            Cursor cur;
            String name = name_et.getText().toString();
            cur = adaptor.dealershipByName(name);

            if(cur == null || cur.getCount() < 1){
                toast("No encontrado!");
                dealership = null;
                return;
            }
            name_et.setText(cur.getString(0));
            address_et.setText(cur.getString(1));
            dealership = cur.getInt(2);
            toast("Data loaded!");
            delete.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            hideSoftKeyboard(this);
        }
        catch (SQLException ex){
            Log.e("SQL-EX", ex.toString());
            dealership = null;
        }
        finally {
            adaptor.close();
        }
    }

    private void do_insert(){
        try {
            adaptor.open();
            adaptor.insertDealership(
                    name_et.getText().toString(),
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
        address_et.setText("");
        delete.setVisibility(View.INVISIBLE);
        update.setVisibility(View.INVISIBLE);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void onClick_delete(View view) {
        if(dealership == null){
            toast("No se puede borrar!");
            return;
        }
        try {
            adaptor.open();
            adaptor.deleteDealership(dealership);
            toast("Eliminado!");
            clear();
        }
        catch (SQLException ex){
            Log.e("SQL-EX", ex.toString());
        }
        finally {
            adaptor.close();
        }
    }

    public void onClick_update(View view) {
        if(dealership == null){
            toast("No se puede editar!");
            return;
        }
        try {
            adaptor.open();
            adaptor.updateDealership(
                    dealership,
                    name_et.getText().toString(),
                    address_et.getText().toString());
            toast("Guardado!");
        }
        catch (SQLException ex){
            Log.e("SQL-EX", ex.toString());
        }
        finally {
            adaptor.close();
        }
    }
}
