package com.example.android.fryerapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yajos on 3/6/17.
 */

// This class is need for SettingsFragment to be shown, connected to an aml with a fragment tag
// with the attribute name="com.example.android.fryerapp.SettingsFragment"
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
