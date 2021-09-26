package com.example.motionlayout.Demo04_Fragment_in_ViewPager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.example.motionlayout.R;
import com.example.motionlayout.databinding.ActivityMainDemo04Binding;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

import java.util.ArrayList;

public class Main_Demo04 extends AppCompatActivity {

    ActivityMainDemo04Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDemo04Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Fragment> mfragmentArrayList = new ArrayList<>();
        mfragmentArrayList.add(new RedFragment());
        mfragmentArrayList.add(new GreenFragment());
        mfragmentArrayList.add(new BlueFragment());

        VPFAdapter vpfAdapter = new VPFAdapter(this, mfragmentArrayList);

        binding.demo04Viewpager.setAdapter(vpfAdapter);

        // ===== indicator at below the ViewPager2 ======================================
        binding.demo04IndicatorView
                .setSliderColor(Color.BLACK, Color.WHITE)
                .setSliderWidth(getResources().getDimension(R.dimen.dimen_15dp))
                .setSliderHeight(getResources().getDimension(R.dimen.dimen_5dp))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setupWithViewPager(binding.demo04Viewpager);

    }
}