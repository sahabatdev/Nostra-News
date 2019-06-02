package com.sahabatdeveloper.nostranews.network;

import com.sahabatdeveloper.nostranews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<NewsResponse> getHeadlineNews(@Header("X-Api-Key") String apiKey, @Query("country") String country, @Query("category") String category, @Query("pageSize") int pageSize, @Query("page") int page);
}
