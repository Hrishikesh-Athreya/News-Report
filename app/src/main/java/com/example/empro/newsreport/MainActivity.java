package com.example.empro.newsreport;

import android.app.ActionBar;
import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NewsAdapter mNewsAdapter;
    private RecyclerView newsview;
    private ArrayList<News> mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsview = findViewById(R.id.rvNews);
        newsview.setLayoutManager(new LinearLayoutManager(this));
        mNewsAdapter = new NewsAdapter(mNews,this.getApplication());
        newsview.setAdapter(mNewsAdapter);
        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        final android.arch.lifecycle.Observer<ArrayList<News>> newsObserver = new android.arch.lifecycle.Observer<ArrayList<News>>() {

            @Override
            public void onChanged(@Nullable ArrayList<News> news) {
                mNewsAdapter.setData(news);
            }
        };
        newsViewModel.getNews().observe(this, newsObserver);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Report.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(R.id.action_bookmark):
                Intent intent = new Intent(this,BookmarksActivity.class);
                startActivity(intent);
            }
        return super.onOptionsItemSelected(item);
    }
}