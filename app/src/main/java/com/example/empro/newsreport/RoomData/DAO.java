package com.example.empro.newsreport.RoomData;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
//The dao for the news database
@Dao
public interface DAO {
    @Insert
    long InsertNewsArticle(NewsDataModelClass newsDataModelClass);
    @Query("SELECT * FROM news_table WHERE Newsid =:newsID")
    NewsDataModelClass getNews(int newsID);
    @Query("SELECT * FROM news_table ORDER BY Newsid desc")
    LiveData<List<NewsDataModelClass>> fetchAllNews();
    @Delete
    void deleteNews(NewsDataModelClass newsDataModelClass);
}
