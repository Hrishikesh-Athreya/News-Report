package com.example.empro.newsreport.RoomData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.empro.newsreport.News;

import java.util.ArrayList;
import java.util.List;
//A viewmodel to perform room database queries and actions.
public class NewsRoomViewModel extends AndroidViewModel {
    private LiveData<List<NewsDataModelClass>> NewsLiveData;
    private NewsRepository mRepository;
    public NewsRoomViewModel(Application application){
        super(application);
        mRepository = new NewsRepository(this.getApplication());
        NewsLiveData = mRepository.getAllNews();
    }
    public LiveData<List<NewsDataModelClass>> getAllNews(){
//        NewsLiveData = mRepository.getAllNews();
        return NewsLiveData;
    }
    public void insertNews(News news){
        if (news!=null){
            mRepository.InsertNews(news);
        }
    }
}

