package com.ermile.a06_rss_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_about );

        //     menu back
        if (getSupportActionBar ( ) != null)
        {
            getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        }



    }

    public void ermile_ir (View view){
        startActivity ( new Intent ( this,ermile.class ) );
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
