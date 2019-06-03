package com.sahabatdeveloper.nostranews.module.list_news;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sahabatdeveloper.nostranews.R;
import com.sahabatdeveloper.nostranews.config.AppConfig;
import com.sahabatdeveloper.nostranews.model.NewsResponse;
import com.sahabatdeveloper.nostranews.module.error.ErrorActivity;

import java.util.ArrayList;
import java.util.List;

public class ListNewsActivity extends AppCompatActivity implements ListNewsView{
    private static final int REQUES_CODE_ERROR = 9;
    private ListNewsPresenter mPresenter;
    private RecyclerView rvNews;
    private Spinner spnCountry;
    private ProgressDialog loading;
    private Button btnNext, btnPrev;
    private TextView tvPages;
    private int pageSize=5, page=1, total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage(getString(R.string.label_loading));
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        mPresenter = new ListNewsPresenter(this);

        rvNews = findViewById(R.id.rv_news);
        spnCountry = findViewById(R.id.spn_country);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        tvPages = findViewById(R.id.tv_pages);

        fillSpinner();

        spnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                page=1;
                total=0;
                mPresenter.getHeadlineNews(AppConfig.API_KEY, AppConfig.codeCountry[i] , AppConfig.CATEGORY_NEWS,pageSize,page);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page>5) {
                    page -= 5;
                    mPresenter.getHeadlineNews(AppConfig.API_KEY, AppConfig.codeCountry[spnCountry.getSelectedItemPosition()], AppConfig.CATEGORY_NEWS, pageSize, page);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page<(total-4)) {
                    page += 5;
                    mPresenter.getHeadlineNews(AppConfig.API_KEY, AppConfig.codeCountry[spnCountry.getSelectedItemPosition()], AppConfig.CATEGORY_NEWS, pageSize, page);
                }
            }
        });
    }

    private void fillSpinner() {
        spnCountry.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,AppConfig.nameCountry));
        spnCountry.setSelection(20);
    }

    @Override
    public void onSuccessGetNews(NewsResponse response) {
        this.total = response.getTotalResults();
        double pages = Math.ceil(total/5);
        double hal = Math.ceil(page/5)+1;
        tvPages.setText("Page "+((int)hal)+" from "+((int)pages)+" pages");
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setHasFixedSize(true);
        rvNews.setAdapter(new NewsAdapter(this,response.getArticles()));
    }

    @Override
    public void onShowMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureConnection() {
        Intent i = new Intent(this, ErrorActivity.class);
        startActivityForResult(i, REQUES_CODE_ERROR);
    }

    @Override
    public void onShowLoading() {
        loading.show();
    }

    @Override
    public void onHideLoading() {
        loading.hide();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            mPresenter.getHeadlineNews(AppConfig.API_KEY, AppConfig.codeCountry[spnCountry.getSelectedItemPosition()], AppConfig.CATEGORY_NEWS, pageSize, page);
        }
    }
}
