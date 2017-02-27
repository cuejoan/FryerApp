package com.example.android.fryerapp;

/**
 * Created by yajos on 2/26/17.
 */

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.View;


public abstract class AppPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the default white background in the view so as to avoid transparency
        view.setBackgroundColor(
                ContextCompat.getColor(getContext(), R.color.background_material_light));

    }
}
