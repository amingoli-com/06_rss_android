package com.ermile.a06_rss_android;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class web_view extends AppCompatActivity {
    private WebView webbb;
    private ProgressBar progressBar;
    Toolbar toolbar_wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_web_view );
        webbb =  findViewById ( R.id.webgoogle );
        progressBar =  findViewById(R.id.progressBar);

        //     menu back
        if (getSupportActionBar ( ) != null)
        {
            getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        }
        //   end menu back


        // get form count adn title
        Bundle getinfo_form = getIntent ().getExtras ();
        if (getinfo_form != null)
        {
            String get_link="";
            String get_Author="";


            if (getinfo_form.containsKey ( "post_link" )){
                get_link = getinfo_form.getString ( "post_link" );
            }
            if (getinfo_form.containsKey ( "post_Author" )){
                get_Author = getinfo_form.getString ( "post_Author" );
            }

            String url = get_link;
            String Author = get_Author;



            webbb.setWebViewClient(new myWebClient());
            webbb.loadUrl(url);

            toolbar_wv = findViewById(R.id.toolbar);
            toolbar_wv.setTitle("نویسنده: " + Author);
            setSupportActionBar (toolbar_wv);
            gone_toolbar();


        }




    }




    public  class myWebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view , String url , Bitmap favicon) {

            super.onPageStarted ( view , url , favicon );
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view , String url) {

            progressBar.setVisibility ( View.VISIBLE );
            view.loadUrl ( url );
            return true;

        }

        @Override
        public void onPageFinished(WebView view , String url) {

            super.onPageFinished ( view , url );

            progressBar.setVisibility ( View.GONE );
        }
    }


    private void gone_toolbar() {
        if (progressBar.getVisibility () == View.GONE) {
            toolbar_wv.setVisibility ( View.GONE );
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


