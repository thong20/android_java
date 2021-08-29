package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.Demo01.Main_Demo01;
import com.example.projectandroid.Demo02.Main_Demo02;
import com.example.projectandroid.Demo03.Main_Demo03;
import com.example.projectandroid.Demo04.Main_Demo04;
import com.example.projectandroid.Demo05.Main_Demo05;
import com.example.projectandroid.Demo06.Main_Demo06;
import com.example.projectandroid.Demo07.Main_Demo07;
import com.example.projectandroid.Demo08.Main_Demo08;
import com.example.projectandroid.Demo09.Main_Demo09;
import com.example.projectandroid.Demo10.Main_Demo10;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_01, btn_02, btn_03, btn_04, btn_05,
            btn_06, btn_07, btn_08, btn_09, btn_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        btn_03 = findViewById(R.id.btn_03);
        btn_04 = findViewById(R.id.btn_04);
        btn_05 = findViewById(R.id.btn_05);
        btn_06 = findViewById(R.id.btn_06);
        btn_07 = findViewById(R.id.btn_07);
        btn_08 = findViewById(R.id.btn_08);
        btn_09 = findViewById(R.id.btn_09);
        btn_10 = findViewById(R.id.btn_10);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
        btn_05.setOnClickListener(this);
        btn_06.setOnClickListener(this);
        btn_07.setOnClickListener(this);
        btn_08.setOnClickListener(this);
        btn_09.setOnClickListener(this);
        btn_10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_01){
            startActivity(new Intent(this, Main_Demo01.class));
        }
        if(v.getId() == R.id.btn_02){
            startActivity(new Intent(this, Main_Demo02.class));
        }
        if(v.getId() == R.id.btn_03){
            startActivity(new Intent(this, Main_Demo03.class));
        }
        if(v.getId() == R.id.btn_04){
            startActivity(new Intent(this, Main_Demo04.class));
        }
        if(v.getId() == R.id.btn_05){
            startActivity(new Intent(this, Main_Demo05.class));
        }
        if(v.getId() == R.id.btn_06){
            startActivity(new Intent(this, Main_Demo06.class));
        }
        if(v.getId() == R.id.btn_07){
            startActivity(new Intent(this, Main_Demo07.class));
        }
        if(v.getId() == R.id.btn_08){
            startActivity(new Intent(this, Main_Demo08.class));
        }
        if(v.getId() == R.id.btn_09){
            startActivity(new Intent(this, Main_Demo09.class));
        }
        if(v.getId() == R.id.btn_10){
            startActivity(new Intent(this, Main_Demo10.class));
        }
    }
}