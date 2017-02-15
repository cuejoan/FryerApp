package com.example.android.fryerapp;

import android.widget.Button;

/**
 * Created by yajos on 2/14/17.
 */

public class ButtonVisibility {

    Button mbutton;
    String mbuttonVisibilityKey;
    boolean mbuttonVisibilityDefaultValue;


    public ButtonVisibility(Button button, String buttonVisibilityKey, boolean buttonVisibilityDefaultValue) {
        mbutton = button;
        mbuttonVisibilityKey = buttonVisibilityKey;
        mbuttonVisibilityDefaultValue = buttonVisibilityDefaultValue;
    }
}
