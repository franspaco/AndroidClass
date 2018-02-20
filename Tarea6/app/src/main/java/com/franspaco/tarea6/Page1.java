package com.franspaco.tarea6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class Page1 extends Fragment {

    private static final String[] CAR_BRANDS = new String[] {
            "BMW", "Mercedes", "Audi", "Porsche", "Ferrari", "Ford", "Toyota",
            "Nissan", "Honda"
    };

    public Page1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_page1, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_dropdown_item_1line, CAR_BRANDS);
        AutoCompleteTextView textView = rootview.findViewById(R.id.p1_i1);
        textView.setAdapter(adapter);
        return rootview;
    }



}
