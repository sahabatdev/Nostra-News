package com.sahabatdeveloper.nostranews.module.list_news;

import com.sahabatdeveloper.nostranews.model.NewsResponse;

public interface ListNewsView {
    void onSuccessGetNews(NewsResponse response);
    void onShowMessage(String message);
    void onFailureConnection();
    void onShowLoading();
    void onHideLoading();
}
