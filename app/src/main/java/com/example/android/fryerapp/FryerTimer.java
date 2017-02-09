package com.example.android.fryerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class FryerTimer extends AppCompatActivity {

    Context context = getBaseContext();

    //this variable is for a button that will hold the information for a menuitem that will be put in the frier
    // like french fries or fried chicken
    public Button mChickenButton;

    //this variable is for a button that will hold the information for a menuitem that will be put in the frier
    // like french fries or fried chicken
    public Button mCowButton;

    //this shows "Zone X" before the timer is started
    public TextView zoneAText;
    public TextView zoneBText;
    public TextView zoneCText;

    //this variable holds the start time for the timer
    private final long startTime = 30 * 1000;

    //this variable divides the milliseconds
    private final long interval = 1000;

    //helps define actions in the "if" statement for when the timer is paused
    private boolean timerAIsPaused = false;
    private boolean timerBIsPaused = false;
    private boolean timerCIsPaused = false;

    // These variables to help define actions for when the timer is finished
    public boolean timerAIsFinished = true;
    public boolean timerBIsFinished = false;
    public boolean timerCIsFinished = false;

    // these variables are to show that the timers are running
    private boolean timerAIsRunning = false;
    private boolean timerBIsRunning = false;
    private boolean timerCIsRunning = false;

    //after a menu item is touched then we say that it is selected.  This variable will help us decided what to do when the menu item is selected
    private boolean menuItemIsSelected = false;

    // This variable is use to store the value in milliseconds that the timer will be set
    private int mTimerTime;

    // This is an Anonymous object, it means you instantiate the class inline, this way
    // you write less code and the code is more readable, you do the same crete an onClickListener
    CustomCountDownTimer countDownTimerA = new CustomCountDownTimer() {
        @Override
        public void onTick(long millisUntilFinished) {
            // Converts the millisecond to hh:mm:ss
            String counter = formatTime(millisUntilFinished);
            zoneAText.setText(counter);
        }

        @Override
        public void onFinish() {
            zoneAText.setText("Time's up!");
            fryer1ZoneAToOriginalValue();
        }
    };

    CustomCountDownTimer countDownTimerB = new CustomCountDownTimer() {
        @Override
        public void onTick(long millisUntilFinished) {
            // Converts the millisecond to hh:mm:ss
            String counter = formatTime(millisUntilFinished);
            zoneBText.setText(counter);
        }

        @Override
        public void onFinish() {
            zoneBText.setText("Time's up!");
            fryer1ZoneBToOriginalValue();
        }
    };

    CustomCountDownTimer countDownTimerC = new CustomCountDownTimer() {
        @Override
        public void onTick(long millisUntilFinished) {
            // Converts the millisecond to hh:mm:ss
            String counter = formatTime(millisUntilFinished);
            zoneCText.setText(counter);
        }

        @Override
        public void onFinish() {
            zoneCText.setText("Time's up!");
            fryer1ZoneCToOriginalValue();
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);

        //set menu item to be selected
        mChickenButton = (Button) findViewById(R.id.menuItems);
        //set code to select menu item and change background colors when selected
        mChickenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {

                if (!menuItemIsSelected) {
                    mChickenButton.setBackgroundResource(R.drawable.pressed_menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was Selected",
                            Toast.LENGTH_LONG).show();
                    menuItemIsSelected = true;
                    mTimerTime = 15000;

                //Handle selected state change
                } else {
                    menuItemIsSelected = false;
                    mChickenButton.setBackgroundResource(R.drawable.menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was DeSelected",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //set menu item to be selected
        mCowButton = (Button) findViewById(R.id.menuItems2);
        //set code to select menu item and change background colors when selected
        mCowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {

                if (!menuItemIsSelected) {
                    mCowButton.setBackgroundResource(R.drawable.pressed_menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was Selected",
                            Toast.LENGTH_LONG).show();
                    menuItemIsSelected = true;
                    mTimerTime = 20000;

                    //Handle selected state change
                } else {
                    menuItemIsSelected = false;
                    mCowButton.setBackgroundResource(R.drawable.menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was DeSelected",
                            Toast.LENGTH_LONG).show();
                }
            }
        });




        zoneAText = (TextView) findViewById(R.id.zoneA_text);
        zoneAText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuItemIsSelected && !timerAIsRunning) {
                    countDownTimerA.setTime(mTimerTime, interval);
                    countDownTimerA.start();
                    setButtonsToDefaultColor();
                    menuItemIsSelected = false;
                    timerAIsRunning = true;
                    timerAIsPaused = false;
                    timerAIsFinished = false;
                }
                else if (timerAIsRunning && !menuItemIsSelected){
                    Log.i("11111111111", "test");
                    countDownTimerA.pause();
                    timerAIsRunning = false;
                    timerAIsPaused = true;
                }
                else if(timerAIsPaused){
                    Log.i("111111111112", "test");
                    countDownTimerA.resume();
                    timerAIsRunning = true;
                    timerAIsPaused = false;
                }
                else if(timerAIsFinished) {
                    Log.i("111111111113", "test");
                    zoneAText.setText("Zone A");
                }
                else if (menuItemIsSelected && timerAIsRunning){
                    showDeleteConfirmationDialog(countDownTimerA);
                    setButtonsToDefaultColor();
                    menuItemIsSelected = false;
                }
            }
        });
        zoneAText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (timerAIsRunning || timerAIsPaused) {
                    countDownTimerA.cancel();
                    zoneAText.setText("Zone A");
                    fryer1ZoneAToOriginalValue();
                }
                return true;
            }
        });



        //
        zoneBText = (TextView) findViewById(R.id.zoneB_text);
        zoneBText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuItemIsSelected && !timerBIsRunning) {
                    countDownTimerB.setTime(mTimerTime, interval);
                    countDownTimerB.start();
                    setButtonsToDefaultColor();
                    mChickenButton.setBackgroundResource(R.drawable.menu_button);
                    menuItemIsSelected = false;
                    timerBIsRunning = true;
                    timerBIsPaused = false;
                } else if (timerBIsRunning && !menuItemIsSelected) {
                    Log.i("2222222221", "test");
                    countDownTimerB.pause();
                    timerBIsRunning = false;
                    timerBIsPaused = true;
                } else if (timerBIsPaused) {
                    Log.i("22222222222", "test");
                    countDownTimerB.resume();
                    timerBIsRunning = true;
                    timerBIsPaused = false;
                }
                else if (timerBIsFinished) {
                    Log.i("22222222223", "test");
                    zoneBText.setText("Zone B");
                }
                else if (menuItemIsSelected && timerBIsRunning){
                    showDeleteConfirmationDialog(countDownTimerB);
                    setButtonsToDefaultColor();
                    menuItemIsSelected = false;
                }
            }
            });
        zoneBText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (timerBIsRunning || timerBIsPaused) {
                    countDownTimerB.cancel();
                    zoneBText.setText("Zone B");
                    timerBIsRunning = false;
                    timerBIsPaused = false;
                }
                return true;
            }
        });


        //
        zoneCText = (TextView) findViewById(R.id.zoneC_text);
        zoneCText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuItemIsSelected && !timerCIsRunning) {
                    countDownTimerC.setTime(mTimerTime, interval);
                    countDownTimerC.start();
                    setButtonsToDefaultColor();
                    mChickenButton.setBackgroundResource(R.drawable.menu_button);
                    menuItemIsSelected = false;
                    timerCIsRunning = true;
                    timerCIsPaused = false;
                }
                else if (timerCIsRunning && !menuItemIsSelected){
                    Log.i("1333333331", "test");
                    countDownTimerC.pause();
                    timerCIsRunning = false;
                    timerCIsPaused = true;
                }
                else if(timerCIsPaused){
                    Log.i("3333333332", "test");
                    countDownTimerC.resume();
                    timerCIsRunning = true;
                    timerCIsPaused = false;
                }
                else if(timerCIsFinished) {
                    Log.i("111111111113", "test");
                    zoneCText.setText("Zone C");
                }
                else if (menuItemIsSelected && timerCIsRunning){
                    showDeleteConfirmationDialog(countDownTimerC);
                    setButtonsToDefaultColor();
                    menuItemIsSelected = false;
                }
            }
        });
        zoneCText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (timerCIsRunning || timerCIsPaused) {
                    countDownTimerC.cancel();
                    zoneCText.setText("Zone C");
                    timerCIsRunning = false;
                    timerCIsPaused = false;
                }
                return true;
            }
        });
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
        //switch (item.getItemId()) {
//            case R.id.settings_icon:
//                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
//                // Create a new intent to open the {@link PhrasesActivity}
        int id = item.getItemId();
        if (id == R.id.settiings_icon) {
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*
    *
    * Convert milliseconds to a String with format hh:mm:ss
    *
    * @param milliseconds
    * */
    public String formatTime(long milliseconds) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
    }




    /*
    *
    * Set the logic variables to the original values
    * */
    public void fryer1ZoneAToOriginalValue() {
        timerAIsPaused = false;
        timerAIsRunning = false;
        timerAIsFinished = true;
    }

    public void fryer1ZoneBToOriginalValue() {
        timerBIsPaused = false;
        timerBIsRunning = false;
        timerBIsFinished = true;
    }

    public void fryer1ZoneCToOriginalValue() {
        timerCIsPaused = false;
        timerCIsRunning = false;
        timerCIsFinished = true;
    }




    /*
    *
    * */
    public void setButtonsToDefaultColor(){
        mChickenButton.setBackgroundResource(R.drawable.menu_button);
        mCowButton.setBackgroundResource(R.drawable.menu_button);
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
                // User clicked the "Yes" button, so change the current cooking time.
                customCountDownTimer.start();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "No" button, so dismiss the dialog
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
}







