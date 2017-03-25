package com.example.android.fryerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class FryerActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = FryerActivity.class.getName();
    //this variable divides the milliseconds
    private static final long INTERVAL = 1000;
    private static final boolean BUTTONS_DEFAULT_VISIBILITY = true;
    private static final int BUTTONS_DEFAULT_TIME = 15;
    private static final String BUTTONS_DEFAULT_NAME = "Chicken";
    private static final int ONE_SECOND_IN_MILLISECONDS = 1000;
    private static final int NO_BUTTON_IS_SELECTED = 100;
    private static final String DEFAULT_NUM_OF_ZONES = "3";
    private static final String DEFAULT_NUM_OF_FRYERS = "4";
    static final int NUMBER_MENU_ITEMS = 8;


    // I am hardcoding the string because if a get the values from resources the switch block show
    // the error "Constants values required", I tried
    //private final String BUTTON1_VISIBILITY_KEY= getString(R.string.number_of_fryers_default_value);


    // Fryers are Custom Data type objects that contain 3 Zone Data type objects inside, and each
    // Zone object contains 3 boolean values, mTimerIsPaused, mTimerIsRunning, mIsStop.
    private Fryer mFryer1;
    private Fryer mFryer2;
    private Fryer mFryer3;
    private Fryer mFryer4;

    private SharedPreferences mPrefs;

    private LinearLayout mFryerLayout2;
    private LinearLayout mFryerLayout3;
    private LinearLayout mFryerLayout4;

    private ArrayList<ButtonValue> mButtonValues = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer);

        mFryerLayout2 = (LinearLayout) findViewById(R.id.fryer2_layout);
        mFryerLayout3 = (LinearLayout) findViewById(R.id.fryer3_layout);
        mFryerLayout4 = (LinearLayout) findViewById(R.id.fryer4_layout);


        // Create a sharedPreference object and register a ChangeListener
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mPrefs.registerOnSharedPreferenceChangeListener(this);



        boolean fryer1 = mPrefs.getBoolean("fryer1_visibility", BUTTONS_DEFAULT_VISIBILITY);

        // todo is it worth no to create the textViews won't be used here?
        // todo Improve this
        if (fryer1) {

            mFryer1 = new Fryer(this);

            mFryer1.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer1_zoneA);
            mFryer1.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer1_zoneA);
            // getString(R.string.zone_X); is added to the mFryer1.zoneX to link it to the Zone
            // and easily use the value depending on the mFryer1.zoneX
            mFryer1.zoneA.mDefaultTextZone = getString(R.string.zone_a);

            mFryer1.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer1_zoneB);
            mFryer1.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer1_zoneB);
            mFryer1.zoneB.mDefaultTextZone = getString(R.string.zone_b);

            mFryer1.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer1_zoneC);
            mFryer1.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer1_zoneC);
            mFryer1.zoneC.mDefaultTextZone = getString(R.string.zone_c);

            setListenersToTextViews(mFryer1);
            manageZonesVisibility(mFryer1, getString(R.string.number_of_zones_fryer1_key));
        }
        if (fryer1){

            mFryer2 = new Fryer(this);

            mFryer2.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer2_zoneA);
            mFryer2.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer2_zoneB);
            mFryer2.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer2_zoneC);

            mFryer2.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer2_zoneA);
            mFryer2.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer2_zoneB);
            mFryer2.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer2_zoneC);

            setListenersToTextViews(mFryer2);
            manageZonesVisibility(mFryer2, getString(R.string.number_of_zones_fryer2_key));
        }
        if (fryer1) {

            mFryer3 = new Fryer(this);

            mFryer3.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer3_zoneA);
            mFryer3.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer3_zoneB);
            mFryer3.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer3_zoneC);

            mFryer3.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer3_zoneA);
            mFryer3.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer3_zoneB);
            mFryer3.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer3_zoneC);

            setListenersToTextViews(mFryer3);
            manageZonesVisibility(mFryer3, getString(R.string.number_of_zones_fryer3_key));
        }
        if (fryer1){

            mFryer4 = new Fryer(this);

            mFryer4.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer4_zoneA);
            mFryer4.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer4_zoneB);
            mFryer4.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer4_zoneC);

            mFryer4.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer4_zoneA);
            mFryer4.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer4_zoneB);
            mFryer4.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer4_zoneC);

            setListenersToTextViews(mFryer4);
            manageZonesVisibility(mFryer4, getString(R.string.number_of_zones_fryer4_key));
        }
        // Set visibilities to Fryer's Layouts depending on the pref's values.
        manageLinearLayoutsVisibility();

        // Save the button and all the Preference keys of the number of Menu Items specified.
        for (int i = 1; i <= NUMBER_MENU_ITEMS; i++) {
            // Create ids of the buttons, e.g R.id.menuItem1
            int resId =  getResources().getIdentifier("menuItem" + i, "id",
                    getPackageName());
            // Save the button and all the Preference keys that belong to that Menu Item.
            mButtonValues.add(new ButtonValue(findViewById(resId), "button_visibility" + i,
                    "menu_item_name" + i, "menu_item_time" + i, "action_visibility" + i,
                    "action_name" + i, "action_time" + i));
        }

        // Starts all buttons in ButtonValues
        for(ButtonValue buttonValue : mButtonValues){
            manageButtonVisibility(buttonValue);
            manageAction(buttonValue);
        }
    }

    /*
    *
    * */
    private void manageAction(ButtonValue buttonValue) {

        // Get button' Action State, on or off (true or false) and save it.
        buttonValue.saveActionState(mPrefs.getBoolean(buttonValue.getActionStateKey(), false));
        if (buttonValue.getActionState()) {
            buttonValue.saveActionName(mPrefs.getString(buttonValue.getActionNameKey(), ""));
            buttonValue.saveActionTime(mPrefs.getInt(buttonValue.getActionTimeKey(), 10000));
        }
    }



    /*
    *
    * */
    private void setListenersToTextViews(Fryer fryer){

        // todo iterate custom object
        fryer.zoneA.mZoneText.setOnClickListener(this);
        fryer.zoneB.mZoneText.setOnClickListener(this);
        fryer.zoneC.mZoneText.setOnClickListener(this);

        fryer.zoneA.mZoneText.setOnLongClickListener(this);
        fryer.zoneB.mZoneText.setOnLongClickListener(this);
        fryer.zoneC.mZoneText.setOnLongClickListener(this);
    }

    /*
    * Manage all the logic, to pause, resume, stop, number_picker_dialog to show in the TextView that was click.
    *
    * @ zone is the Zone object with the logic boolean variable, mTimerIsPaused, mTimerIsRunning and mIsStop.
    * @ view is the TextView that was being click and will be modified.
    * */
    private void timerLogic(Fryer.Zone zone) {
        // todo Improve this
        // Get the button's index was clicked
        int index = indexButtonClicked();

        if (index != NO_BUTTON_IS_SELECTED) {
             // Get the buttonValue object that contains the button just clicked and the relative
             // variables
             ButtonValue buttonValue = mButtonValues.get(index);
             // Timer not running, not Paused, start mTimer when click
             if (!zone.mTimerIsRunning && !zone.mTimerIsPaused) {
                zone.mTimer.setTimeAndStart(buttonValue, zone);
                zone.mSummaryText.setText(buttonValue.getMenuItemName());
                zone.mSummaryText.setVisibility(View.VISIBLE);

                zone.setVariablesToRunningMode();
                resetButton(buttonValue);

            // If running and the user try to change the time, throw a dialog to asking
            // "Are you sure ...."
            } else {
                // Throws a dialog, if confirm
                confirmationDialogAndStartTimer(zone, buttonValue);
                resetButton(buttonValue);
            }
        // If menuItemIsSelected is not selected, proceed
        } else  {
            if (zone.mTimerIsRunning) {
                zone.mTimer.pause();
                zone.setVariablesToPausedMode();

            // if paused and not button selected, resume when click
            } else if (zone.mTimerIsPaused) {
                zone.mTimer.resume();
                zone.setVariablesToRunningMode();
                zone.releaseMediaPlayer();

            } else if (zone.mIsStop) {
                zone.mZoneText.setText(zone.mDefaultTextZone);
                zone.mSummaryText.setVisibility(View.GONE);
                zone.releaseMediaPlayer();
            }
        }
    }

    /*
    *
    *
    * */
    private void buttonLogic(ButtonValue buttonValue) {
        // Finds out if a button  was in state clicked before this new click, if yes store index.
        int index = indexButtonClicked();
        // if a button in state clicked before this new click, proceed.
        if (index != NO_BUTTON_IS_SELECTED) {
            ButtonValue previousClicked = mButtonValues.get(index);
            // If the button in state clicked before is the same as the new one clicked, proceed.
            if (previousClicked == buttonValue) {
                resetButton(previousClicked);

            // If the user clicked a another different button, proceed.
            } else  {
                resetButton(previousClicked);
                buttonValue.getButton().setBackgroundResource(R.drawable.pressed_menu_button);
                buttonValue.saveSelected(true);
            }

        // If a button is clicked and no other was in state clicked, proceed
        } else if (!buttonValue.isSelected()) {
            buttonValue.getButton().setBackgroundResource(R.drawable.pressed_menu_button);
            buttonValue.saveSelected(true);
        }
    }


    /*
    *
    * Make the button visible and gone in the layout
    * It is called when the app is launched and each time a button's preference is changed, to
    * set OnClickListener, text and visibility.
    * */
    // todo should I called al this function or separately
    private void manageButtonVisibility(ButtonValue buttonValue) {
        // Store the true or false the visibility of the button.
        buttonValue.saveVisibility(mPrefs.getBoolean(buttonValue.getVisibilityKey(),
                BUTTONS_DEFAULT_VISIBILITY));

        // If the buttonValue.mVisible is true, proceed.
        if (buttonValue.isVisible()) {
            // Get the button and make it visible in the layout.
            Button button = buttonValue.getButton();
            button.setVisibility(View.VISIBLE);
            //
            buttonValue.saveMenuItemTime(getTime(buttonValue));
            //
            setAndSaveButtonName(buttonValue);
            button.setOnClickListener(this);
        }else {
            // Get the button and make it gone in the layout.
            buttonValue.getButton().setVisibility(View.GONE);
            // Without this line if you click a button, hide it, and show it, it would
            // appear in Selected state, this is very unlikely to happen, but it's a logic bug.
            resetButton(buttonValue);
        }
    }

    /*
    *
    *  It is called when the button's name is change, when the visibility is changed and when the
    *  app is lunch, both last through manageButtonVisibility
    * */
    private void setAndSaveButtonName(ButtonValue buttonValue) {
        // Get the current text of the button.
        String buttonText = mPrefs.getString(buttonValue.getMenuItemNameKey(), BUTTONS_DEFAULT_NAME);

        // Set the text to the Button view
        buttonValue.getButton().setText(buttonText);
        buttonValue.saveMenuItemName(buttonText);
    }

    /*
    *
    * */
    private int getTime(ButtonValue buttonValue) {

        return mPrefs.getInt(buttonValue.getMenuItemTimeKey(), 15)
              * ONE_SECOND_IN_MILLISECONDS;
    }

    /*
    *
    *  Returns the index of the button the was clicked or NO_BUTTON_IS_SELECTED.
    * */
    private int indexButtonClicked() {
        for (int i = 0; i < 8; i++){
            if (mButtonValues.get(i).isSelected()){
                return i;
            }
        }
        return NO_BUTTON_IS_SELECTED;
    }

    /*
    *
    * */
    private void resetCounterAndVariables(Fryer.Zone zone) {

        if (zone.mTimerIsRunning || zone.mTimerIsPaused) {
            zone.mTimer.cancel();
            zone.mZoneText.setText(zone.mDefaultTextZone);
            zone.mSummaryText.setVisibility(View.GONE);

            zone.setVariablesToStopMode();
        }
    }


    /*
    *
    * Sets all cooking buttons to the original color
    * */
    public void resetButton(ButtonValue buttonValue) {
        buttonValue.saveSelected(false);
        buttonValue.getButton().setBackgroundResource(R.drawable.menu_button);
    }

    /**
     * Prompt the user to confirm that they want to change the current cooking time.
     * If
     */
    private void confirmationDialogAndStartTimer(final Fryer.Zone zone,
                                                 final ButtonValue buttonValue) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to change the cooking time?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Yes" buttonLogic, so change the current cooking time.
                zone.mTimer.cancel();
                zone.mTimer.setTimeAndStart(buttonValue, zone);
                zone.mSummaryText.setText(buttonValue.getMenuItemName());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "No" buttonLogic, so dismiss the dialog
                // and continue.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    /*
    *
    * */
    @Override
    public void onClick(View view) {

        // Todo check if can do some improvement here
        int i = view.getId();
        if (i == R.id.menuItem1) {
            buttonLogic(mButtonValues.get(0));

        } else if (i == R.id.menuItem2) {
            buttonLogic(mButtonValues.get(1));

        } else if (i == R.id.menuItem3) {
            buttonLogic(mButtonValues.get(2));

        } else if (i == R.id.menuItem4) {
            buttonLogic(mButtonValues.get(3));

        } else if (i == R.id.menuItem5) {
            buttonLogic(mButtonValues.get(4));

        } else if (i == R.id.menuItem6) {
            buttonLogic(mButtonValues.get(5));

        } else if (i == R.id.menuItem7) {
            buttonLogic(mButtonValues.get(6));

        } else if (i == R.id.menuItem8) {
            buttonLogic(mButtonValues.get(7));


        // Todo find out if would be convenient to iterate here
        } else if (view == mFryer1.zoneA.mZoneText) {
            timerLogic(mFryer1.zoneA);

        } else if (view == mFryer1.zoneB.mZoneText) {
            timerLogic(mFryer1.zoneB);

        } else if (view == mFryer1.zoneC.mZoneText) {
            timerLogic(mFryer1.zoneC);

        } else if (view == mFryer2.zoneA.mZoneText) {
            timerLogic(mFryer2.zoneA);

        } else if (view == mFryer2.zoneB.mZoneText) {
            timerLogic(mFryer2.zoneB);

        } else if (view == mFryer2.zoneC.mZoneText) {
            timerLogic(mFryer2.zoneC);

        } else if (view == mFryer3.zoneA.mZoneText) {
            timerLogic(mFryer3.zoneA);

        } else if (view == mFryer3.zoneB.mZoneText) {
            timerLogic(mFryer3.zoneB);

        } else if (view == mFryer3.zoneC.mZoneText) {
            timerLogic(mFryer3.zoneC);

        } else if (view == mFryer4.zoneA.mZoneText) {
            timerLogic(mFryer4.zoneA);

        } else if (view == mFryer4.zoneB.mZoneText) {
            timerLogic(mFryer4.zoneB);

        } else if (view == mFryer4.zoneC.mZoneText) {
            timerLogic(mFryer4.zoneC);
        }
    }

    @Override
    public boolean onLongClick(View view) {

        if (view == mFryer1.zoneA.mZoneText) {
            resetCounterAndVariables(mFryer1.zoneA);

        } else if (view == mFryer1.zoneB.mZoneText) {
            resetCounterAndVariables(mFryer1.zoneB);

        } else if (view == mFryer1.zoneC.mZoneText) {
            resetCounterAndVariables(mFryer1.zoneC);

        } else if (view == mFryer2.zoneA.mZoneText) {
            resetCounterAndVariables(mFryer2.zoneA);

        } else if (view == mFryer2.zoneB.mZoneText) {
            resetCounterAndVariables(mFryer2.zoneB);

        } else if (view == mFryer2.zoneC.mZoneText) {
            resetCounterAndVariables(mFryer2.zoneC);

        } else if (view == mFryer3.zoneA.mZoneText) {
            resetCounterAndVariables(mFryer3.zoneA);

        } else if (view == mFryer3.zoneB.mZoneText) {
            resetCounterAndVariables(mFryer3.zoneB);

        } else if (view == mFryer3.zoneC.mZoneText) {
            resetCounterAndVariables(mFryer3.zoneC);

        } else if (view == mFryer4.zoneA.mZoneText) {
            resetCounterAndVariables(mFryer4.zoneA);

        } else if (view == mFryer4.zoneB.mZoneText) {
            resetCounterAndVariables(mFryer4.zoneB);

        } else if (view == mFryer4.zoneC.mZoneText) {
            resetCounterAndVariables(mFryer4.zoneC);
        }
        return true;
    }

    /*
    *
    *  It listens for changes in all the Preferences objects
    * */
    // todo decide is change it for Preference.OnChangeListener
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // If any of the SwitchPreferences are switch, check the new visibility of the button and
        // make visible or gone in activity_fryer.xml
        if (key.equals(mButtonValues.get(0).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(0));

        } else if (key.equals(mButtonValues.get(1).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(1));

        } else if (key.equals(mButtonValues.get(2).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(2));


        } else if (key.equals(mButtonValues.get(3).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(3));

        } else if (key.equals(mButtonValues.get(4).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(4));

        } else if (key.equals(mButtonValues.get(5).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(5));

        } else if (key.equals(mButtonValues.get(6).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(6));

        } else if (key.equals(mButtonValues.get(7).getVisibilityKey())) {
            manageButtonVisibility(mButtonValues.get(7));

            // todo maybe update summary name summary here instead of in the in SettingsFragment
            // If any Name EditTextPreferences is modified, set the new text to the appropriate button.
        } else if (key.equals(mButtonValues.get(0).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(0));

        } else if (key.equals(mButtonValues.get(1).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(1));
            Log.i(TAG, "same button");
            Log.i(TAG, mButtonValues.get(1).getActionName());

        } else if (key.equals(mButtonValues.get(2).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(2));

        } else if (key.equals(mButtonValues.get(3).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(3));

        } else if (key.equals(mButtonValues.get(4).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(4));

        } else if (key.equals(mButtonValues.get(5).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(5));

        } else if (key.equals(mButtonValues.get(6).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(6));

        } else if (key.equals(mButtonValues.get(7).getMenuItemNameKey())) {
            setAndSaveButtonName(mButtonValues.get(7));


            // todo maybe update summary time summary here instead of in the in SettingsFragment
            // If any Time EditTextPreferences is modified, set the new text to the appropriate button.
        } else if (key.equals(mButtonValues.get(0).getMenuItemTimeKey())) {
            mButtonValues.get(0).saveMenuItemTime(getTime(mButtonValues.get(0)));

        } else if (key.equals(mButtonValues.get(1).getMenuItemTimeKey())) {
            mButtonValues.get(1).saveMenuItemTime(getTime(mButtonValues.get(1)));

        } else if (key.equals(mButtonValues.get(2).getMenuItemTimeKey())) {
            mButtonValues.get(2).saveMenuItemTime(getTime(mButtonValues.get(2)));

        } else if (key.equals(mButtonValues.get(3).getMenuItemTimeKey())) {
            mButtonValues.get(3).saveMenuItemTime(getTime(mButtonValues.get(3)));

        } else if (key.equals(mButtonValues.get(4).getMenuItemTimeKey())) {
            mButtonValues.get(4).saveMenuItemTime(getTime(mButtonValues.get(4)));

        } else if (key.equals(mButtonValues.get(5).getMenuItemTimeKey())) {
            mButtonValues.get(5).saveMenuItemTime(getTime(mButtonValues.get(5)));

        } else if (key.equals(mButtonValues.get(6).getMenuItemTimeKey())) {
            mButtonValues.get(6).saveMenuItemTime(getTime(mButtonValues.get(6)));

        } else if (key.equals(mButtonValues.get(7).getMenuItemTimeKey())) {
            mButtonValues.get(7).saveMenuItemTime(getTime(mButtonValues.get(7)));


            // Todo if you set a timer, hide the same fryer and show it again, you would see the
            // timer still running, very unlikely to happen, but still a bug.
        } else if (key.equals(getString(R.string.number_of_fryers_key))) {
            manageLinearLayoutsVisibility();

        } else if (key.contains(getString(R.string.number_of_zones_fryer_key))) {
            getZonesFryerAndManage(key);


        } else if (key.equals(mButtonValues.get(0).getActionNameKey())) {
            mButtonValues.get(0).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(1).getActionNameKey())) {
            mButtonValues.get(1).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(2).getActionNameKey())) {
            mButtonValues.get(2).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(3).getActionNameKey())) {
            mButtonValues.get(3).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(4).getActionNameKey())) {
            mButtonValues.get(4).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(5).getActionNameKey())) {
            mButtonValues.get(5).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(6).getActionNameKey())) {
            mButtonValues.get(6).saveActionName(mPrefs.getString(key, ""));

        } else if (key.equals(mButtonValues.get(7).getActionNameKey())) {
            mButtonValues.get(7).saveActionName(mPrefs.getString(key, ""));



        } else if (key.equals((mButtonValues.get(0).getActionStateKey()))) {
            mButtonValues.get(0).saveActionState(mPrefs.getBoolean(key, false));

        } else if (key.equals((mButtonValues.get(1).getActionStateKey()))) {
            mButtonValues.get(1).saveActionState(mPrefs.getBoolean(key, false));

        }  else if (key.equals((mButtonValues.get(2).getActionStateKey()))) {
            mButtonValues.get(2).saveActionState(mPrefs.getBoolean(key, false));

        } else if (key.equals((mButtonValues.get(3).getActionStateKey()))) {
            mButtonValues.get(3).saveActionState(mPrefs.getBoolean(key, false));

        } else if (key.equals((mButtonValues.get(4).getActionStateKey()))) {
            mButtonValues.get(4).saveActionState(mPrefs.getBoolean(key, false));

        } else if (key.equals((mButtonValues.get(5).getActionStateKey()))) {
            mButtonValues.get(5).saveActionState(mPrefs.getBoolean(key, false));

        } else if (key.equals((mButtonValues.get(6).getActionStateKey()))) {
            mButtonValues.get(6).saveActionState(mPrefs.getBoolean(key, false));

        } else if (key.equals((mButtonValues.get(7).getActionStateKey()))) {
            mButtonValues.get(7).saveActionState(mPrefs.getBoolean(key, false));


        } else if (key.equals(mButtonValues.get(0).getActionTimeKey())) {
            mButtonValues.get(0).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(1).getActionTimeKey())) {
            mButtonValues.get(1).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(2).getActionTimeKey())) {
            mButtonValues.get(2).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(3).getActionTimeKey())) {
            mButtonValues.get(3).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(4).getActionTimeKey())) {
            mButtonValues.get(4).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(5).getActionTimeKey())) {
            mButtonValues.get(5).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(6).getActionTimeKey())) {
            mButtonValues.get(6).saveActionTime(mPrefs.getInt(key, 10000));

        } else if (key.equals(mButtonValues.get(7).getActionTimeKey())) {
            mButtonValues.get(7).saveActionTime(mPrefs.getInt(key, 10000));

        }
    }



    /*
    *
    * */
    private void getZonesFryerAndManage(String key) {
        // todo there is way to avoid these ifs, linking the values.
        if (key.equals(getString(R.string.number_of_zones_fryer1_key))) {
            manageZonesVisibility(mFryer1, key);

        } else if (key.equals(getString(R.string.number_of_zones_fryer2_key))) {
            manageZonesVisibility(mFryer2, key);

        } else if (key.equals(getString(R.string.number_of_zones_fryer3_key))) {
            manageZonesVisibility(mFryer3, key);

        } else if (key.equals(getString(R.string.number_of_zones_fryer4_key))) {
            manageZonesVisibility(mFryer4, key);
        }
    }


    /*
    *
    * */
    // todo check all this
    private void manageZonesVisibility(Fryer fryer, String key) {

        int numberOfZones = Integer.parseInt(mPrefs.getString(key, DEFAULT_NUM_OF_ZONES));

        // There is no logic for zone 1 because it is suppose to have at least one zone
        if (numberOfZones == 1) {
            fryer.zoneB.mZoneText.setVisibility(View.GONE);
            fryer.zoneB.mSummaryText.setVisibility(View.GONE);
            if (fryer.zoneB.mTimer != null) {
                resetCounterAndVariables(fryer.zoneB);
            }

            fryer.zoneC.mZoneText.setVisibility(View.GONE);
            fryer.zoneC.mSummaryText.setVisibility(View.GONE);
            if (fryer.zoneC.mTimer != null) {
                resetCounterAndVariables(fryer.zoneC);
            }

        } else if (numberOfZones == 2) {
            fryer.zoneB.mZoneText.setVisibility(View.VISIBLE);
            fryer.zoneB.mZoneText.setText(R.string.zone_b);

            fryer.zoneC.mZoneText.setVisibility(View.GONE);
            fryer.zoneC.mSummaryText.setVisibility(View.GONE);
            if (fryer.zoneC.mTimer != null) {
                // todo repair cancel
                resetCounterAndVariables(fryer.zoneC);
            }

        } else if (numberOfZones == 3) {
            fryer.zoneB.mZoneText.setVisibility(View.VISIBLE);
            fryer.zoneC.mZoneText.setVisibility(View.VISIBLE);
            fryer.zoneB.mZoneText.setText(R.string.zone_b);
            fryer.zoneC.mZoneText.setText(R.string.zone_c);
        }
    }

    /*
    *
    * */
    private void manageLinearLayoutsVisibility() {
        int numberOfFryer = Integer.parseInt(mPrefs.getString(getString(R.string.number_of_fryers_key),
                DEFAULT_NUM_OF_FRYERS));

        if (numberOfFryer == 1) {
            mFryerLayout2.setVisibility(View.GONE);
            mFryerLayout3.setVisibility(View.GONE);
            mFryerLayout4.setVisibility(View.GONE);
        } else if (numberOfFryer == 2) {
            mFryerLayout2.setVisibility(View.VISIBLE);
            mFryerLayout3.setVisibility(View.GONE);
            mFryerLayout4.setVisibility(View.GONE);
        } else if (numberOfFryer == 3) {
            mFryerLayout2.setVisibility(View.VISIBLE);
            mFryerLayout3.setVisibility(View.VISIBLE);
            mFryerLayout4.setVisibility(View.GONE);
        } else if (numberOfFryer == 4) {
            mFryerLayout2.setVisibility(View.VISIBLE);
            mFryerLayout3.setVisibility(View.VISIBLE);
            mFryerLayout4.setVisibility(View.VISIBLE);
        }
    }

    /*
    *
    * For proper lifecycle management in the activity the listener is unregister when the Activity
    * is destroyed
    *
    * https://developer.android.com/guide/topics/ui/settings.html recommends to register and
    * unregister in onResume() and onPaused(), but in this activity make the listener not to work,
    * although I didn't try to fins for because Udacity did it this way, and it is good anyway.
    * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    /*
    *
    * */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /*
    *
    * */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings_icon) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}







