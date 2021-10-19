package com.example.customnavgationdrawerright;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.customnavgationdrawerright.Fragment.FavoriteFragment;
import com.example.customnavgationdrawerright.Fragment.HistoryFragment;
import com.example.customnavgationdrawerright.Fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private final int FRAGMENT_HOME = 0;
    private final int FRAGMENT_FAVORITE = 1;
    private final int FRAGMENT_HISTORY = 2;
    private int currentFragment = FRAGMENT_HOME;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Nhúng Toolbar vào ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.nav_home:
                        if(currentFragment != FRAGMENT_HOME){
                            replaceFragment(new HomeFragment());
                            currentFragment = FRAGMENT_HOME;
                        }
                        break;
                    case R.id.nav_favorite:
                        if(currentFragment != FRAGMENT_FAVORITE){
                            replaceFragment(new FavoriteFragment());
                            currentFragment = FRAGMENT_FAVORITE;
                        }
                        break;
                    case R.id.nav_history:
                        if(currentFragment != FRAGMENT_HISTORY){
                            replaceFragment(new HistoryFragment());
                            currentFragment = FRAGMENT_HISTORY;
                        }
                        break;
                    case R.id.nav_my_profile:
                        Toast.makeText(MainActivity.this, "thong20 - My Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_change_password:
                        Toast.makeText(MainActivity.this, "thong20 - Change Password", Toast.LENGTH_SHORT).show();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }
        });


        replaceFragment(new HomeFragment());
        // Đánh dấu item Home trong Drawer
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }


    // NHÚNG MENU VÀO ACTIONBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
    // BẮT SỰ KIỆN ONCLIK TRÊN ICON ACTIONBAR
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.menu_toolbar:
                // Mở Navigation Drawer
                drawerLayout.openDrawer(GravityCompat.END);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else{
            // Exit app
            super.onBackPressed();
        }
    }
}