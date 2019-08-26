package com.example.empro.newsreport;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

public class NewsViewModel extends ViewModel {
    private static final String NEWSAPI_BASE_URL = "https://newsapi.org/v2/";
    private static final String NEWSAPI_API_KEY = "insert API key here";
    private MutableLiveData<ArrayList<News>> NewsLivedata = new MutableLiveData();
    public NewsViewModel(){
    }
    public MutableLiveData<ArrayList<News>> getNews() {
        Uri baseURI = Uri.parse(NEWSAPI_BASE_URL);
        Uri.Builder builder = baseURI.buildUpon();
        builder.appendPath("top-headlines");
        builder.appendQueryParameter("country","in");
        builder.appendQueryParameter("apiKey",NEWSAPI_API_KEY);
        mGetNews(builder.toString());
        return NewsLivedata;
    }

    @SuppressLint("StaticFieldLeak")
    private void mGetNews(final String mURL) {
         new AsyncTask<Void, Void, ArrayList<News>>() {
            @Override
            protected ArrayList<News> doInBackground(Void... voids) {
                ArrayList<News> news = null;
                if (mURL == null) {
                    return null;
                } else {
                    try {
                        news = QueryUtils.extractArticle(mURL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return news;
            }

             @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);
            NewsLivedata.setValue(news);
        }
    }.execute();
    }
}
