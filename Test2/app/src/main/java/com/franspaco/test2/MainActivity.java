package com.franspaco.test2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String message = "Reached onCreate";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    @Override
    protected void onStart(){
        super.onStart();
        String message = "Reached onStart";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    @Override
    protected void onResume(){
        super.onResume();
        String message = "Reached onResume";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        String message = "Reached onRestart";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    @Override
    protected void onPause(){
        super.onPause();
        String message = "Reached onPause";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    @Override
    protected void onStop(){
        super.onStop();
        String message = "Reached onStop";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        String message = "Reached onDestroy";
        Log.v("MainActivity", message);
        sendToast(message);
        sendAlertDialog(message);
    }

    private void sendToast(String str){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, str, duration);
        toast.show();
    }

    private void sendAlertDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg).setTitle("MainActivity");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked OK button
//            }
//        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
