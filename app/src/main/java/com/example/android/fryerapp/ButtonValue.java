package com.example.android.fryerapp;

import android.widget.Button;

/**
 * Created by yajos on 3/1/17.
 */

class ButtonValue {

    private Button mButton;

    private boolean mVisible;
    private String mVisibilityKey;

    private int mTime;
    private String mTimeKey;
    
    private String mName;
    private String mNameKey;

    private boolean mIsSelected = false;


    ButtonValue(Button button, String buttonVisibilityKey, String buttonTimeKey,
                       String buttonNameKey) {
        mButton = button;
        mVisibilityKey = buttonVisibilityKey;
        mTimeKey = buttonTimeKey;
        mNameKey = buttonNameKey;
    }

    // Getter methods
    /*
    *
    * */
    String getVisibilityKey() {
        return mVisibilityKey;
    }

    /*
    *
    * */
    String getTimeKey() {
        return mTimeKey;
    }

    String getNameKey() {
        return mNameKey;
    }

    /*
    *
    * */
    Button getButton() {
        return mButton;
    }

    /*
    *
    * */
    int getTime() {
        return mTime;
    }

    /*
    *
    * */
    String getText() {
        return mName;
    }

    /*
    *
    * */
    boolean isSelected() {
        return mIsSelected;
    }

    /*
    *
    * */
    boolean isVisible() {
        return mVisible;
    }





    // Setter methods
    /*
    *
    * */
    void saveTime(int time){
       mTime = time;
    }

    /*
    *
    * */
    void saveName(String name) {
        mName = name;
    }

    /*
    *
    * I named this method saveVisibility instead of setVisibility because the method to make the
    * button views is named "setVisibility" and both will be use in the same Java.file,
    * so could be confusing.
    * */
    void saveVisibility(boolean visibility) {
        mVisible =  visibility;
    }

    /*
    *
    * */
    void saveSelected(boolean buttonIsSelected) {
        mIsSelected = buttonIsSelected;
    }




}
