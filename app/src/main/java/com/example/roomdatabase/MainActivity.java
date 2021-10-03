package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.Demo01_Insert_Get_database.Main_Demo01;
import com.example.roomdatabase.Demo02_Check_Update_database.Main_Demo02;
import com.example.roomdatabase.Demo03_Delete_Search_database.Main_Demo03;
import com.example.roomdatabase.Demo04_Migrating_Room_Database.Main_Demo04;
import com.example.roomdatabase.databinding.ActivityMainBinding;

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
        binding.btn04.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01:
                startActivity(new Intent(this, Main_Demo01.class)); break;
            case R.id.btn_02:
                startActivity(new Intent(this, Main_Demo02.class)); break;
            case R.id.btn_03:
                startActivity(new Intent(this, Main_Demo03.class)); break;
            case R.id.btn_04:
                startActivity(new Intent(this, Main_Demo04.class)); break;


        }
    }
}