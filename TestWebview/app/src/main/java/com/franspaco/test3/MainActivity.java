package com.franspaco.test3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button go;
    WebView wv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = findViewById(R.id.go_button);
        wv = findViewById(R.id.webView);
        et = findViewById(R.id.url_text);

        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = et.getText().toString();
                if(!url.startsWith("http://") || !url.startsWith("https://")){
                    url = "http://" + url;
                }
                wv.loadUrl(url);
            }
        });
    }
}