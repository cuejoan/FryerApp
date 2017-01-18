package com.example.android.fryerapp;

/**
 * Created by Best Buy Demo on 1/17/2017.
 */
/*

public class TimerTask extends Activity implements View.OnClickListener {

/*
    private CountDownTimer countDownTimer;

    private boolean timerHasStarted = false;
    private Button startB;
    public Button textTimer;
    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fryer_timer);
        startB = (Button) this.findViewById(R.id.fryer1A);
        startB.setOnClickListener(this);

        textTimer = (Button) this.findViewById(R.id.fryer1A);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        textTimer.setText(textTimer.getText() + String.valueOf(startTime / 1000));
    }


    @Override
    public void onClick(View v) {
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }


        @Override
        public void onFinish() {
            textTimer.setText("Time's up!");
        }

        @Override

        public void onTick(long millisUntilFinished) {

            textTimer.setText("" + millisUntilFinished / 1000);

        }

    }
stuff that i'm saving

 button.setSelected(!button.isSelected());

                if (menuItem.isSelected()  || !timerHasStarted ) {
                    timerHasStarted =true;

                    Toast.makeText(getApplicationContext(), "Start Timer",
                            Toast.LENGTH_LONG).show();

                    //Handle selected state change
                } else {
                    timerHasStarted = false;
                    Toast.makeText(getApplicationContext(), "Select menu item",
                            Toast.LENGTH_LONG).show();
                    //Handle de-select state change
                }

}
*/


/*  Working code for menu selection with some fryer implementations

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
        Button fryerAText = (Button) findViewById(R.id.fryer1A);

        //set textTimer on Fryer A
        fryerAText.setText(fryerAText.getText() + String.valueOf(startTime / 1000));

        //set code to select menu item and change colors
           menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                menuItem.setSelected(!button.isSelected());

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

                if (menuItem.isSelected()) {

                    Toast.makeText(getApplicationContext(), "Start Timer",
                            Toast.LENGTH_LONG).show();

                    //Handle selected state change
                } else {
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
*/