package com.franspaco.tareaasync;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;

public class PostsView extends AppCompatActivity {

    ListView post_list;
    ProgressDialog progressDialog;
    int[] postIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        final String cat = getIntent().getExtras().getString("cat-id");
        String uri = BackEnd.endpointPosts + "?categories=" + cat;

        post_list = findViewById(R.id.post_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        new GetPostListTask().execute(uri);

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Post.class);
                intent.putExtra("id", String.valueOf(postIds[position]));
                startActivity(intent);
            }
        });

    }

    class GetPostListTask extends GetHTTP{
        @Override
        protected void onPostExecute(String respanse) {
            try {
                JSONArray array = new JSONArray(respanse);
                String[] titles = new String[array.length()];
                postIds = new int[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    try {
                        titles[i] = array.getJSONObject(i).getJSONObject("title").getString("rendered");
                        postIds[i] = array.getJSONObject(i).getInt("id");
                    } catch (JSONException ex) {
                        Log.v("ERROR", "Error parsing element #" + i);
                    }
                }
                post_list.setAdapter(
                        new ArrayAdapter(
                                PostsView.this,
                                android.R.layout.simple_list_item_1,
                                titles
                        )
                );
                progressDialog.dismiss();
            }
            catch (JSONException ex ){
                ex.printStackTrace();
            }
        }
    }
}
