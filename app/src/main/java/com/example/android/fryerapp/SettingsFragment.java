package com.example.android.fryerapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by yajos on 2/13/17.
 */

public class SettingsFragment extends AppPreferenceFragment  {


    public static final String FRAGMENT_TAG = "my_preference_fragment";

    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.pref_cooking, rootKey);

    }
}
