package com.example.devcom.sportnews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.devcom.sportnews.R;


public class MainActivity extends AppCompatActivity {
    public static final String KEY = "Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        String id = (String) view.getTag();
        intent.putExtra("Category", (String) view.getTag());
        startActivity(intent);
    }
}
