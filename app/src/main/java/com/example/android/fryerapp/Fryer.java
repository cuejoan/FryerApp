package com.example.android.fryerapp;

import android.content.Context;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by yajos on 2/10/17.
 */

class Fryer {

    private String a;
    private String b;
    private String c;

    // todo maybe change this to a list
    Zone zoneA;
    Zone zoneB;
    Zone zoneC;

    Fryer(Context context) {

        a = context.getString(R.string.zone_a);
        b = context.getString(R.string.zone_b);
        c = context.getString(R.string.zone_c);

        zoneA = new Zone(a);
        zoneB = new Zone(b);
        zoneC = new Zone(c);
    }

    class Zone{
        boolean mIsPaused;
        boolean mIsRunning;
        boolean mIsStop;

        TextView mZoneText;
        TextView mSummaryText;

        String mDefaultTextZone;

        CustomCountDownTimer mTimer;

        private Zone(String defaultTextZone){
            // This is the only variable initialized because the rest are false by default.
            mIsStop = true;
            mDefaultTextZone = defaultTextZone;
            mTimer = new CustomCountDownTimer() {
                @Override
                public void onTick(long millisUntilFinished, TextView textView) {
                    // Converts the millisecond to hh:mm:ss
                    String counter = formatTime(millisUntilFinished);
                    textView.setText(counter);
                }

                @Override
                public void onFinish(TextView view) {
                    view.setText("Time's up!");
                    setVariablesToStopMode();
                }
            };
        }

        /* Set variables to the value they are supposed to be when running.*/
        void setVariablesToRunningMode() {
            mIsPaused = false;
            mIsRunning = true;
            mIsStop = false;
        }

        /* Set variables to the value they are supposed to be when pause.*/
        void setVariablesToPausedMode() {
            mIsPaused = true;
            mIsRunning = false;
            mIsStop = false;
        }

        /* Set variables to the value they are supposed to be when stop.*/
        void setVariablesToStopMode() {
            mIsPaused = false;
            mIsRunning = false;
            mIsStop = true;
        }


        /*
        *
        * Convert milliseconds to a String with format hh:mm:ss
        *
        * @param milliseconds
        * */
        String formatTime(long milliseconds) {
            int oneHour = 3600000;
            if (milliseconds < oneHour){
                return String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                        TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
            }
            return String.format(Locale.getDefault(),"%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(milliseconds),
                    TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                    TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
        }


        @Override
        public String toString() {
            return "Zone{" +
                    "mIsPaused=" + mIsPaused +
                    ", mIsRunning=" + mIsRunning +
                    ", mIsStop=" + mIsStop +
                    '}';
        }
    }



}