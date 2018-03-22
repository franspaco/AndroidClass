package com.franspaco.tareaasync;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class Post extends AppCompatActivity {

    TextView title;

    WebView content;

    ProgressDialog progressDialog;

    JSONObject post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final String id = getIntent().getExtras().getString("id");

        title = findViewById(R.id.post_title);
        content = findViewById(R.id.post_content);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String uri = BackEnd.endpointPosts + "/" + id + "?fields=title,content";

        Log.v("DEBUG", uri);

        Log.v("DEBUG", id);

        new GetPostTask().execute(uri);
    }

    class GetPostTask extends GetHTTP {
        @Override
        protected void onPostExecute(String responseString) {
            try {
                JSONObject response = new JSONObject(responseString);
                post = response;
                try {
                    title.setText(post.getJSONObject("title").getString("rendered"));
                    content.loadData(post.getJSONObject("content").getString("rendered"), "text/html", "UTF-8");
                }
                catch (JSONException ex){
                    Toast.makeText(Post.this, "Error!", Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
                progressDialog.dismiss();
            }
            catch (JSONException ex ){
                ex.printStackTrace();
            }
        }
    }
}
