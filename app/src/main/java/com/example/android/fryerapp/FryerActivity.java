package com.example.android.fryerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FryerActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    //
    public static final boolean BUTTONS_DEFAULT_VISIBILITY = true;

    //this variable divides the milliseconds
    private final long interval = 1000;

    Context context = getBaseContext();
    // After a menu item is touched then we say that it is selected.  This variable will help us decided what to do when the menu item is selected
    private boolean menuItemIsSelected = false;

    // This variable is use to store the value in milliseconds that the mTimer will be set
    private int mTimerTime;

    // these variables are for buttons that will hold the information for a menuitem that will be put
    // in the fryer like french fries or fried chicken
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;


    // Fryers are Custom Data type objects that contain 3 Zone Data type objects inside, and each
    // Zone object contains 3 boolean values, mIsPaused, mIsRunning, mIsStop.
    private Fryer mFryer1;
    private Fryer mFryer2;
    private Fryer mFryer3;
    private Fryer mFryer4;

    private SharedPreferences mSharedPreferences;


    private LinearLayout mFryerLayout2;
    private LinearLayout mFryerLayout3;
    private LinearLayout mFryerLayout4;


    private int[] mButtonTime = {0, 0, 0, 0, 0, 0, 0, 0, 0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);


        mFryerLayout2 = (LinearLayout) findViewById(R.id.fryer2_layout);
        mFryerLayout3 = (LinearLayout) findViewById(R.id.fryer3_layout);
        mFryerLayout4 = (LinearLayout) findViewById(R.id.fryer4_layout);


        // Create a sharedPreference object and register a ChangeListener
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);


        // numOfFryer is meant to store the number of fryers
        String numOfFryer = mSharedPreferences.getString(getString(R.string.number_of_fryers_key),
                getString(R.string.number_of_fryers_default_value));

        int numberOfFryer = Integer.parseInt(numOfFryer);
        if (numberOfFryer >= 1) {

            mFryer1 = new Fryer();

            TextView mFryer1ZoneAText = (TextView) findViewById(R.id.text_fryer1_zoneA);
            mFryer1ZoneAText.setOnClickListener(this);
            mFryer1ZoneAText.setOnLongClickListener(this);

            TextView mFryer1ZoneBText = (TextView) findViewById(R.id.text_fryer1_zoneB);
            mFryer1ZoneBText.setOnClickListener(this);
            mFryer1ZoneBText.setOnLongClickListener(this);

            TextView mFryer1ZoneCText = (TextView) findViewById(R.id.text_fryer1_zoneC);
            mFryer1ZoneCText.setOnClickListener(this);
            mFryer1ZoneCText.setOnLongClickListener(this);

            if (numberOfFryer == 1) {
                mFryerLayout2.setVisibility(View.GONE);
                mFryerLayout3.setVisibility(View.GONE);
                mFryerLayout4.setVisibility(View.GONE);
            }
        }
        if (numberOfFryer >= 2){

            mFryer2 = new Fryer();

            TextView mFryer2ZoneAText = (TextView) findViewById(R.id.text_fryer2_zoneA);
            mFryer2ZoneAText.setOnClickListener(this);
            mFryer2ZoneAText.setOnLongClickListener(this);

            TextView mFryer2ZoneBText = (TextView) findViewById(R.id.text_fryer2_zoneB);
            mFryer2ZoneBText.setOnClickListener(this);
            mFryer2ZoneBText.setOnLongClickListener(this);

            TextView mFryer2ZoneCText = (TextView) findViewById(R.id.text_fryer2_zoneC);
            mFryer2ZoneCText.setOnClickListener(this);
            mFryer2ZoneCText.setOnLongClickListener(this);

            if (numberOfFryer == 2) {
                mFryerLayout3.setVisibility(View.GONE);
                mFryerLayout4.setVisibility(View.GONE);
            }
        }
        if (numberOfFryer >= 3) {

            mFryer3 = new Fryer();

            TextView mFryer3ZoneAText = (TextView) findViewById(R.id.text_fryer3_zoneA);
            mFryer3ZoneAText.setOnClickListener(this);
            mFryer3ZoneAText.setOnLongClickListener(this);

            TextView mFryer3ZoneBText = (TextView) findViewById(R.id.text_fryer3_zoneB);
            mFryer3ZoneBText.setOnClickListener(this);
            mFryer3ZoneBText.setOnLongClickListener(this);

            TextView mFryer3ZoneCText = (TextView) findViewById(R.id.text_fryer3_zoneC);
            mFryer3ZoneCText.setOnClickListener(this);
            mFryer3ZoneCText.setOnLongClickListener(this);

            if (numberOfFryer == 3) {
                mFryerLayout4.setVisibility(View.GONE);
            }
        }
        if (numberOfFryer == 4){

            mFryer4 = new Fryer();

            TextView mFryer4ZoneAText = (TextView) findViewById(R.id.text_fryer4_zoneA);
            mFryer4ZoneAText.setOnClickListener(this);
            mFryer4ZoneAText.setOnLongClickListener(this);

            TextView mFryer4ZoneBText = (TextView) findViewById(R.id.text_fryer4_zoneB);
            mFryer4ZoneBText.setOnClickListener(this);
            mFryer4ZoneBText.setOnLongClickListener(this);

            TextView mFryer4ZoneCText = (TextView) findViewById(R.id.text_fryer4_zoneC);
            mFryer4ZoneCText.setOnClickListener(this);
            mFryer4ZoneCText.setOnLongClickListener(this);
        }

        mButton1 = (Button) findViewById(R.id.menuItem1);
        startButton(mButton1, "button1_visibility", BUTTONS_DEFAULT_VISIBILITY, "button1_text", "1", "button1_time");

        mButton2 = (Button) findViewById(R.id.menuItem2);
        startButton(mButton2, "button2_visibility", BUTTONS_DEFAULT_VISIBILITY, "button2_text", "2", "button2_time");

        mButton3 = (Button) findViewById(R.id.menuItem3);
        startButton(mButton3, "button3_visibility", BUTTONS_DEFAULT_VISIBILITY, "button3_text", "3", "button3_time");

        mButton4 = (Button) findViewById(R.id.menuItem4);
        startButton(mButton4, "button4_visibility", BUTTONS_DEFAULT_VISIBILITY, "button4_text", "4", "button4_time");

        mButton5 = (Button) findViewById(R.id.menuItem5);
        startButton(mButton5, "button5_visibility", BUTTONS_DEFAULT_VISIBILITY, "button5_text", "5", "button5_time");

        mButton6 = (Button) findViewById(R.id.menuItem6);
        startButton(mButton6, "button6_visibility", BUTTONS_DEFAULT_VISIBILITY, "button6_text", "6", "button6_time");

        mButton7 = (Button) findViewById(R.id.menuItem7);
        startButton(mButton7, "button7_visibility", BUTTONS_DEFAULT_VISIBILITY, "button7_text", "7", "button7_time");

        mButton8 = (Button) findViewById(R.id.menuItem8);
        startButton(mButton8, "button8_visibility", BUTTONS_DEFAULT_VISIBILITY, "button8_text", "8", "button8_time");
    }

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


    /*
    *
    * Sets all cooking buttons to the original color
    * */
    public void setButtonsToDefaultColor() {
        mButton1.setBackgroundResource(R.drawable.menu_button);
        mButton2.setBackgroundResource(R.drawable.menu_button);
        mButton3.setBackgroundResource(R.drawable.menu_button);
        mButton4.setBackgroundResource(R.drawable.menu_button);
    }


    /**
     * Prompt the user to confirm that they want to change the current cooking time.
     */
    private void showDeleteConfirmationDialog(final CustomCountDownTimer customCountDownTimer) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to change the cooking time?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Yes" buttonLogic, so change the current cooking time.
                customCountDownTimer.setTimeAndStart(mTimerTime, interval);
                customCountDownTimer.start();
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
    * Manage all the logic, to pause, resume, stop, what to show in the TextView that was click.
    *
    * @ zone is the Zone object with the logic boolean variable, mIsPaused, mIsRunning and mIsStop.
    * @ view is the TextView that was being click and will be modified.
    * */
    private void timerAndTextViewLogic(Fryer.Zone zone, View view) {
        // Converting, narrowing or casting a View into a TextView
        TextView textView = (TextView) view;

        // if buttonLogic selected and mTimer not running, start mTimer when click
        if (menuItemIsSelected && !zone.mIsRunning && !zone.mIsPaused) {
            zone.mTimer.setTimeAndStart(mTimerTime, interval, textView);
            setButtonsToDefaultColor();
            menuItemIsSelected = false;
            zone.setVariablesToRunningMode();
        }
        // If running and a not buttonLogic selected, pause when click.
        else if (zone.mIsRunning && !menuItemIsSelected) {
            zone.mTimer.pause();
            zone.setVariablesToPausedMode();
        }
        // if paused and not buttonLogic selected, resume when click
        else if (zone.mIsPaused && !menuItemIsSelected) {
            zone.mTimer.resume();
            zone.setVariablesToRunningMode();
        }
//        // If finished, set text to "Zone A" when click
//        else if (zone.mIsStop) {
//            textView.setText("Zone A");
//        }
        // If running and the user try to change the time, throw a dialog to asking
        // "Are you sure ...."
        else if ((menuItemIsSelected && zone.mIsRunning)
                || (menuItemIsSelected && zone.mIsPaused)) {
            showDeleteConfirmationDialog(zone.mTimer);
            setButtonsToDefaultColor();
            menuItemIsSelected = false;
        }
    }




    /*
    *
    *
    *
    * */
    private void resetCounterAndVariables(Fryer.Zone zone, View v, int stringId) {
        // Converting, narrowing or casting a View into a TextView
        TextView textView = (TextView) v;

        if (zone.mIsRunning || zone.mIsPaused) {
            zone.mTimer.cancel();
            textView.setText(stringId);
            zone.setVariablesToStopMode();
        }
    }


    /*
    *
    *
    * */
    private void buttonLogic(View button, int timerTime) {

        if (!menuItemIsSelected) {
            button.setBackgroundResource(R.drawable.pressed_menu_button);
            Toast.makeText(getApplicationContext(), "The Menu Was Selected",
                    Toast.LENGTH_SHORT).show();
            menuItemIsSelected = true;
            mTimerTime = timerTime;

            //Handle selected state change
        } else {
            menuItemIsSelected = false;
            setButtonsToDefaultColor();
            Toast.makeText(getApplicationContext(), "The Menu Was DeSelected",
                    Toast.LENGTH_SHORT).show();
        }
    }



    /*
    *
    *
    * */
    private void startButton(Button button, String buttonVisibilityKey,
                             boolean buttonVisibilityDefaultValue, String buttonTextKey,
                             String buttonTextDefaultValue, String buttonTimeKey){
        boolean button1Visibility = manageButtonVisibility(button, buttonVisibilityKey,
                BUTTONS_DEFAULT_VISIBILITY);
        if (button1Visibility) {
            setButtonText(button, buttonTextKey, buttonTextDefaultValue);
            button.setOnClickListener(this);
            int position = Integer.parseInt(buttonTextDefaultValue);
            mButtonTime[position] = Integer.parseInt(mSharedPreferences.getString(buttonTimeKey, "15")) * 60000;
        }
    }


    /*
    *
    * */
    private boolean manageButtonVisibility(Button button, String buttonVisibilityKey, boolean buttonVisibilityDefaultValue) {
        boolean buttonVisible = mSharedPreferences.getBoolean(buttonVisibilityKey,
                buttonVisibilityDefaultValue);
        if (buttonVisible) {
            button.setVisibility(View.VISIBLE);
            return true;
        }else {
            button.setVisibility(View.GONE);
            return false;
        }
    }


    /*
    *
    * */
    private void setButtonText(Button button, String buttonTextKey, String buttonTextDefaultValue) {
        String buttonText = mSharedPreferences.getString(buttonTextKey, buttonTextDefaultValue);
        button.setText(buttonText);

    }

    /*
    *
    * */
    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.menuItem1:
                buttonLogic(view, mButtonTime[1]);
                break;
            case R.id.menuItem2:
                buttonLogic(view, mButtonTime[2]);
                break;
            case R.id.menuItem3:
                buttonLogic(view, mButtonTime[3]);
                break;
            case R.id.menuItem4:
                buttonLogic(view, mButtonTime[4]);
                break;
            case R.id.menuItem5:
                buttonLogic(view, mButtonTime[5]);
                break;
            case R.id.menuItem6:
                buttonLogic(view, mButtonTime[6]);
                break;
            case R.id.menuItem7:
                buttonLogic(view, mButtonTime[7]);
                break;
            case R.id.menuItem8:
                buttonLogic(view, mButtonTime[8]);
                break;
            case R.id.text_fryer1_zoneA:
                timerAndTextViewLogic(mFryer1.zoneA, view);
                break;
            case R.id.text_fryer1_zoneB:
                timerAndTextViewLogic(mFryer1.zoneB, view);
                break;
            case R.id.text_fryer1_zoneC:
                timerAndTextViewLogic(mFryer1.zoneC, view);
                break;
            case R.id.text_fryer2_zoneA:
                timerAndTextViewLogic(mFryer2.zoneA, view);
                break;
            case R.id.text_fryer2_zoneB:
                timerAndTextViewLogic(mFryer2.zoneB, view);
                break;
            case R.id.text_fryer2_zoneC:
                timerAndTextViewLogic(mFryer2.zoneC, view);
                break;
            case R.id.text_fryer3_zoneA:
                timerAndTextViewLogic(mFryer3.zoneA, view);
                break;
            case R.id.text_fryer3_zoneB:
                timerAndTextViewLogic(mFryer3.zoneB, view);
                break;
            case R.id.text_fryer3_zoneC:
                timerAndTextViewLogic(mFryer3.zoneC, view);
                break;
            case R.id.text_fryer4_zoneA:
                timerAndTextViewLogic(mFryer4.zoneA, view);
                break;
            case R.id.text_fryer4_zoneB:
                timerAndTextViewLogic(mFryer4.zoneB, view);
                break;
            case R.id.text_fryer4_zoneC:
                timerAndTextViewLogic(mFryer4.zoneC, view);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {

        switch (view.getId()) {
            case R.id.text_fryer1_zoneA:
                resetCounterAndVariables(mFryer1.zoneA, view, R.string.zone_a);
                break;
            case R.id.text_fryer1_zoneB:
                resetCounterAndVariables(mFryer1.zoneB, view, R.string.zone_b);
                break;
            case R.id.text_fryer1_zoneC:
                resetCounterAndVariables(mFryer1.zoneC, view, R.string.zone_c);
                break;
            case R.id.text_fryer2_zoneA:
                resetCounterAndVariables(mFryer2.zoneA, view, R.string.zone_a);
                break;
            case R.id.text_fryer2_zoneB:
                resetCounterAndVariables(mFryer2.zoneB, view, R.string.zone_b);
                break;
            case R.id.text_fryer2_zoneC:
                resetCounterAndVariables(mFryer2.zoneC, view, R.string.zone_c);
                break;
            case R.id.text_fryer3_zoneA:
                resetCounterAndVariables(mFryer3.zoneA, view, R.string.zone_a);
                break;
            case R.id.text_fryer3_zoneB:
                resetCounterAndVariables(mFryer3.zoneB, view, R.string.zone_b);
                break;
            case R.id.text_fryer3_zoneC:
                resetCounterAndVariables(mFryer3.zoneC, view, R.string.zone_c);
                break;
            case R.id.text_fryer4_zoneA:
                resetCounterAndVariables(mFryer4.zoneA, view, R.string.zone_a);
                break;
            case R.id.text_fryer4_zoneB:
                resetCounterAndVariables(mFryer4.zoneB, view, R.string.zone_b);
                break;
            case R.id.text_fryer4_zoneC:
                resetCounterAndVariables(mFryer4.zoneC, view, R.string.zone_c);
                break;
            default:
                break;
        }
        return true;
    }

    /*
    *
    * */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        String numOfFryer = mSharedPreferences.getString(getString(R.string.number_of_fryers_key),
                getString(R.string.number_of_fryers_default_value));
        int numberOfFryer = Integer.parseInt(numOfFryer);

        switch (key) {
            case "button1_visibility":
                manageButtonVisibility(mButton1, "button1_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button2_visibility":
                manageButtonVisibility(mButton2, "button2_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button3_visibility":
                manageButtonVisibility(mButton3, "button3_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button4_visibility":
                manageButtonVisibility(mButton4, "button4_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button5_visibility":
                manageButtonVisibility(mButton5, "button5_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button6_visibility":
                manageButtonVisibility(mButton6, "button6_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button7_visibility":
                manageButtonVisibility(mButton7, "button7_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;
            case "button8_visibility":
                manageButtonVisibility(mButton8, "button8_visibility", BUTTONS_DEFAULT_VISIBILITY);
                break;

            case "button1_text":
                setButtonText(mButton1, "button1_text", "1");
                break;
            case "button2_text":
                setButtonText(mButton2, "button2_text", "2");
                break;
            case "button3_text":
                setButtonText(mButton3, "button3_text", "3");
                break;
            case "button4_text":
                setButtonText(mButton4, "button4_text", "4");
                break;
            case "button5_text":
                setButtonText(mButton1, "button5_text", "5");
                break;
            case "button6_text":
                setButtonText(mButton2, "button6_text", "6");
                break;
            case "button7_text":
                setButtonText(mButton3, "button7_text", "7");
                break;
            case "button8_text":
                setButtonText(mButton4, "button8_text", "8");
                break;

            case "button1_time":
                mButtonTime[1] = Integer.parseInt(mSharedPreferences.getString("button1_time", "15")) * 60000;
                break;
            case "button2_time":
                mButtonTime[2] = Integer.parseInt(mSharedPreferences.getString("button2_time", "15")) * 60000;
                break;
            case "button3_time":
                mButtonTime[3] = Integer.parseInt(mSharedPreferences.getString("button3_time", "15")) * 60000;
                break;
            case "button4_time":
                mButtonTime[4] = Integer.parseInt(mSharedPreferences.getString("button4_time", "15")) * 60000;
                break;
            case "button5_time":
                mButtonTime[5] = Integer.parseInt(mSharedPreferences.getString("button5_time", "15")) * 60000;
                break;
            case "button6_time":
                mButtonTime[6] = Integer.parseInt(mSharedPreferences.getString("button6_time", "15")) * 60000;
                break;
            case "button7_time":
                mButtonTime[7] = Integer.parseInt(mSharedPreferences.getString("button7_time", "15")) * 60000;
                break;
            case "button8_time":
                mButtonTime[8] = Integer.parseInt(mSharedPreferences.getString("button8_time", "15")) * 60000;
                break;

            case "num_fryers":
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
                break;
        }

    }
}







