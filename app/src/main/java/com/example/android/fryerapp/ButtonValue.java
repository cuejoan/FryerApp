package com.example.android.fryerapp;

import android.view.View;
import android.widget.Button;

/**
 * Created by yajos on 3/1/17.
 */

class ButtonValue {

    private Button mButton;

    private boolean mMenuItemVisible;
    private String mMenuItemVisibilityKey;
    private boolean mActionState;
    private String mActionStateKey;

    private String mMenuItemName;
    private String mMenuItemNameKey;
    private String mActionName = "test";
    private String mActionNameKey;

    private int mMenuItemTime;
    private String mMenuItemTimeKey;
    private int mActionTime = 8;
    private String mActionTimeKey;



    private boolean mIsSelected = false;


    ButtonValue(View button, String menuItemVisibilityKey, String menuItemNameKey,
                String menuItemTimeKey, String actionIsActiveKey, String actionNameKey,
                String actionTimeKey) {

        mButton = (Button) button;
        mMenuItemVisibilityKey = menuItemVisibilityKey;
        mMenuItemTimeKey = menuItemTimeKey;
        mMenuItemNameKey = menuItemNameKey;
        mActionStateKey = actionIsActiveKey;
        mActionNameKey = actionNameKey;
        mActionTimeKey = actionTimeKey;
    }

    // Getter methods
    /*
    *
    * */
    String getVisibilityKey() {
        return mMenuItemVisibilityKey;
    }

    /*
    *
    * */
    String getMenuItemTimeKey() {
        return mMenuItemTimeKey;
    }

    /*
    *
    * */
    String getActionTimeKey() {
        return mActionTimeKey;
    }

    /*
    *
    * */
    String getMenuItemNameKey() {
        return mMenuItemNameKey;
    }

    /*
    *
    * */
    String getActionNameKey() {
        return mActionNameKey;
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
    int getMenuItemTime() {
        return mMenuItemTime;
    }

    /*
    *
    * */
    int getActionTime() {  return mActionTime;  }

    /*
    *
    * */
    String getMenuItemName() {
        return mMenuItemName;
    }

    /*
    *
    * */
    String getActionName() {  return mActionName;  }

    /*
    *
    * */
    String getActionStateKey() {
        return mActionStateKey;
    }

    /*
    *
    * */
    boolean getActionState() {
        return mActionState;
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
        return mMenuItemVisible;
    }






    // Setter methods
    /*
    *
    * */
    void saveMenuItemTime(int time){
       mMenuItemTime = time;
    }

    /*
    *
    * */
    void saveMenuItemName(String name) {
        mMenuItemName = name;
    }

    // Setter methods
    /*
    *
    * */
    void saveActionTime(int time){
        mActionTime = time;
    }

    /*
    *
    * */
    void saveActionName(String name) {
        mActionName = name;
    }

    /*
    *
    * I named this method saveVisibility instead of setVisibility because the method to make the
    * button views is named "setVisibility" and both will be use in the same Java.file,
    * so could be confusing.
    * */
    void saveVisibility(boolean visibility) {
        mMenuItemVisible =  visibility;
    }

    /*
    *  Save whether the action is active or not.
    * */
    void saveActionState(boolean actionState) {
        mActionState = actionState;
    }

    /*
    *
    * */
    void saveSelected(boolean buttonIsSelected) {
        mIsSelected = buttonIsSelected;
    }


}
