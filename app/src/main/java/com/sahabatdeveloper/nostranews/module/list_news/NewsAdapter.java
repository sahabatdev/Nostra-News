package com.sahabatdeveloper.nostranews.module.list_news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sahabatdeveloper.nostranews.R;
import com.sahabatdeveloper.nostranews.model.NewsResponse;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<NewsResponse.Articles> listArticle;

    public NewsAdapter(Context context, List<NewsResponse.Articles> listArticle) {
        this.context = context;
        this.listArticle = listArticle;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int i) {
        NewsResponse.Articles article = listArticle.get(i);

        holder.tvTitle.setText(article.getTitle());
        holder.tvAuthor.setText(article.getAuthor());
        holder.tvDescription.setText(article.getDescription());
        Glide.with(context).load(article.getUrlToImage()).into(holder.imgArticle);

    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvDescription;
        ImageView imgArticle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgArticle = itemView.findViewById(R.id.img_article);
        }
    }
}
