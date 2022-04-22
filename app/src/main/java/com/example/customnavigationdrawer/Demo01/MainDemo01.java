package com.example.customnavigationdrawer.Demo01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
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
import com.example.customnavigationdrawer.R;
import com.google.android.material.navigation.NavigationView;

public class MainDemo01 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final int FRAGMENT_HOME = 0;
    private final int FRAGMENT_FAVORITE = 1;
    private final int FRAGMENT_HISTORY = 2;
    private int currentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ImageView iv_avatar;
    private TextView tv_name, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo01);

        // THÊM ACTION BAR
        // Chú ý: Toolbar này là của package androidx.*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        View v = navigationView.getHeaderView(0);
        iv_avatar = v.findViewById(R.id.header_iv_avatar);
        tv_name = v.findViewById(R.id.header_tv_name);
        tv_email = v.findViewById(R.id.header_tv_email);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // HANDLE onClick ITEM NavigationDrawer
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        replaceFragment(new FragmentHome());
        // Đánh dấu item Home trong Drawer
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

    }

    private void initView() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                if(currentFragment != FRAGMENT_HOME){
                    replaceFragment(new FragmentHome());
                    currentFragment = FRAGMENT_HOME;
                }
                break;

            case R.id.nav_favorite:
                if(currentFragment != FRAGMENT_FAVORITE){
                    replaceFragment(new FragmentFavorite());
                    currentFragment = FRAGMENT_FAVORITE;
                }
                break;

            case R.id.nav_history:
                if(currentFragment != FRAGMENT_HISTORY){
                    replaceFragment(new FragmentHistory());
                    currentFragment = FRAGMENT_HISTORY;
                }
                break;

            case R.id.nav_my_profile:
                showToast("profile profile");
                break;

            case R.id.nav_change_password:
                showToast("password password");
                break;
        }

        // CLOSE DRAWER AFTER ONCLICK
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }

    // XỬ LÝ KHI NHẤN PHÍM CỨNG "BACK"
    // Vì nếu không xử lý thì khi app đang open Drawer
    // mà người dùng nhấn phím cứng "Back" thì app sẽ thoát thay
    // vì phải đóng Drawer lại
    @Override
    public void onBackPressed() {

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            // Đóng Drawer
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            // Thoát app
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }





    // ====================================================================
    public void showToast( String str ){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}