package com.example.android.fryerapp;
import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Best Buy Demo on 1/25/2017.
 */

public class TimerFragmentPagerAdapter extends FragmentPagerAdapter{
    final int PAGE_COUNT = 2;

    private String tabTitles[] = new String[] { "Fryer Settings", "Menu Item Settings" };
    private Context mContext;

    public TimerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TimerFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
         if (position == 0) {
            return new FryerSettingsFragment();
        } else   {
            return new MenuItemSettingsFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
             if (position == 0) {
            return mContext.getResources().getString(R.string.fryer_timer_settings);

        } else {
            return mContext.getResources().getString(R.string.menu_item_setings);
        }
    }


}
