package com.example.android.fryerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FryerTimer extends AppCompatActivity {

    // this variable is to show that the timer has started
    private boolean timerHasStarted = false;

    // this variable is to show that the timer B has started
    private boolean timerBIsRunning = false;

    private CountDownTimer countDownTimer;
    //casting views to variables
    public RelativeLayout zoneARelativeLayout;

    //this variable is for a button that will hold the information for a menuitem that will be put in the frier
    // like french fries or fried chicken
    public Button menuItem;
    //this is the where the text for the countdown timer is displayed for zone1A

    // this is where the countdown timer shows the numbers
    public TextView zoneATimerText;

    //this shows "Zone A" before the timer is started
    public TextView zoneAText;

    // This view displays Zone B
    public TextView zoneBText;


    //this variable holds the start time for the timer
    private final long startTime = 30 * 1000;

    //this variable divides the milliseconds
    private final long interval = 1 * 1000;

    //when when we puse the timer with a cancel(); then the timr has to save where it is paused, so that it knows wheree to restart
    //that is done at this variable
    Long s1;

    //helps define actions in the "if" statement for when the timer is paused
    private boolean timerIsPaused = false;

    //helps define actions in the "if" statement for when the timer is paused
    private boolean timerBIsPaused = false;

    // this is defined in the onMonveListener to show where timertextA is on a move
    float dX, dY;

    // variable to help define actions for when the timer is finished
    public boolean timerIsFinished = false;

    //after a menu item is touched then we say that it is selected.  This variable will help us decided what to do when the menu item is selected
    public boolean menuItemIsSelected = false;


    CustomCountDownTimer countDownTimerB = new CustomCountDownTimer(startTime, interval) {
        @Override
        public void onTick(long millisUntilFinished) {
            zoneBText.setText("" + millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            zoneBText.setText("Time's up!");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);


        //set up the couttdown timer
        countDownTimer = new FryerTimer.MyCountDownTimer(startTime, interval);

        //set menu item to be selected
        menuItem = (Button) findViewById(R.id.menuItems);

        zoneAText = (TextView) findViewById(R.id.zoneA_text);

        //set fryer A display of Time
        zoneATimerText = (TextView) findViewById(R.id.zoneA_timer);
        //set textTimer on Fryer A
        zoneATimerText.setText(zoneATimerText.getText() + String.valueOf(startTime / 1000));
        //define menu ited set selected variable

        //set code to select menu item and change beackground colors when selected
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



        //setOnTouchListener Here for when the timer is moved, we destroy the timer and make it ready to start again in
        //zoneATimertext and we must set all the colors of the zones back to original and hide the zoneATimerText and show
        //zoneAText

        zoneATimerText.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //ToDo add code to start moving the timer after it has been moved a little.  If we don't do that then it gets confused wit
                // Todo I want the touch to pause the timer
                // Todo add code so that when the animation is finished it will destroy timer we can use destroyCountdownTimer
                // Todo add code so that after the animation is finished it will return ZoneATimerText to the original position
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        view.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(1000)
                                .start();

                        // this holds the code to tell it what to do when destroying countdown timer when it is used
                        destroyCountDownTimer();

                        Log.v("on move", "you're moving");
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

       /* depending on the state of the timer, it needs to execute different code
       For example, if the timer hasn't started and a menu item is selected then we should start the timer in that z zone when
       we touch it.  If the timer has started and we touch then zone then the timer should pause

        */
        zoneARelativeLayout = (RelativeLayout) findViewById(R.id.zoneA_relative_layout);
        zoneARelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoneARelativeLayout.setSelected(!zoneARelativeLayout.isSelected());

                //if the menu item is selected and the timer has not started then change background of the zoneARelativeLaoyout
                // and start the timer
                // ToDo I think this code can probably be cleaned up a lot and maybe add some break statements to make it run faster. I'm not 100% sure
                if ((menuItemIsSelected) & (!timerHasStarted) & (timerIsPaused)) {
                    timerIsRunningViews();
                    countDownTimer.cancel();

                    countDownTimer.start();
                    menuItemIsSelected = false;
                    timerHasStarted = true;
                    menuItem.setBackgroundResource(R.drawable.menu_button);
                    timerIsPaused = false;

                // if the menu item is selected and time has not started or paused then we should start the timer
                } else if ((menuItemIsSelected) & (!timerHasStarted) & (!timerIsPaused)) {
                    timerIsRunningViews();
                    countDownTimer.cancel();
                    countDownTimer.start();
                    menuItemIsSelected = false;
                    timerHasStarted = true;
                    timerIsPaused = false;
                    menuItem.setBackgroundResource(R.drawable.menu_button);


                } else if (timerIsPaused & timerHasStarted & menuItemIsSelected) {
                    countDownTimer.cancel();
                    countDownTimer.start();
                    timerIsPaused = false;
                    timerHasStarted = true;
                    timerIsFinished = false;
                    menuItemIsSelected = false;
                } else if (!timerIsPaused & timerHasStarted & menuItemIsSelected) {
                    countDownTimer.cancel();
                    countDownTimer.start();
                    timerIsPaused = true;
                    timerHasStarted = true;
                    timerIsFinished = false;
                    menuItemIsSelected = false;
                } else if ((!timerIsPaused) & (timerHasStarted) & (!menuItemIsSelected)) {
                    timerIsRunningViews();
                    timerHasStarted = true;
                    countDownTimer.cancel();
                    timerIsPaused = true;
                    timerIsFinished = false;

                } else if ((timerIsPaused) & !menuItemIsSelected) {
                    countDownTimer = new MyCountDownTimer(s1, 1000);
                    countDownTimer.start();
                    timerIsPaused = false;
                    timerHasStarted = true;
                    timerIsFinished = false;
                } else {
                    Toast.makeText(getApplicationContext(), "Select menu item",
                            Toast.LENGTH_LONG).show();
                    //Handle de-select state change
                }


            }

        });


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
                    Log.i("11111111", "test");
                    countDownTimerB.pause();
                    timerBIsRunning = false;
                    timerBIsPaused = true;
                }
                else if(timerBIsPaused){
                    Log.i("111111112", "test");
                    countDownTimerB.resume();
                    timerBIsRunning = true;
                    timerBIsPaused = false;
                }

            }
        });
    }



    ///tell the OnTouchListener when to start moving the timerTextView and throw it away.

    //set the views for when the timer is running
    public void timerIsRunningViews() {

        zoneARelativeLayout.setBackgroundResource(R.drawable.pressed_menu_button);
        zoneAText.setVisibility(View.INVISIBLE);
        zoneATimerText.setVisibility(View.VISIBLE);
    }


    //        zoneATimerText.setVisibility(View.INVISIBLE);

    //build method to destroy countdown timer.
    // ToDo take those off from being commented but when done I can probably clean up some code in the if statement jut above
    public void destroyCountDownTimer() {
        //zoneATimerText.setVisibility(View.INVISIBLE);
        //zoneAText.setVisibility(View.VISIBLE);
        countDownTimer.cancel();


        zoneARelativeLayout.setBackgroundResource(R.color.colorPrimary);

    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }


        @Override
        public void onFinish() {
            zoneATimerText.setText("Time's up!");
            countDownTimer.cancel();
            timerIsFinished = true;
        }

        @Override

        public void onTick(long millisUntilFinished) {
            zoneATimerText.setText("" + millisUntilFinished / 1000);
            s1 = millisUntilFinished;
            zoneATimerText.setBackgroundResource(R.color.colorPrimary);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) {
//            case R.id.settiings_icon:
//                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
//                // Create a new intent to open the {@link PhrasesActivity}
        int id = item.getItemId();
        if (id == R.id.settiings_icon) {
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}







