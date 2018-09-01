package com.example.devcom.sportnews.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.devcom.sportnews.utils.ApiService;
import com.example.devcom.sportnews.model.Event;
import com.example.devcom.sportnews.model.EventsList;
import com.example.devcom.sportnews.utils.InternetConnection;
import com.example.devcom.sportnews.R;
import com.example.devcom.sportnews.utils.RetrofitClient;
import com.example.devcom.sportnews.adapter.RvCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlertDialog dialog;

    private List<Event> eventList;
    private RvCategoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);

        String category = getIntent().getExtras().getString("Category");

        eventList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_category);

        if (InternetConnection.checkConnection(getApplicationContext())) {
            dialog = new  SpotsDialog.Builder().setContext(CategoryActivity.this).build();
            dialog.show();

            ApiService api = RetrofitClient.getApiService();

            Call<EventsList> call = api.getCategory(category);
            call.enqueue(new Callback<EventsList>() {
                @Override
                public void onResponse(@NonNull Call<EventsList> call, Response<EventsList> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        eventList = response.body().getEvents();
                        adapter = new RvCategoryAdapter(CategoryActivity.this, eventList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(CategoryActivity.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EventsList> call, Throwable t) {
                    dialog.dismiss();
                }
            });
        } else {
            Toast.makeText(CategoryActivity.this, "No connection",Toast.LENGTH_SHORT).show();
        }


    }
}
