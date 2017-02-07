package com.example.android.fryerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class FryerTimer extends AppCompatActivity {

    //this variable is for a button that will hold the information for a menuitem that will be put in the frier
    // like french fries or fried chicken
    public Button menuItem;
    //this is the where the text for the countdown timer is displayed for zone1A

    //this shows "Zone A" before the timer is started
    public TextView zoneAText;

    // This view displays Zone B
    public TextView zoneBText;

    // This view displays Zone C
    public TextView zoneCText;

    //this variable holds the start time for the timer
    private final long startTime = 30 * 1000;

    //this variable divides the milliseconds
    private final long interval = 1000;

    //helps define actions in the "if" statement for when the timer is paused
    private boolean timerAIsPaused = false;

    //helps define actions in the "if" statement for when the timer is paused
    private boolean timerBIsPaused = false;

    //helps define actions in the "if" statement for when the timer is paused
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
    public boolean menuItemIsSelected = false;




    // This is an Anonymous object, it means you instantiate the class inline, this way
    // you write less code and the code is more readable, you do the same crete an onClickListener
    CustomCountDownTimer countDownTimerA = new CustomCountDownTimer(startTime, interval) {
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

    CustomCountDownTimer countDownTimerB = new CustomCountDownTimer(startTime, interval) {
        @Override
        public void onTick(long millisUntilFinished) {
            // Converts the millisecond to hh:mm:ss
            String counter = formatTime(millisUntilFinished);
            zoneBText.setText(counter);
        }

        @Override
        public void onFinish() {
            zoneBText.setText("Time's up!");
        }
    };

    CustomCountDownTimer countDownTimerC = new CustomCountDownTimer(startTime, interval) {
        @Override
        public void onTick(long millisUntilFinished) {
            // Converts the millisecond to hh:mm:ss
            String counter = formatTime(millisUntilFinished);
            zoneCText.setText(counter);
        }

        @Override
        public void onFinish() {
            zoneCText.setText("Time's up!");
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);

        //set menu item to be selected
        menuItem = (Button) findViewById(R.id.menuItems);
        //set code to select menu item and change background colors when selected
        menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {

                if (!menuItemIsSelected) {
                    menuItem.setBackgroundResource(R.drawable.pressed_menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was Selected",
                            Toast.LENGTH_LONG).show();
                    menuItemIsSelected = true;

                    //Handle selected state change
                } else {
                    menuItemIsSelected = false;
                    menuItem.setBackgroundResource(R.drawable.menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was DeSelected",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        zoneAText = (TextView) findViewById(R.id.zoneA_text);
        zoneAText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuItemIsSelected) {
                    countDownTimerA.start();
                    menuItem.setBackgroundResource(R.drawable.menu_button);
                    menuItemIsSelected = false;
                    timerAIsRunning = true;
                    timerAIsPaused = false;
                    timerAIsFinished = false;
                }
                else if (timerAIsRunning){
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
            }
        });

        zoneAText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (timerAIsRunning || timerAIsPaused) {
                    countDownTimerA.cancel();
                    zoneAText.setText("Zone A");
                    timerAIsRunning = false;
                    timerAIsPaused = false;
                }
                return true;
            }
        });



        //
        zoneBText = (TextView) findViewById(R.id.zoneB_text);
        zoneBText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuItemIsSelected) {
                    countDownTimerB.start();
                    menuItem.setBackgroundResource(R.drawable.menu_button);
                    menuItemIsSelected = false;
                    timerBIsRunning = true;
                    timerBIsPaused = false;
                }
                else if (timerBIsRunning){
                    Log.i("2222222221", "test");
                    countDownTimerB.pause();
                    timerBIsRunning = false;
                    timerBIsPaused = true;
                }
                else if(timerBIsPaused){
                    Log.i("22222222222", "test");
                    countDownTimerB.resume();
                    timerBIsRunning = true;
                    timerBIsPaused = false;
                }
            }
        });


        //
        zoneCText = (TextView) findViewById(R.id.zoneC_text);
        zoneCText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuItemIsSelected) {
                    countDownTimerC.start();
                    menuItem.setBackgroundResource(R.drawable.menu_button);
                    menuItemIsSelected = false;
                    timerCIsRunning = true;
                    timerCIsPaused = false;
                }
                else if (timerCIsRunning){
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
    * Set the logic variable to the values in
    * */
    public void fryer1ZoneAToOriginalValue() {
        timerAIsPaused = false;
        timerAIsRunning = false;
        timerAIsFinished = true;
    }
}







