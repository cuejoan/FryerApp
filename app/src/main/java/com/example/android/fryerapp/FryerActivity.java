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

import org.w3c.dom.Text;

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


            /*   TextView mFryer1ZoneAText = (TextView) findViewById(R.id.text_fryer1_zoneA);
            mFryer1ZoneAText.setOnClickListener(this);
            mFryer1ZoneAText.setOnLongClickListener(this);

            TextView mFryer1ZoneBText = (TextView) findViewById(R.id.text_fryer1_zoneB);
            mFryer1ZoneBText.setOnClickListener(this);
            mFryer1ZoneBText.setOnLongClickListener(this);

            TextView mFryer1ZoneCText = (TextView) findViewById(R.id.text_fryer1_zoneC);
            mFryer1ZoneCText.setOnClickListener(this);
            mFryer1ZoneCText.setOnLongClickListener(this);*/

           /*
            if (numberOfFryer == 1) {
                mFryerLayout2.setVisibility(View.GONE);
                mFryerLayout3.setVisibility(View.GONE);
                mFryerLayout4.setVisibility(View.GONE);
            }*/
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


/*        int maxNumButtons = 8;
        for (int i = 0; i < maxNumButtons; i++) {
            mButtonValues.add(new ButtonValue((Button) findViewById(R.id.menuItem1),
                    getString(R.string.button_visibility) + i, getString(R.string.button_time) + i,
                    getString(R.string.button_text + i)));
        }*/

        // TODO iterate id resources,
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
    * Sets all cooking buttons to the original color
    * */
    public void setButtonToDefaultColor(ButtonValue buttonValue) {
        buttonValue.getButton().setBackgroundResource(R.drawable.menu_button);
    }

    /*
    * Manage all the logic, to pause, resume, stop, what to show in the TextView that was click.
    *
    * @ zone is the Zone object with the logic boolean variable, mIsPaused, mIsRunning and mIsStop.
    * @ view is the TextView that was being click and will be modified.
    * */
    private void timerAndTextViewLogic(Fryer.Zone zone, View view, String text) {
        // Converting, narrowing or casting a View into a TextView
        TextView textView = (TextView) view;

        // todo Improve this
        int index = indexButtonClicked();



        if (index != NO_BUTTON_IS_SELECTED) {
            Log.i("aaaaaaaa", "aaaaaaaa");

            ButtonValue buttonValue = mButtonValues.get(index);
            // if buttonLogic selected and mTimer not running, start mTimer when click
            if (!zone.mIsRunning && !zone.mIsPaused) {
                Log.i("cccccccc", "ccccccccc");
                zone.mTimer.setTimeAndStart(buttonValue.getTime(), INTERVAL, textView);
                buttonValue.saveSelected(false);
                zone.setVariablesToRunningMode();

                setButtonToDefaultColor(buttonValue);

            }// If running and the user try to change the time, throw a dialog to asking
            // "Are you sure ...."
            else {

                // Throws a dialog, if confirm
                confirmationDialogAndStartTimer(zone.mTimer, buttonValue, textView);
                buttonValue.saveSelected(false);

                setButtonToDefaultColor(buttonValue);
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
               Log.i("dddddddd", "dddddddddd");
               textView.setText(text);
           }
        }
    }


    /*
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
    private void buttonLogic(ButtonValue buttonValue) {
        // Finds out if a button  was in state clicked before this new click, if yes store index.
        int index = indexButtonClicked();
        // if a button in state clicked before this new click, proceed.
        if (index != NO_BUTTON_IS_SELECTED) {
            ButtonValue previousClicked = mButtonValues.get(index);
            // If the button in state clicked before is the same as the new one clicked, proceed.
            if (previousClicked == buttonValue) {
                previousClicked.saveSelected(false);
                setButtonToDefaultColor(previousClicked);

            // If the user clicked a another different button, proceed.
            } else  {
                previousClicked.saveSelected(false);
                setButtonToDefaultColor(previousClicked);
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
        // If the buttonValue.mVisible is true, proceed.
        if (buttonValue.isVisible()) {
            // Get the button and make it visible in the layout.
            buttonValue.getButton().setVisibility(View.VISIBLE);
            return true;
        }else {
            // Get the button and make it gone in the layout.
            buttonValue.getButton().setVisibility(View.GONE);
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

        } else if (i == mFryer1.zoneA.mZoneText.getId()) {
            timerAndTextViewLogic(mFryer1.zoneA);

        } else if (i == mFryer1.zoneB.mZoneText.getId()) {
            timerAndTextViewLogic(mFryer1.zoneB);

        } else if (i == mFryer1.zoneC.mZoneText.getId()) {
            timerAndTextViewLogic(mFryer1.zoneC);

        } else if (i == R.id.text_fryer2_zoneA) {
            timerAndTextViewLogic(mFryer2.zoneA, view, getString(R.string.zone_a));

        } else if (i == R.id.text_fryer2_zoneB) {
            timerAndTextViewLogic(mFryer2.zoneB, view, getString(R.string.zone_b));

        } else if (i == R.id.text_fryer2_zoneC) {
            timerAndTextViewLogic(mFryer2.zoneC, view, getString(R.string.zone_c));

        } else if (i == R.id.text_fryer3_zoneA) {
            timerAndTextViewLogic(mFryer3.zoneA, view, getString(R.string.zone_a));

        } else if (i == R.id.text_fryer3_zoneB) {
            timerAndTextViewLogic(mFryer3.zoneB, view, getString(R.string.zone_b));

        } else if (i == R.id.text_fryer3_zoneC) {
            timerAndTextViewLogic(mFryer3.zoneC, view, getString(R.string.zone_c));

        } else if (i == R.id.text_fryer4_zoneA) {
            timerAndTextViewLogic(mFryer4.zoneA, view, getString(R.string.zone_a));

        } else if (i == R.id.text_fryer4_zoneB) {
            timerAndTextViewLogic(mFryer4.zoneB, view, getString(R.string.zone_b));

        } else if (i == R.id.text_fryer4_zoneC) {
            timerAndTextViewLogic(mFryer4.zoneC, view, getString(R.string.zone_c));

        } else {
        }
    }

    @Override
    public boolean onLongClick(View view) {

        switch (view.getId()) {
            case R.id.text_fryer1_zoneA:
                resetCounterAndVariables(mFryer1.zoneA, view, R.id.summ_fryer1_zoneA, R.string.zone_a);
                break;
            case R.id.text_fryer1_zoneB:
                resetCounterAndVariables(mFryer1.zoneB, view, R.id.summ_fryer1_zoneB, R.string.zone_b);
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


        //
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



    private void timerAndTextViewLogic(Fryer.Zone zone) {

        // todo Improve this
        int index = indexButtonClicked();

        if (index != NO_BUTTON_IS_SELECTED) {
            Log.i("aaaaaaaa", "aaaaaaaa");

            ButtonValue buttonValue = mButtonValues.get(index);
            // if buttonLogic selected and mTimer not running, start mTimer when click
            if (!zone.mIsRunning && !zone.mIsPaused) {
                zone.mTimer.setTimeAndStart(buttonValue.getTime(), INTERVAL, zone.mZoneText);
                zone.mSummaryText.setText(buttonValue.getText());
                zone.mSummaryText.setVisibility(View.VISIBLE);


                buttonValue.saveSelected(false);
                zone.setVariablesToRunningMode();

                setButtonToDefaultColor(buttonValue);

            }// If running and the user try to change the time, throw a dialog to asking
            // "Are you sure ...."
            else {
                // Throws a dialog, if confirm
                confirmationDialogAndStartTimer(zone.mTimer, buttonValue, zone.mSummaryText);
                buttonValue.saveSelected(false);

                setButtonToDefaultColor(buttonValue);
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
                Log.i("dddddddd", "dddddddddd");
                zone.mZoneText.setText(zone.defaultTextZones);
                zone.mSummaryText.setVisibility(View.GONE);
            }
        }
    }


    /*
    *
    * */
    private void resetCounterAndVariables(Fryer.Zone zone, View v, int resourceId, int stringId) {
        // Converting, narrowing or casting a View into a TextView
        TextView zoneText = (TextView) v;
        TextView summaryView = (TextView) findViewById(resourceId);


        if (zone.mIsRunning || zone.mIsPaused) {
            zone.mTimer.cancel();
            zoneText.setText(stringId);
            summaryView.setVisibility(View.GONE);

            zone.setVariablesToStopMode();
        }
    }

    private void setUpTextView(Fryer fryer){



        fryer.zoneA.mZoneText.setOnClickListener(this);
        fryer.zoneB.mZoneText.setOnClickListener(this);
        fryer.zoneC.mZoneText.setOnClickListener(this);

        fryer.zoneA.mZoneText.setOnLongClickListener(this);
        fryer.zoneB.mZoneText.setOnLongClickListener(this);
        fryer.zoneC.mZoneText.setOnLongClickListener(this);
    }
}







