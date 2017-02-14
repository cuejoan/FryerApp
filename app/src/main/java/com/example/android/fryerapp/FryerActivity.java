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


    //this variable divides the milliseconds
    private final long interval = 1000;

    Context context = getBaseContext();
    // After a menu item is touched then we say that it is selected.  This variable will help us decided what to do when the menu item is selected
    private boolean menuItemIsSelected = false;

    // This variable is use to store the value in milliseconds that the mTimer will be set
    private int mTimerTime;

    // these variables are for buttons that will hold the information for a menuitem that will be put
    // in the fryer like french fries or fried chicken
    private Button mChickenButton;
    private Button mCowButton;
    private Button mFriesButton;
    private Button mFishButton;
    private Button mOther1Button;
    private Button mOther2Button;
    private Button mOther3Button;
    private Button mOther4Button;


    // Fryers are Custom Data type objects that contain 3 Zone Data type objects inside, and each
    // Zone object contains 3 boolean values, mIsPaused, mIsRunning, mIsStop.
    private Fryer mFryer1;
    private Fryer mFryer2;
    private Fryer mFryer3;
    private Fryer mFryer4;

    private SharedPreferences mSharedPreferences;


    LinearLayout fryerLayout2;
    LinearLayout fryerLayout3;
    LinearLayout fryerLayout4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String numOfFryer = mSharedPreferences.getString(getString(R.string.number_of_fryers_key),
                getString(R.string.number_of_fryers_default_value));
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);

        fryerLayout2 = (LinearLayout) findViewById(R.id.fryer2_layout);
        fryerLayout3 = (LinearLayout) findViewById(R.id.fryer3_layout);
        fryerLayout4 = (LinearLayout) findViewById(R.id.fryer4_layout);


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
                fryerLayout2.setVisibility(View.GONE);
                fryerLayout3.setVisibility(View.GONE);
                fryerLayout4.setVisibility(View.GONE);
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
                fryerLayout3.setVisibility(View.GONE);
                fryerLayout4.setVisibility(View.GONE);
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
                fryerLayout4.setVisibility(View.GONE);
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


        mChickenButton = (Button) findViewById(R.id.menuItems);
        mChickenButton.setOnClickListener(this);

        mCowButton = (Button) findViewById(R.id.menuItems2);
        mCowButton.setOnClickListener(this);

        mFriesButton = (Button) findViewById(R.id.menuItems3);
        mFriesButton.setOnClickListener(this);

        mFishButton = (Button) findViewById(R.id.menuItems4);
        mFishButton.setOnClickListener(this);
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
        mChickenButton.setBackgroundResource(R.drawable.menu_button);
        mCowButton.setBackgroundResource(R.drawable.menu_button);
        mFriesButton.setBackgroundResource(R.drawable.menu_button);
        mFishButton.setBackgroundResource(R.drawable.menu_button);
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
                    Toast.LENGTH_LONG).show();
            menuItemIsSelected = true;
            mTimerTime = timerTime;

            //Handle selected state change
        } else {
            menuItemIsSelected = false;
            button.setBackgroundResource(R.drawable.menu_button);
            Toast.makeText(getApplicationContext(), "The Menu Was DeSelected",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.menuItems:
                buttonLogic(view, 10000);
                break;
            case R.id.menuItems2:
                buttonLogic(view, 15000);
                break;
            case R.id.menuItems3:
                buttonLogic(view, 20000);
                break;
            case R.id.menuItems4:
                buttonLogic(view, 25000);
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        String numOfFryer = mSharedPreferences.getString(getString(R.string.number_of_fryers_key),
                getString(R.string.number_of_fryers_default_value));
        int numberOfFryer = Integer.parseInt(numOfFryer);

        if (numberOfFryer == 1) {
            fryerLayout2.setVisibility(View.GONE);
            fryerLayout3.setVisibility(View.GONE);
            fryerLayout4.setVisibility(View.GONE);
        }
        if (numberOfFryer == 2) {
            fryerLayout2.setVisibility(View.VISIBLE);
            fryerLayout3.setVisibility(View.GONE);
            fryerLayout4.setVisibility(View.GONE);
        }
        if (numberOfFryer == 3) {
            fryerLayout2.setVisibility(View.VISIBLE);
            fryerLayout3.setVisibility(View.VISIBLE);
            fryerLayout4.setVisibility(View.GONE);
        }
        if (numberOfFryer == 4) {
            fryerLayout2.setVisibility(View.VISIBLE);
            fryerLayout3.setVisibility(View.VISIBLE);
            fryerLayout4.setVisibility(View.VISIBLE);
        }
    }
}







