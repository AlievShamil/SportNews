package com.example.devcom.sportnews.utils;

import com.example.devcom.sportnews.model.ArticleList;
import com.example.devcom.sportnews.model.EventsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/list.php")
    Call<EventsList> getCategory(@Query("category") String category);

    @GET("/post.php")
    Call<ArticleList> getArticle(@Query("article") String article);
}
