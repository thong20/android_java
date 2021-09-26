package com.example.motionlayout.Demo03_RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.motionlayout.R;
import com.example.motionlayout.databinding.ActivityDetailBinding;

import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    String image_thumb_url, news_source, news_title, news_desc,
            image_source_logo_url, news_category, tv_news_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        image_thumb_url = getIntent().getExtras().getString("image_thumb_url");
        news_source = getIntent().getExtras().getString("news_source");
        news_title = getIntent().getExtras().getString("news_title");
        news_desc = getIntent().getExtras().getString("news_desc");

        image_source_logo_url = getIntent().getExtras().getString("image_source_logo_url");
        news_category = getIntent().getExtras().getString("news_category");
        tv_news_date = getIntent().getStringExtra("tv_news_date");

        // ===== SET VALUES TO DETAIL ACTIVITY ELEMENTS ==============================
        RequestOptions options1 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.error_red);
        Glide.with(DetailActivity.this).load(image_thumb_url).apply(options1).into(binding.demo03ImgThumb);

        binding.demo03TvNewsSource.setText(news_source +"");
        binding.demo03TvNewsTitle.setText(news_title +"");
        binding.demo03TvNewsDesc.setText(news_desc +"");

        Logdln(image_source_logo_url +"", 39);

        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.error_red);
        Glide.with(DetailActivity.this).load(image_source_logo_url).apply(options2).into(binding.demo03ImgNewsSourceLogo);

        binding.demo03TvNewsCategory.setText(news_category +"");
        binding.demo03TvNewsDate.setText(tv_news_date +"");
        
    }
    
    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== DetailActivity.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== DetailActivity.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== DetailActivity.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== DetailActivity.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}