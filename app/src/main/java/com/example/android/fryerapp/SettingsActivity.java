package com.example.android.fryerapp;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Set the content of the activity to use the activity_settings.xml.xml layout file
        setContentView(R.layout.activity_settings);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // TimerFragmentPagerAdapter adapter =
        //     new TimerFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(new TimerFragmentPagerAdapter(getSupportFragmentManager(),
                SettingsActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}