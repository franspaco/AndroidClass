package com.franspaco.tarea6;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Page4 extends Fragment implements View.OnClickListener{

    View rootview;

    EditText name, email, phone, address;

    public Page4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_page4, container, false);
        rootview.findViewById(R.id.p4_load).setOnClickListener(this);
        rootview.findViewById(R.id.p4_save).setOnClickListener(this);

        name = rootview.findViewById(R.id.p4_1);
        email = rootview.findViewById(R.id.p4_2);
        phone = rootview.findViewById(R.id.p4_3);
        address = rootview.findViewById(R.id.p4_4);

        return rootview;
    }

    @Override
    public void onClick(View v) {
        String key = name.getText().toString();
        if(key.isEmpty()){
            toast("Nombre vacío!");
            return;
        }
        switch (v.getId()){
            case R.id.p4_save:
                save(key);
                break;
            case R.id.p4_load:
                load(key);
                break;
        }
    }

    private void save(String key){
        String email_s = email.getText().toString();
        String phone_s = phone.getText().toString();
        String address_s = address.getText().toString();
        SharedPreferences preferences = rootview.getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key + "_email", email_s);
        editor.putString(key + "_phone", phone_s);
        editor.putString(key + "_address", address_s);
        editor.commit();
        toast("Guardado: " + key + "!");
    }

    private void load(String key){
        SharedPreferences preferences = rootview.getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        String email_s = preferences.getString(key + "_email", null);
        String phone_s = preferences.getString(key + "_phone", null);
        String address_s = preferences.getString(key + "_address", null);

        if (email_s == null && phone_s == null && address_s == null){
            toast("No encontrado!");
            email.setText("");
            phone.setText("");
            address.setText("");
        }
        else {
            toast("Encontrado!");
            email.setText( (email_s!= null) ? email_s : "");
            phone.setText( (phone_s!= null) ? phone_s : "");
            address.setText( (address_s!= null) ? address_s : "");
        }
    }

    private void toast(String msg){
        Toast.makeText(rootview.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
