package com.example.empro.newsreport;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.empro.newsreport.RoomData.NewsDataModelClass;
import com.example.empro.newsreport.RoomData.NewsRoomViewModel;

import java.util.ArrayList;
import java.util.List;

//TODO:Newsviewmodel is being returned as null because it is in bookmarksactivity and not main
//TODO: activity , plan out what activity should come where
public class BookmarksActivity extends AppCompatActivity {
    private NewsAdapter mNewsAdapter;
    private RecyclerView newsview;
    private ArrayList<News> mNews;
    private NewsRoomViewModel newsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        newsview = findViewById(R.id.rvNews2);
        newsViewModel = ViewModelProviders.of(this).get(NewsRoomViewModel.class);
        mNewsAdapter = new NewsAdapter(mNews,this.getApplication());
        newsview.setAdapter(mNewsAdapter);
        newsview.setLayoutManager(new LinearLayoutManager(this));

        final android.arch.lifecycle.Observer<List<NewsDataModelClass>> newsObserver = new android.arch.lifecycle.Observer<List<NewsDataModelClass>>() {

            @Override
            public void onChanged(@Nullable List<NewsDataModelClass> news) {
                if (news!=null)
                mNewsAdapter.setdData(news);
            }
        };
        newsViewModel.getAllNews().observe(this, newsObserver);
        Toolbar toolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmarks.");

    }
}
