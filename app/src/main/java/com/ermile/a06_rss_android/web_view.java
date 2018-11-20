package com.ermile.a06_rss_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web_view extends AppCompatActivity {
    private WebView webbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_web_view );

        //     menu back
        if (getSupportActionBar ( ) != null)
        {
            getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        }










        Bundle getinfo_form = getIntent ().getExtras ();
        if (getinfo_form != null)
        {
            String get_link="";


            if (getinfo_form.containsKey ( "post_link" )){
                get_link = getinfo_form.getString ( "post_link" );
            }



            webbb = (WebView) findViewById ( R.id.webgoogle );
            webbb.setWebViewClient ( new WebViewClient () );
            webbb.loadUrl (get_link);


        }




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


