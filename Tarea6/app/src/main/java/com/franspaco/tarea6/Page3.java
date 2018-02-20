package com.franspaco.tarea6;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Page3 extends Fragment implements View.OnClickListener{

    View rootview;

    ArrayAdapter<String> adapter;

    AutoCompleteTextView atv;

    int count = 0;

    public Page3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_page3, container, false);
        rootview.findViewById(R.id.p3_ok).setOnClickListener(this);
        atv = rootview.findViewById(R.id.p3_i);
        updateAutocomplete();
        return rootview;
    }

    private void updateAutocomplete() {
        SharedPreferences preferences = rootview.getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        int indx = 0;
        String data;
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            data = preferences.getString("auto_" + indx++, "");
            if (data.length() == 0)
                break;
            Log.v("DEBUG", data);
            list.add(data);
            count++;
        }
        String[] list_array = list.toArray(new String[list.size()]);
        adapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_dropdown_item_1line, list_array);
        atv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        String text = atv.getText().toString();
        if(text.isEmpty())
            return;

        SharedPreferences preferences = rootview.getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("auto_" + count++, text);
        editor.commit();

        adapter.add(text);
        adapter.notifyDataSetChanged();
    }
}
