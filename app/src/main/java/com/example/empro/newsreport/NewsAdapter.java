package com.example.empro.newsreport;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.empro.newsreport.RoomData.NewsDataModelClass;
import com.example.empro.newsreport.RoomData.NewsRepository;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News> mNews;
    private News ne;
    private String Surl;
    private Application mApplication;
    private NewsRepository mNewsRepository;
    private static Bitmap bitmap;

    public NewsAdapter(ArrayList<News> news) {
        this.mNews = news;
    }

    public NewsAdapter(ArrayList<News> news, Application application) {
        this.mNews = news;
        this.mApplication = application;
        mNewsRepository = new NewsRepository(mApplication);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView, IDtextview;
        private RoundedImageView thumbnailImageView;
        private ImageButton imgButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnail);
            nameTextView = itemView.findViewById(R.id.title);
            imgButton = itemView.findViewById(R.id.imgbutton);
        }

        public void setDetails(News news) throws IOException {
            nameTextView.setText(news.getTitle());
            Surl = news.getURL();
            if (news.getImage() != null) {
                thumbnailImageView.setImageBitmap(news.getImage());
            }else if (news.getImageURL()!=null){
                Picasso.get().load(news.getImageURL()).into(thumbnailImageView);
            }

        }
    }

    public void setData(ArrayList<News> news) {
        mNews = news;
        notifyDataSetChanged();
    }

    public void setdData(List<NewsDataModelClass> newsDataModelClasses) {
        ArrayList<News> Anews = new ArrayList<>();
//        Log.i("SetDData","Size of ANEWS IS " + newsDataModelClasses.size());
        for (int i = 0; i < newsDataModelClasses.size(); i++) {
            News news = new News();
//            Log.i("SETDDATA", "setdData: TITLE IS "+newsDataModelClasses.get(0).getMTitle());
            news.setTitle(newsDataModelClasses.get(i).getMTitle());
            news.setDescription(newsDataModelClasses.get(i).getMDescription());
            news.setImageURL(newsDataModelClasses.get(i).getMImage());
//            Log.i("SETDDATA", "setdData: BUTTONSTATEIS "+newsDataModelClasses.get(0).getButton_state().toString());
            news.setButtonState(newsDataModelClasses.get(i).getButton_state());
            news.setImageURL(newsDataModelClasses.get(i).getMImage());
            news.setImage(bitmap);
            news.setURL(newsDataModelClasses.get(i).getMURL());
            Anews.add(news);
        }
        this.mNews = Anews;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View NewsView = inflater.inflate(R.layout.cardview_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(NewsView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mNews != null) {
            return mNews.size();
        } else {
            return -1;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder viewHolder, int position) {
        ne = mNews.get(position);
        viewHolder.imgButton.setActivated(ne.getButtonState());
        try {
            viewHolder.setDetails(ne);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Imageview:", "Error setting image");
        }
        viewHolder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news1 = mNews.get(viewHolder.getAdapterPosition());
                news1.setButtonState(!news1.getButtonState());
                viewHolder.imgButton.setActivated(news1.getButtonState());
                if (viewHolder.imgButton.isActivated()) {
                    Log.i("IMG_BUTTON", "" + news1.getTitle());
                    mNewsRepository.InsertNews(news1);
                    Toast.makeText(view.getContext(),"Added to saved",Toast.LENGTH_SHORT).show();
                }if (viewHolder.imgButton.isActivated()==false){
                    mNewsRepository.deleteNews(news1);
                }
            }
        });
        viewHolder.thumbnailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news1 = mNews.get(viewHolder.getAdapterPosition());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(String.valueOf(news1.getURL())));
                view.getContext().startActivity(i);
            }
        });
    }

    public News getNews(int position) {
        News newws = mNews.get(position);
        return newws;
    }
}
