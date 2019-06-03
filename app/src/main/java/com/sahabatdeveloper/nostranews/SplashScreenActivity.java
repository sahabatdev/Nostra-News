package com.sahabatdeveloper.nostranews;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sahabatdeveloper.nostranews.module.list_news.ListNewsActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long TIME_LOADING = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home=new Intent(SplashScreenActivity.this, ListNewsActivity.class);
                startActivity(home);
                finish();
            }
        },TIME_LOADING);

    }
}
