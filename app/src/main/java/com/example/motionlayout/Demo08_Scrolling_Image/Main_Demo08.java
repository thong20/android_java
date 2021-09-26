package com.example.motionlayout.Demo08_Scrolling_Image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.motionlayout.R;
import com.example.motionlayout.databinding.ActivityMainDemo08Binding;

public class Main_Demo08 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo08);

        //Cách 2
        ((MotionLayout)findViewById(R.id.demo08_ml_parent)).transitionToEnd() ;
        // Hoặc:
//        MotionLayout motionLayout = findViewById(R.id.demo08_ml_parent);
//        motionLayout.transitionToEnd();
    }
}