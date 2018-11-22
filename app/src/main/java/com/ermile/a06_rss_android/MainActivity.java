package com.ermile.a06_rss_android;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ermile.a06_rss_android.Adapter.FeedAdapter;
import com.ermile.a06_rss_android.Common.HTTPDateHandler;
import com.ermile.a06_rss_android.Modle.RSSObject;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;

    //RSS link

    private final String RSS_link="https://news.amfm.ir/feed/";
    private final String RSS_to_Json_API ="https://api.rss2json.com/v1/api.json?rss_url=";
//    SwipeRefreshLayout sw_ref = findViewById ( R.id.swipelayout );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SwipeRefreshLayout sw_refresh = findViewById(R.id.swipelayout);
        sw_refresh.setOnRefreshListener ( new SwipeRefreshLayout.OnRefreshListener ( ) {
                @Override
                public void onRefresh() {
                    loadRSS ();
                }


        } );



        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("@string/app_name");
        setSupportActionBar (toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();



    }


    private void loadRSS() {
        @SuppressLint("StaticFieldLeak") AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {
            final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
            final SwipeRefreshLayout sw_ref = findViewById ( R.id.swipelayout );


            @Override
            protected void onPreExecute() {
                sw_ref.setRefreshing(true);
//                mDialog.setMessage("صبور باشید..");
//                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDateHandler http =new HTTPDateHandler();
                result = http.GetHTTPDate(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                sw_ref.setRefreshing(false);
//                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        };

        StringBuilder url_get_data =new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh){
            loadRSS ();}
        if (item.getItemId() == R.id.about) {
            startActivity ( new Intent ( this,about.class ) );
        }
        return true;
    }



}
