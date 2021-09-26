package com.example.motionlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.motionlayout.Demo01_onSwipe_onClick_on_same_View.Main_Demo01;
import com.example.motionlayout.Demo02_Change_TextSize_with_Motion_Animation_androidx.Main_Demo02;
import com.example.motionlayout.Demo03_RecyclerView.Main_Demo03;
import com.example.motionlayout.Demo04_Fragment_in_ViewPager2.Main_Demo04;
import com.example.motionlayout.Demo05_Album_Photo_with_RecyclerView.Main_Demo05;
import com.example.motionlayout.Demo06_WindMill.Main_Demo06;
import com.example.motionlayout.Demo07_Positioning_Icons.Main_Demo07;
import com.example.motionlayout.Demo08_Scrolling_Image.Main_Demo08;
import com.example.motionlayout.Demo09_Two_Circle.Main_Demo09;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_01 = findViewById(R.id.btn_01);
        Button btn_02 = findViewById(R.id.btn_02);
        Button btn_03 = findViewById(R.id.btn_03);
        Button btn_04 = findViewById(R.id.btn_04);
        Button btn_05 = findViewById(R.id.btn_05);
        Button btn_06 = findViewById(R.id.btn_06);
        Button btn_07 = findViewById(R.id.btn_07);
        Button btn_08 = findViewById(R.id.btn_08);
        Button btn_09 = findViewById(R.id.btn_09);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
        btn_05.setOnClickListener(this);
        btn_06.setOnClickListener(this);
        btn_07.setOnClickListener(this);
        btn_08.setOnClickListener(this);
        btn_09.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01:
                startActivity(new Intent(this, Main_Demo01.class));
                break;
            case R.id.btn_02:
                startActivity(new Intent(this, Main_Demo02.class));
                break;
            case R.id.btn_03:
                startActivity(new Intent(this, Main_Demo03.class));
                break;
            case R.id.btn_04:
                startActivity(new Intent(this, Main_Demo04.class));
                break;
            case R.id.btn_05:
                startActivity(new Intent(this, Main_Demo05.class));
                break;
            case R.id.btn_06:
                startActivity(new Intent(this, Main_Demo06.class));
                break;
            case R.id.btn_07:
                startActivity(new Intent(this, Main_Demo07.class));
                break;
            case R.id.btn_08:
                startActivity(new Intent(this, Main_Demo08.class));
                break;
            case R.id.btn_09:
                startActivity(new Intent(this, Main_Demo09.class));
                break;
        }
    }
}