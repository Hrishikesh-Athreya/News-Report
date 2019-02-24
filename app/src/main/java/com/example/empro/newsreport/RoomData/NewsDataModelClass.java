package com.example.empro.newsreport.RoomData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "news_table")
public class NewsDataModelClass {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Newsid;
    //@ColumnInfo(name = "news_title")
    private String MTitle;
    //@ColumnInfo(name = "news_description")
    private String MDescription;
    //@ColumnInfo(name = "news_url")
    private String MURL;
    //@ColumnInfo(name = "button_state")
    private Boolean Button_state;
    @Nullable
    private String MImage;

    public NewsDataModelClass() {
    }

    @NonNull
    public int getNewsid() {
        return Newsid;
    }

    public void setNewsid(@NonNull int Newsid) {
        this.Newsid = Newsid;
    }

    public String getMTitle() {
        return MTitle;
    }

    public void setMTitle(String MTitle) {
        this.MTitle = MTitle;
    }

    public String getMDescription() {
        return MDescription;
    }

    public void setMDescription(String MDescription) {
        this.MDescription = MDescription;
    }

    public String getMURL() {
        return MURL;
    }

    public void setMURL(String MURL) {
        this.MURL = MURL;
    }

    public Boolean getButton_state() {
        return Button_state;
    }

    public void setButton_state(Boolean Button_state) {
        this.Button_state = Button_state;
    }

    @Nullable
    public String getMImage() {
        return MImage;
    }

    public void setMImage(@Nullable String MImage) {
        this.MImage = MImage;
    }
}

