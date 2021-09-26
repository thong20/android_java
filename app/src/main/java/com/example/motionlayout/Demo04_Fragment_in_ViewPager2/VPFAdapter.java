package com.example.motionlayout.Demo04_Fragment_in_ViewPager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPFAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> mfragmentArrayList;

    public VPFAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> mfragmentArrayList) {
        super(fragmentActivity);
        this.mfragmentArrayList = mfragmentArrayList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // your handle here

        return mfragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        // your handle here

        return mfragmentArrayList.size();
    }
}
