package com.sahabatdeveloper.nostranews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @Expose
    @SerializedName("articles")
    private List<Articles> articles;
    @Expose
    @SerializedName("totalResults")
    private int totalResults;
    @Expose
    @SerializedName("status")
    private String status;

    public List<Articles> getArticles() {
        return articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public String getStatus() {
        return status;
    }

    public class Articles {
        @Expose
        @SerializedName("content")
        private String content;
        @Expose
        @SerializedName("publishedAt")
        private String publishedAt;
        @Expose
        @SerializedName("urlToImage")
        private String urlToImage;
        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("author")
        private String author;
        @Expose
        @SerializedName("source")
        private Source source;

        public String getContent() {
            return content;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public String getUrl() {
            return url;
        }

        public String getDescription() {
            return description;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public Source getSource() {
            return source;
        }
    }

    public class Source {
        @Expose
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }
}
