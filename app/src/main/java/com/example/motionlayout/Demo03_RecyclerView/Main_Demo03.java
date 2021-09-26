package com.example.motionlayout.Demo03_RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.motionlayout.databinding.ActivityMainDemo03Binding;

import java.util.ArrayList;

// 43:10
public class Main_Demo03 extends AppCompatActivity {

    ActivityMainDemo03Binding binding;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<POJONews> pojoNewsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDemo03Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        layoutManager = new LinearLayoutManager(this);
        binding.myRecyclerView.setLayoutManager(layoutManager);

        pojoNewsArrayList = new ArrayList<>();
        addList();

        CustomAdapter adapter = new CustomAdapter(pojoNewsArrayList, Main_Demo03.this);

        binding.myRecyclerView.setAdapter(adapter);
    }


    private void addList(){
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=11",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=1",
                "Android Developers",
                "June 11, 2020"
        ));
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=12",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=2",
                "iOS Developers",
                "July 12, 2020"
        ));
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=13",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=3",
                "Web Font End Developers",
                "August 13, 2020"
        ));
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=14",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=4",
                "Android Developers",
                "September 14, 2020"
        ));
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=15",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=5",
                "Server Developers",
                "October 15, 2020"
        ));
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=16",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=6",
                "AI Developers",
                "November 16, 2020"
        ));
        pojoNewsArrayList.add(new POJONews(
                "https://picsum.photos/200?random=17",
                "Blog",
                "What's New in Android Gaming",
                "In March of this year, at the Google for Games Developer Submt, we shared several new tools",
                "https://picsum.photos/200?random=7",
                "Engineer Developers",
                "December 17, 2020"
        ));
    }

}