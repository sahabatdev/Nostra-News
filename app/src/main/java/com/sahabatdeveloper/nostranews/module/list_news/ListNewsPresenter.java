package com.sahabatdeveloper.nostranews.module.list_news;

import android.widget.Toast;

import com.sahabatdeveloper.nostranews.model.NewsResponse;
import com.sahabatdeveloper.nostranews.network.ApiClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNewsPresenter {
    ListNewsView mView;

    public ListNewsPresenter(ListNewsView mView) {
        this.mView = mView;
    }

    public void getHeadlineNews(String apiKey, String country, String category, int pageSize, int page){
        mView.onShowLoading();
        ApiClient.getClient().getHeadlineNews(apiKey, country, category, pageSize, page).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                mView.onHideLoading();
                if(response.isSuccessful()){
                    mView.onSuccessGetNews(response.body());
                }else{
                    JSONObject jObjError = null;
                    try {
                        jObjError = new JSONObject(response.errorBody().string());
                        mView.onShowMessage(jObjError.getString("message"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                mView.onHideLoading();
                mView.onFailureConnection();
            }
        });
    }
}
