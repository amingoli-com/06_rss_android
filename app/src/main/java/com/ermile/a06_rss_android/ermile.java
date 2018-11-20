package com.ermile.a06_rss_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ermile extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ermile );
        if (getSupportActionBar ( ) != null)
        {
            getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        }





        webview = (WebView) findViewById ( R.id.webview );
        webview.setWebViewClient ( new WebViewClient () );
        webview.loadUrl ("http://www.ermile.com");
    }



    //     menu back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
