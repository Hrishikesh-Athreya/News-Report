package com.example.empro.newsreport;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QueryUtils {

    private QueryUtils(){}
    public static ArrayList<News> extractArticle(String nURL_String) throws IOException {
        URL url = CreateURL(nURL_String);
        String JSONresponse = CreateHTTP_URL(url);
        int L =-1;
        ArrayList<News> newsArrayList = new ArrayList<>();
        try{
            JSONObject JSONroot = new JSONObject(JSONresponse);
            JSONArray articles = JSONroot.getJSONArray("articles");

            L = articles.length();

            for (int i=0;i<articles.length();i++){
                JSONObject article = articles.getJSONObject(i);
                String NewsTitle = article.getString("title");
                Log.i("News Title:",NewsTitle);
                String NewsDescription = article.getString("description");
                String NewsUrl = article.getString("url");
                String NewsImageUrl = article.getString("urlToImage");
                Bitmap NewsImage=null;
                String ImageURL = NewsImageUrl;
                if (NewsImageUrl != "null") {
                    NewsImage = makeImage(NewsImageUrl);
                }
                News n = new News(NewsTitle,NewsDescription,NewsUrl,NewsImage,ImageURL);
                newsArrayList.add(n);
            }
        } catch (JSONException e) {
            Log.e("Query Utils:","Problem pasing JSON string");
        }
        return newsArrayList;
    }
    public static Bitmap makeImage(String imageURL){
        URL url = null;
        try {
            url = new URL(imageURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("makeImage:","Image URL couldnt be formed");
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("makeImage : ","Error creating bitmap");
        }
        return bmp;
    }
    private static URL CreateURL(String nURL_String){
        URL url = null;
        try {
            url = new URL(nURL_String);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("CREATE URL:","URL couldnt be formed");
            Log.e("CREATE URL URL:",nURL_String);
        }
        return url;
    }
    private static String CreateHTTP_URL(URL url) throws IOException {
        String JsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
        urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setConnectTimeout(15000);
        urlConnection.setReadTimeout(10000);
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        if (urlConnection.getResponseCode()==200){
            inputStream = urlConnection.getInputStream();
            JsonResponse = getJsonResponse(inputStream);
        }else {
            Log.e("Error Response code:",""+urlConnection.getResponseCode());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if ((inputStream!=null)){
                inputStream.close();
            }
        }
        return JsonResponse;
    }
    private static String getJsonResponse(InputStream inputStream) throws IOException{
        StringBuilder builder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line!=null){
            builder.append(line);
            line = bufferedReader.readLine();
        }
    return builder.toString();
    }
}
