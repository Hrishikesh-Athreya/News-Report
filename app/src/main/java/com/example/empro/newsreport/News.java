package com.example.empro.newsreport;
import android.graphics.Bitmap;
//The news POJO which is used to map the news response.
public class News {
    private String Title;
    private String Description;
    private String URL;
    private Bitmap Image;
    private String ImageURL;
    private boolean buttonState =false;
    private int NewsID;
    public News() {
    }

    public int getNewsID() {
        return NewsID;
    }

    public void setNewsID(int newsID) {
        NewsID = newsID;
    }

    public boolean getButtonState() {
        return buttonState;
    }

    public void setButtonState(boolean buttonState) {
        this.buttonState = buttonState;
    }

    public News(String Title, String Description , String URL, Bitmap Image,String ImageURL) {
        this.Title=Title;
        this.Description = Description;
        this.URL = URL;
        this.Image = Image;
        this.ImageURL = ImageURL;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getURL(){return URL;}

    public Bitmap getImage(){return Image;}

    public String getImageURL(){
        return ImageURL;
    }
}
