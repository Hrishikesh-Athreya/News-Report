package com.example.empro.newsreport.RoomData;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.empro.newsreport.BookmarksActivity;
import com.example.empro.newsreport.MainActivity;
import com.example.empro.newsreport.News;

import java.util.ArrayList;
import java.util.List;

//Holds the current news from the database
public class NewsRepository {
    private static DAO mNewsDAO;
    static LiveData<NewsDataModelClass> newsLiveData = new MutableLiveData<>();
    static LiveData<List<NewsDataModelClass>> newsLiveDataArray = new MutableLiveData<>();
    static NewsDataModelClass newsdata = new NewsDataModelClass();
    static ArrayList<NewsDataModelClass> arrayList = new ArrayList<>();

    public NewsRepository(Application application){
        NewsDatabase newsDatabase = NewsDatabase.getNewsDatabase(application);
        mNewsDAO = newsDatabase.DAO();
        new AsyncTask<Void,Void,LiveData<List<NewsDataModelClass>>>(){
            @Override
            protected void onPostExecute(LiveData<List<NewsDataModelClass>> listLiveData) {
                super.onPostExecute(listLiveData);
                newsLiveDataArray = listLiveData;
            }

            @Override
            protected LiveData<List<NewsDataModelClass>> doInBackground(Void... voids) {
                return  mNewsDAO.fetchAllNews();
            }
        };

    }
    public static void InsertNews(final News news){
        newsdata.setMTitle(news.getTitle());
        newsdata.setMDescription(news.getDescription());
        newsdata.setMURL(news.getURL());
        newsdata.setMImage(news.getImageURL());
        newsdata.setButton_state(news.getButtonState());
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mNewsDAO.InsertNewsArticle(newsdata);
                newsLiveDataArray=mNewsDAO.fetchAllNews();
                return null;
            }

        }.execute();
    }

//    public void deleteNews(final int id) {
//        final LiveData<NewsDataModelClass> newsLiveData= getNews(id);
//        if(newsLiveData != null) {
//            new AsyncTask<Void, Void, Void>() {
//                @Override
//                protected Void doInBackground(Void... voids) {
//
//                    mNewsDAO.deleteNews(newsLiveData.getValue());
//                    return null;
//                }
//            }.execute();
//        }
//    }

    public static void deleteNews(final News news) {
        newsdata.setMTitle(news.getTitle());
        newsdata.setMDescription(news.getDescription());
        newsdata.setMURL(news.getURL());
        newsdata.setMImage(news.getImageURL());
        newsdata.setButton_state(news.getButtonState());
        //arrayList.remove(newsdata);
        //newsLiveDataArray.setValue(arrayList);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mNewsDAO.deleteNews(newsdata);
                newsLiveDataArray = mNewsDAO.fetchAllNews();
                return null;
            }
        }.execute();
    //arrayList.remove(newsdata);
    //newsLiveDataArray
    }

//    public MutableLiveData<NewsDataModelClass> getNews(final int id) {
//
//        new AsyncTask<Void,Void,Void>(){
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                newsLiveData.postValue(mNewsDAO.getNews(id));
//                return null;
//            }
//        };
//        return newsLiveData;
//    }

    public static LiveData<List<NewsDataModelClass>> getAllNews() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                newsLiveDataArray = mNewsDAO.fetchAllNews();
                return null;
            }
        }.execute();
        return newsLiveDataArray;
    }
}
