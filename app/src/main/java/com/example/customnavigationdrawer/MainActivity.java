package com.example.customnavigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customnavigationdrawer.Demo01.Fragment.FragmentFavorite;
import com.example.customnavigationdrawer.Demo01.Fragment.FragmentHistory;
import com.example.customnavigationdrawer.Demo01.Fragment.FragmentHome;
import com.example.customnavigationdrawer.Demo01.MainDemo01;
import com.example.customnavigationdrawer.Demo02_Right.MainDemo02;
import com.example.customnavigationdrawer.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01: startActivity(new Intent(MainActivity.this, MainDemo01.class)); break;
            case R.id.btn_02: startActivity(new Intent(MainActivity.this, MainDemo02.class)); break;

        }
    }
}