package com.example.rmarkov.facebooklogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int PAGE_NUM = 2;

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new OwnPagerAdapter(getSupportFragmentManager()));
    }

    class OwnPagerAdapter extends FragmentPagerAdapter {

        public OwnPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return MapFragment.getNewInstance();
                case 1:
                    return CarsFragment.getNewInstance();
                    default:
                        // do nothing
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_NUM;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Map";
                case 1:
                    return "Cars";
                default:
                    // do nothing
            }
            return super.getPageTitle(position);
        }
    }
}
