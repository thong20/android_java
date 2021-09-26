package com.example.motionlayout.Demo03_RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.motionlayout.R;
import com.example.motionlayout.databinding.Demo03EntityNewsBinding;

import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<POJONews> pojoNewsArrayList;
    Context context;

    public CustomAdapter(ArrayList<POJONews> pojoNewsArrayList, Context context) {
        this.pojoNewsArrayList = pojoNewsArrayList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        Demo03EntityNewsBinding binding;

        MyViewHolder(Demo03EntityNewsBinding b){
            super(b.getRoot());
            binding = b;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());

        return new MyViewHolder(Demo03EntityNewsBinding.inflate(li, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        try{

            final URL image_thumb_url = new URL(pojoNewsArrayList.get(position).img_thumb + "");
            Logdln(image_thumb_url +"", 50);

            RequestOptions options1 = new RequestOptions()
                    .centerCrop()
                    // hiện ảnh khi khởi tạo element
                    .placeholder(R.mipmap.ic_launcher_round)
                    // hiện ảnh khi lỗi không thể load ảnh từ internent
                    .error(R.drawable.error_red);
            Glide.with(context).load(image_thumb_url).apply(options1).into(holder.binding.demo03ImgThumb);

            holder.binding.demo03TvNewsSource.setText(pojoNewsArrayList.get(position).news_source +"");
            holder.binding.demo03TvNewsTitle.setText(pojoNewsArrayList.get(position).news_title +"");
            holder.binding.demo03TvNewsDesc.setText(pojoNewsArrayList.get(position).news_desc +"");

            final URL image_source_logo_url = new URL(pojoNewsArrayList.get(position).news_source_logo + "");
            Logdln(image_source_logo_url +"", 66);

            RequestOptions options2 = new RequestOptions()
                    .centerCrop()
                    // hiện ảnh khi khởi tạo element
                    .placeholder(R.mipmap.ic_launcher_round)
                    // hiện ảnh khi lỗi không thể load ảnh từ internent
                    .error(R.drawable.error_red);
            Glide.with(context).load(image_source_logo_url).apply(options2).into(holder.binding.demo03ImgNewsSourceLogo);

            holder.binding.demo03TvNewsCategory.setText(pojoNewsArrayList.get(position).news_category +"");
            holder.binding.demo03TvNewsDate.setText(pojoNewsArrayList.get(position).tv_news_date +"");

            // ===== START - CODE FOR GO TO DETAIL STARTS ====================================
            holder.binding.demo03ImgThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("image_thumb_url", image_thumb_url +"");
                    intent.putExtra("news_source", pojoNewsArrayList.get(position).news_source +"");
                    intent.putExtra("news_title", pojoNewsArrayList.get(position).news_title +"");
                    intent.putExtra("news_desc", pojoNewsArrayList.get(position).news_desc +"");

                    intent.putExtra("image_source_logo_url", image_source_logo_url +"");
                    intent.putExtra("news_category", pojoNewsArrayList.get(position).news_category +"");
                    intent.putExtra("tv_news_date", pojoNewsArrayList.get(position).tv_news_date +"");

                    // Link: https://youtu.be/nzDhNmyQBEw?list=PLqIYzEuLi9Co7lKtNeSxVmFon_pimQnXJ&t=3182
                    // FLAG_ACTIVITY_NO_ANIMATION: Không cần animation khi chuyển sang 1 Activity khác
                    // lúc đó, khi chuyển sang 1 activity khác thì hệ thống chỉ blink
                    // Nếu không khai báo thì khi chuyển sang 1 activity khác thì sẽ xuất hiện anmation
                    // slide effect
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    context.startActivity(intent);
                }
            });
            // ===== END - CODE FOR GO TO DETAIL STARTS ====================================


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return pojoNewsArrayList.size();
    }

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== CustomAdapter.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== CustomAdapter.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== CustomAdapter.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== CustomAdapter.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
