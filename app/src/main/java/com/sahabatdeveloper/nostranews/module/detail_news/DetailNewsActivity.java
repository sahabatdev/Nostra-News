package com.sahabatdeveloper.nostranews.module.detail_news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sahabatdeveloper.nostranews.R;
import com.sahabatdeveloper.nostranews.model.NewsResponse;

import java.lang.reflect.Type;

public class DetailNewsActivity extends AppCompatActivity {

    private ImageView imgArticle;
    private TextView tvTitle, tvAuthor, tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        getSupportActionBar().setTitle("Detail News");

        imgArticle = findViewById(R.id.img_article);
        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvContent = findViewById(R.id.tv_content);

        if(getIntent().getExtras()!=null){
            NewsResponse.Articles response = dataArticle();

            Glide.with(this).load(response.getUrlToImage()).into(imgArticle);
            tvTitle.setText(response.getTitle());
            tvAuthor.setText(response.getAuthor());
            tvContent.setText(response.getContent());
        }

    }

    private NewsResponse.Articles dataArticle() {
        String article = getIntent().getExtras().getString("article");
        Gson gson = new Gson();
        Type type = new TypeToken<NewsResponse.Articles>() {
        }.getType();
        return gson.fromJson(article, type);
    }
}
