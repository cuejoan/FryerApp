package com.example.android.fryerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class FryerActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SettingsActivity.class.getName();
    //this variable divides the milliseconds
    private static final long INTERVAL = 1000;
    private static final boolean BUTTONS_DEFAULT_VISIBILITY = true;
    private static final String BUTTONS_DEFAULT_TIME = "15";
    private static final int ONE_MINUTE_IN_MILLISECONDS = 60000;
    private static final int NO_BUTTON_IS_SELECTED = 100;


    // I am hardcoding the string because if a get the values from resources the switch block show
    // the error "Constants values required", I tried
    //private final String BUTTON1_VISIBILITY_KEY= getString(R.string.number_of_fryers_default_value);


    // Fryers are Custom Data type objects that contain 3 Zone Data type objects inside, and each
    // Zone object contains 3 boolean values, mIsPaused, mIsRunning, mIsStop.
    private Fryer mFryer1;
    private Fryer mFryer2;
    private Fryer mFryer3;
    private Fryer mFryer4;

    private SharedPreferences mSharedPreferences;

    private LinearLayout mFryerLayout1;
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
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);


        // numOfFryer is meant to store the number of fryers
        String numOfFryer = mSharedPreferences.getString(getString(R.string.number_of_fryers_key),
                getString(R.string.number_of_fryers_default_value));

        // todo is it worth no to create the textViews won't be used here?
        // todo Improve this
        int numberOfFryer = Integer.parseInt(numOfFryer);
        if (numberOfFryer >= 1) {

            mFryer1 = new Fryer();

            mFryer1.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer1_zoneA);
            mFryer1.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer1_zoneB);
            mFryer1.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer1_zoneC);

            mFryer1.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer1_zoneA);
            mFryer1.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer1_zoneB);
            mFryer1.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer1_zoneC);

            mFryer1.zoneA.defaultTextZones = getString(R.string.zone_a);
            mFryer1.zoneB.defaultTextZones = getString(R.string.zone_b);
            mFryer1.zoneC.defaultTextZones = getString(R.string.zone_c);

            setUpTextView(mFryer1);
        }
        if (numberOfFryer >= 2){

            mFryer2 = new Fryer();

            mFryer2.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer2_zoneA);
            mFryer2.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer2_zoneB);
            mFryer2.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer2_zoneC);

            mFryer2.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer2_zoneA);
            mFryer2.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer2_zoneB);
            mFryer2.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer2_zoneC);

            mFryer2.zoneA.defaultTextZones = getString(R.string.zone_a);
            mFryer2.zoneB.defaultTextZones = getString(R.string.zone_b);
            mFryer2.zoneC.defaultTextZones = getString(R.string.zone_c);

            setUpTextView(mFryer2);
            mFryerLayout2.setVisibility(View.VISIBLE);
        }
        if (numberOfFryer >= 3) {

            mFryer3 = new Fryer();

            mFryer3.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer3_zoneA);
            mFryer3.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer3_zoneB);
            mFryer3.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer3_zoneC);

            mFryer3.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer3_zoneA);
            mFryer3.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer3_zoneB);
            mFryer3.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer3_zoneC);

            mFryer3.zoneA.defaultTextZones = getString(R.string.zone_a);
            mFryer3.zoneB.defaultTextZones = getString(R.string.zone_b);
            mFryer3.zoneC.defaultTextZones = getString(R.string.zone_c);

            setUpTextView(mFryer3);
            mFryerLayout3.setVisibility(View.VISIBLE);
        }
        if (numberOfFryer == 4){

            mFryer4 = new Fryer();

            mFryer4.zoneA.mZoneText = (TextView) findViewById(R.id.text_fryer4_zoneA);
            mFryer4.zoneB.mZoneText = (TextView) findViewById(R.id.text_fryer4_zoneB);
            mFryer4.zoneC.mZoneText = (TextView) findViewById(R.id.text_fryer4_zoneC);

            mFryer4.zoneA.mSummaryText = (TextView) findViewById(R.id.summ_fryer4_zoneA);
            mFryer4.zoneB.mSummaryText = (TextView) findViewById(R.id.summ_fryer4_zoneB);
            mFryer4.zoneC.mSummaryText = (TextView) findViewById(R.id.summ_fryer4_zoneC);

            mFryer4.zoneA.defaultTextZones = getString(R.string.zone_a);
            mFryer4.zoneB.defaultTextZones = getString(R.string.zone_b);
            mFryer4.zoneC.defaultTextZones = getString(R.string.zone_c);

            setUpTextView(mFryer4);
            mFryerLayout4.setVisibility(View.VISIBLE);
        }


/*        int maxNumButtons = 8;
        for (int i = 0; i < maxNumButtons; i++) {
            mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem1),
                    getString(R.string.button_visibility) + i, getString(R.string.button_time) + i,
                    getString(R.string.button_text + i)));
        }*/

        // TODO iterate id resources, and this to strings.xml
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem1),
                "button1_visibility", "button1_time", "button1_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem2),
                "button2_visibility", "button2_time", "button2_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem3),
                "button3_visibility", "button3_time", "button3_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem4),
                "button4_visibility", "button4_time", "button4_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem5),
                "button5_visibility", "button5_time", "button5_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem6),
                "button6_visibility", "button6_time", "button6_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem7),
                "button7_visibility", "button7_time", "button7_text"));
        mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem8),
                "button8_visibility", "button8_time", "button8_text"));

        // Starts all buttons in ,ButtonValues
        for(int i =0; i < mButtonValues.size(); i++){
            startButton(mButtonValues.get(i));
        }
    }

    /*
    *
    * */
    private void setUpTextView(Fryer fryer){

        fryer.zoneA.mZoneText.setOnClickListener(this);
        fryer.zoneB.mZoneText.setOnClickListener(this);
        fryer.zoneC.mZoneText.setOnClickListener(this);

        fryer.zoneA.mZoneText.setOnLongClickListener(this);
        fryer.zoneB.mZoneText.setOnLongClickListener(this);
        fryer.zoneC.mZoneText.setOnLongClickListener(this);
    }



    /*
    * Manage all the logic, to pause, resume, stop, what to show in the TextView that was click.
    *
    * @ zone is the Zone object with the logic boolean variable, mIsPaused, mIsRunning and mIsStop.
    * @ view is the TextView that was being click and will be modified.
    * */
    private void timerLogic(Fryer.Zone zone) {

        // todo Improve this
        int index = indexButtonClicked();

        if (index != NO_BUTTON_IS_SELECTED) {

            ButtonValue buttonValue = mButtonValues.get(index);
            // if buttonLogic selected and mTimer not running, start mTimer when click
            if (!zone.mIsRunning && !zone.mIsPaused) {
                zone.mTimer.setTimeAndStart(buttonValue.getTime(), INTERVAL, zone.mZoneText);
                zone.mSummaryText.setText(buttonValue.getText());
                zone.mSummaryText.setVisibility(View.VISIBLE);

                buttonValue.saveSelected(false);
                zone.setVariablesToRunningMode();
                resetButton(buttonValue);

                // If running and the user try to change the time, throw a dialog to asking
                // "Are you sure ...."
            } else {
                // Throws a dialog, if confirm
                confirmationDialogAndStartTimer(zone.mTimer, buttonValue, zone.mSummaryText);
                buttonValue.saveSelected(false);
                resetButton(buttonValue);
            }

            // If menuItemIsSelected is not selected, proceed
        } else  {
            // If running and a not buttonLogic selected, pause when click.
            if (zone.mIsRunning) {
                zone.mTimer.pause();
                zone.setVariablesToPausedMode();

                // if paused and not buttonLogic selected, resume when click
            } else if (zone.mIsPaused) {
                zone.mTimer.resume();
                zone.setVariablesToRunningMode();

            } else if (zone.mIsStop) {
                zone.mZoneText.setText(zone.defaultTextZones);
                zone.mSummaryText.setVisibility(View.GONE);
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
    *
    * */
    private void startButton(ButtonValue buttonValue){
        // Store the visibility of the button and make it visible or gone.
        manageButtonVisibility(buttonValue);
        if (buttonValue.isVisible()) {
            buttonValue.saveTime(getTime(buttonValue));
            setAndSaveButtonText(buttonValue);
            buttonValue.getButton().setOnClickListener(this);
        }
    }


    /*
    *
    * Make the button visible and gone in the layout
    * */
    // TODO check if I need to return value, if not change to void
    private boolean manageButtonVisibility(ButtonValue buttonValue) {
        // Store the true or false the visibility of the button.
        buttonValue.saveVisibility(mSharedPreferences.getBoolean(buttonValue.getVisibilityKey(),
                BUTTONS_DEFAULT_VISIBILITY));
        buttonValue.saveText(mSharedPreferences.getString(buttonValue.getTextKey(),
                ""));

        // If the buttonValue.mVisible is true, proceed.
        if (buttonValue.isVisible()) {
            // Get the button and make it visible in the layout.
            Button button = buttonValue.getButton();
            button.setVisibility(View.VISIBLE);
            button.setText(buttonValue.getText());
            return true;
        }else {
            // Get the button and make it gone in the layout.
            buttonValue.getButton().setVisibility(View.GONE);
            // Without this line if you would click a button, hide it, and show it, it would
            // appear in Selected state, this is very unlikely to happen, but it's a logic bug.
            resetButton(buttonValue);

            return false;
        }
    }

    /*
    *
    * */
    private void setAndSaveButtonText(ButtonValue buttonValue) {
        // Get the current text of the button.
        String buttonText = mSharedPreferences.getString(buttonValue.getTextKey(), "");

        // Set the text to the Button view
        buttonValue.getButton().setText(buttonText);
        buttonValue.saveText(buttonText);
    }

    /*
    *
    * */
    private int getTime(ButtonValue buttonValue) {
        return Integer.parseInt(mSharedPreferences.getString(buttonValue.getTimeKey(), BUTTONS_DEFAULT_TIME))
                * ONE_MINUTE_IN_MILLISECONDS;
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

        if (zone.mIsRunning || zone.mIsPaused) {
            zone.mTimer.cancel();
            zone.mZoneText.setText(zone.defaultTextZones);
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
    private void confirmationDialogAndStartTimer(final CustomCountDownTimer customCountDownTimer,
                                                 final ButtonValue buttonValue, final TextView summaryText) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to change the cooking time?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Yes" buttonLogic, so change the current cooking time.
                customCountDownTimer.setTimeAndStart(buttonValue.getTime(), INTERVAL);
                summaryText.setText(buttonValue.getText());
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
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


        String numOfFryer = mSharedPreferences.getString(getString(R.string.number_of_fryers_key),
                getString(R.string.number_of_fryers_default_value));
        int numberOfFryer = Integer.parseInt(numOfFryer);

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


        // If any of the EditTextPreferences are modified, set the new text to the appropriate button
        } else if (key.equals(mButtonValues.get(0).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(0));

        } else if (key.equals(mButtonValues.get(1).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(1));

        } else if (key.equals(mButtonValues.get(2).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(2));

        } else if (key.equals(mButtonValues.get(3).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(3));

        } else if (key.equals(mButtonValues.get(4).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(4));

        } else if (key.equals(mButtonValues.get(5).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(5));

        } else if (key.equals(mButtonValues.get(6).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(6));

        } else if (key.equals(mButtonValues.get(7).getTextKey())) {
            setAndSaveButtonText(mButtonValues.get(7));


        //
        } else if (key.equals(mButtonValues.get(0).getTimeKey())) {
            mButtonValues.get(0).saveTime(getTime(mButtonValues.get(0)));

        } else if (key.equals(mButtonValues.get(1).getTimeKey())) {
            mButtonValues.get(1).saveTime(getTime(mButtonValues.get(1)));

        } else if (key.equals(mButtonValues.get(2).getTimeKey())) {
            mButtonValues.get(2).saveTime(getTime(mButtonValues.get(2)));

        } else if (key.equals(mButtonValues.get(3).getTimeKey())) {
            mButtonValues.get(3).saveTime(getTime(mButtonValues.get(3)));

        } else if (key.equals(mButtonValues.get(4).getTimeKey())) {
            mButtonValues.get(4).saveTime(getTime(mButtonValues.get(4)));

        } else if (key.equals(mButtonValues.get(5).getTimeKey())) {
            mButtonValues.get(5).saveTime(getTime(mButtonValues.get(5)));

        } else if (key.equals(mButtonValues.get(6).getTimeKey())) {
            mButtonValues.get(6).saveTime(getTime(mButtonValues.get(6)));

        }else if (key.equals(mButtonValues.get(7).getTimeKey())) {
            mButtonValues.get(7).saveTime(getTime(mButtonValues.get(7)));


        // Todo if you set a timer, hide the same fryer and show it again, you would see the
        // timer still running, very unlikely to happen, but still a bug.
        } else if (key.equals("num_fryers")) {
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
    }

    /*
    *
    * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
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







