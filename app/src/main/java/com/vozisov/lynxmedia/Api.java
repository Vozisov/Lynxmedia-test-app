package com.vozisov.lynxmedia;

import com.vozisov.lynxmedia.model.Article;
import com.vozisov.lynxmedia.model.EventsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by enot on 14.04.18.
 */

public interface Api {

    @GET("list.php")
    Call<EventsList> loadListArticle(@Query("category") String category);

    @GET("post.php")
    Call<Article> loadSingleArticle(@Query("article") String url);
}
