package com.example.devcom.sportnews.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devcom.sportnews.utils.ApiService;
import com.example.devcom.sportnews.model.Article;
import com.example.devcom.sportnews.model.ArticleList;
import com.example.devcom.sportnews.utils.InternetConnection;
import com.example.devcom.sportnews.R;
import com.example.devcom.sportnews.utils.RetrofitClient;
import com.example.devcom.sportnews.adapter.RvArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    private TextView team1, team2, time, tournament;
    private List<Article> articleList;
    private RvArticleAdapter adapter;
    private RecyclerView rvArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        articleList = new ArrayList<>();
        rvArticle = findViewById(R.id.rv_article);

        team1 = findViewById(R.id.tv_team1);
        team2 = findViewById(R.id.tv_team2);
        time = findViewById(R.id.tv_time);
        tournament = findViewById(R.id.tv_tournament);

        final String article = getIntent().getExtras().getString("article");


        if (InternetConnection.checkConnection(this)) {
            final AlertDialog dialog = new SpotsDialog.Builder().setContext(this).build();
            dialog.show();

            ApiService api = RetrofitClient.getApiService();

            Call<ArticleList> call = api.getArticle(article);
            call.enqueue(new Callback<ArticleList>() {
                @Override
                public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                    dialog.dismiss();

                    if(response.isSuccessful()) {
                        team1.setText(response.body().getTeam1());
                        team2.setText(response.body().getTeam2());
                        time.setText(response.body().getTime());
                        tournament.setText(response.body().getTournament());

                        articleList = response.body().getArticle();
                        adapter = new RvArticleAdapter(ArticleActivity.this, articleList);
                        rvArticle.setLayoutManager(new LinearLayoutManager(ArticleActivity.this));
                        rvArticle.setAdapter(adapter);

                    }else {
                        Toast.makeText(ArticleActivity.this, "error",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArticleList> call, Throwable t) {

                }
            });
        }
    }
}