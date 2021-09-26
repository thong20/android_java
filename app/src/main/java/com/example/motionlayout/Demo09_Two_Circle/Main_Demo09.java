package com.example.motionlayout.Demo09_Two_Circle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.motionlayout.databinding.ActivityMainDemo09Binding;

public class Main_Demo09 extends AppCompatActivity {

    ActivityMainDemo09Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDemo09Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}