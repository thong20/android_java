package com.example.motionlayout.Demo07_Positioning_Icons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.motionlayout.databinding.ActivityMainDemo07Binding;


public class Main_Demo07 extends AppCompatActivity {

    ActivityMainDemo07Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDemo07Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}