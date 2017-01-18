package com.example.android.fryerapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FryerTimer extends AppCompatActivity  {

    private boolean timerHasStarted = false;

    private CountDownTimer countDownTimer;
    public Button fryerAText;

    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set text View of fryerA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);
        //set up the couttdown timer
        countDownTimer = new FryerTimer.MyCountDownTimer(startTime, interval);
        //set menu item to be selected
        final Button menuItem = (Button) findViewById(R.id.menuItems);

        //set fryer button
        final Button fryerAText = (Button) findViewById(R.id.fryer1A);

        //set textTimer on Fryer A
        fryerAText.setText(fryerAText.getText() + String.valueOf(startTime / 1000));

        //set code to select menu item and change colors
        menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                menuItem.setSelected(!menuItem.isSelected());

                if (menuItem.isSelected()) {
                    menuItem.setBackgroundResource(R.drawable.pressed_menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was Selected",
                            Toast.LENGTH_LONG).show();
                    //Handle selected state change
                } else {
                    menuItem.setBackgroundResource(R.drawable.menu_button);
                    Toast.makeText(getApplicationContext(), "The Menu Was DeSelected",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //onclick lister set to fryerA to start timer
        //do stuff here
        fryerAText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the menu item is selected and the timer has not started then change background and start timer
                if (menuItem.isSelected()& (!timerHasStarted)) {
                    fryerAText.setBackgroundResource(R.drawable.pressed_menu_button);
                    countDownTimer.start();
                    timerHasStarted = true;
                    Toast.makeText(getApplicationContext(), "Start Timer",
                            Toast.LENGTH_LONG).show();
                }

                //if the menuItem is selected and timer has started then display text choose different zone
                if (menuItem.isSelected()& (timerHasStarted)) {


                    Toast.makeText(getApplicationContext(), "Choose A Different Zone",
                            Toast.LENGTH_LONG).show();
                }
                //if the menuItem is not selected and the timer has not started then cancel (pause timer)
                    if (!menuItem.isSelected() & (timerHasStarted) ){
                        countDownTimer.cancel();
                        timerHasStarted =false;
                        fryerAText.setBackgroundResource(R.drawable.menu_button);

                    }
                    //Handle selected state change
                 else {
                    Toast.makeText(getApplicationContext(), "Select menu item",
                            Toast.LENGTH_LONG).show();
                    //Handle de-select state change
                }
            }
        });



    }
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }


        @Override
        public void onFinish() {
            fryerAText.setText("Time's up!");
        }

        @Override

        public void onTick(long millisUntilFinished) {
            fryerAText.setText("" + millisUntilFinished / 1000);

        }

    }



}
