package com.franspaco.tareacms2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;

    LayeredFragment currentf;

    @Override
    public void onBackPressed() {
        if(currentf.onBaseLayer()) {
            super.onBackPressed();
        }
        else{
            currentf.resetLayer();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(currentf).attach(currentf).commit();
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        currentf = (LayeredFragment)fragment;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_p1:
                    toolbar.setTitle(R.string.title_p1);
                    fragment = new BrandsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_p2:
                    toolbar.setTitle(R.string.title_p2);
                    fragment = new SearchBrand();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_p3:
                    toolbar.setTitle(R.string.title_p3);
                    fragment = new SearchModel();
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

        toolbar.setTitle(R.string.title_p1);
        loadFragment(new BrandsFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
