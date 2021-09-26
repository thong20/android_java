package com.example.motionlayout.Demo06_WindMill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.motionlayout.databinding.ActivityMainDemo06Binding;

public class Main_Demo06 extends AppCompatActivity {

    ActivityMainDemo06Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDemo06Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}