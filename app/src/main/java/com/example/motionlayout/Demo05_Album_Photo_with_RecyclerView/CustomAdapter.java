package com.example.motionlayout.Demo05_Album_Photo_with_RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.motionlayout.R;
import com.example.motionlayout.databinding.Demo05EntityPersonBinding;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context context;
    ArrayList<POJOPerson> pojoPersonArrayList;

    public CustomAdapter(Context context, ArrayList<POJOPerson> pojoPersonArrayList) {
        this.context = context;
        this.pojoPersonArrayList = pojoPersonArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(Demo05EntityPersonBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.binding.demo05TvName.setText(pojoPersonArrayList.get(position).name +"");

        URL image_url = null;
        try {

            image_url = new URL(pojoPersonArrayList.get(position).photo +"");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("Log.d", image_url +"");


        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.demo05_person_ic)
                .error(R.drawable.error_red);
        // Phải khai báo permission INTERNET trong AndroidManifest.xml
        Glide.with(context).load(image_url).apply(options).into(holder.binding.demo05ImgThumb);
    }

    @Override
    public int getItemCount() {
        return pojoPersonArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Demo05EntityPersonBinding binding;

        public MyViewHolder(Demo05EntityPersonBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
