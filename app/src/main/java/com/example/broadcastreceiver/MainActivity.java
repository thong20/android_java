package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.broadcastreceiver.Demo01_Static_Receiver.MainDemo01;
import com.example.broadcastreceiver.Demo02_Dynamic_Receiver.MainDemo02;
import com.example.broadcastreceiver.Demo03_Charge_Battery.MainDemo03;
import com.example.broadcastreceiver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
        binding.btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01: startActivity(new Intent(MainActivity.this, MainDemo01.class)); break;
            case R.id.btn_02: startActivity(new Intent(MainActivity.this, MainDemo02.class)); break;
            case R.id.btn_03: startActivity(new Intent(MainActivity.this, MainDemo03.class)); break;

        }
    }
}