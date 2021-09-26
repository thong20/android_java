package com.example.motionlayout.Demo05_Album_Photo_with_RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.motionlayout.R;
import com.example.motionlayout.databinding.ActivityMainDemo05Binding;

import java.util.ArrayList;

public class Main_Demo05 extends AppCompatActivity {

    ActivityMainDemo05Binding binding;
    ArrayList<POJOPerson> pojoPersonArrayList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDemo05Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pojoPersonArrayList = new ArrayList<>();
        addList();

        CustomAdapter adapter = new CustomAdapter(Main_Demo05.this, pojoPersonArrayList);
        binding.demo05MyRecyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        binding.demo05MyRecyclerView.setLayoutManager(layoutManager);

    }

    private void addList(){
        pojoPersonArrayList.add(new POJOPerson("Person 001", "https://picsum.photos/200?random=1"));
        pojoPersonArrayList.add(new POJOPerson("Person 002", "https://picsum.photos/200?random=2"));
        pojoPersonArrayList.add(new POJOPerson("Person 003", "https://picsum.photos/200?random=3"));
        pojoPersonArrayList.add(new POJOPerson("Person 004", "https://picsum.photos/200?random=4"));
        pojoPersonArrayList.add(new POJOPerson("Person 005", "https://picsum.photos/200?random=5"));
        pojoPersonArrayList.add(new POJOPerson("Person 006", "https://picsum.photos/200?random=6"));
        pojoPersonArrayList.add(new POJOPerson("Person 007", "https://picsum.photos/200?random=7"));
    }
}