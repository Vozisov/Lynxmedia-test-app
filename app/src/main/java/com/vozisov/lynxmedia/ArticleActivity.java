package com.vozisov.lynxmedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vozisov.lynxmedia.model.Article;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleActivity extends AppCompatActivity {

    Article article;
    ScrollView scrollView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        scrollView = findViewById(R.id.scroll);
        scrollView.setVisibility(View.INVISIBLE);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Article> articleCall = api.loadSingleArticle(url);

        articleCall.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (response.isSuccessful()) {
                    article = response.body();
                    setData();
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private void setData() {

        TextView team1 = findViewById(R.id.team1);
        TextView team2 = findViewById(R.id.team2);
        TextView time = findViewById(R.id.time);
        TextView tournament = findViewById(R.id.tournament);
        TextView place = findViewById(R.id.place);

        TextView t1name = findViewById(R.id.t1name);
        TextView t1desc = findViewById(R.id.t1desc);
        TextView t2name = findViewById(R.id.t2name);
        TextView t2desc = findViewById(R.id.t2desc);
        TextView stname = findViewById(R.id.stname);
        TextView stdesc = findViewById(R.id.stdesc);
        TextView prediction = findViewById(R.id.prediction);

        team1.setText(article.getTeam1());
        team2.setText(article.getTeam2());
        time.setText(article.getTime());
        tournament.setText(article.getTournament());
        place.setText(article.getPlace());

        t1name.setText(article.getArticle().get(0).getHeader());
        t1desc.setText(article.getArticle().get(0).getText());

        t2name.setText(article.getArticle().get(1).getHeader());
        t2desc.setText(article.getArticle().get(1).getText());

        stname.setText(article.getArticle().get(2).getHeader());
        stdesc.setText(article.getArticle().get(2).getText());

        prediction.setText(article.getPrediction());

        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
