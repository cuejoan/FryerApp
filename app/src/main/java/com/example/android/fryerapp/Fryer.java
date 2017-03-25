package com.example.android.fryerapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by yajos on 2/10/17.
 */

class Fryer {

    private final static String TAG = Fryer.class.getName();

    private Context mContext;

    Zone zoneA;
    Zone zoneB;
    Zone zoneC;

    Fryer(Context context) {

        mContext = context;

        String zoneAText = context.getString(R.string.zone_a);
        String zoneBText = context.getString(R.string.zone_b);
        String zoneCText = context.getString(R.string.zone_c);

        zoneA = new Zone(zoneAText);
        zoneB = new Zone(zoneBText);
        zoneC = new Zone(zoneCText);
    }

    class Zone{
        boolean mTimerIsPaused;
        boolean mTimerIsRunning;
        boolean mIsStop;

        TextView mZoneText;
        TextView mSummaryText;

        String mDefaultTextZone;

        CustomCountDownTimer mTimer;

        MediaPlayer mMediaPlayer;

        private Zone(String defaultTextZone){
            // This is the only variable initialized because the rest are false by default.
            mIsStop = true;
            mDefaultTextZone = defaultTextZone;

            // todo if no logic to create the necessaries Fryers, think about instantiate the Timer
            // just when need it, and put the onFinish logic in the CustomCountDownTime
            mTimer = new CustomCountDownTimer() {
                @Override
                public void onTick(long millisUntilFinished, ButtonValue buttonValue) {
                    // The exact value of millisUntilFinished at this point is quite to difficult
                    // to expect because it's millisecond, the expected value is not accurate.
                    // e.g you want to catch the value 14 000 but the nearest values are
                    // 14 990 and 13 989, so round it to seconds solves this problem.
                    long seconds = millisUntilFinished / 1000;
                    if (buttonValue.getActionState() && seconds == buttonValue.getActionTime()) {
                        Log.i(TAG, buttonValue.getActionName());
                        mZoneText.setText(buttonValue.getActionName());
                        // Pause and subtract one second to the counter in order not to fall again
                        // in this if statement but in the following.
                        pauseAndSubtractOneSecond();
                        setVariablesToPausedMode();
                        // Create, setLooping true and start Media Player.
                        startMediaPlayer(R.raw.alarm_sound);
                    } else {
                        // Converts the millisecond to hh:mm:ss
                        String counter = formatTime(millisUntilFinished);
                        mZoneText.setText(counter);
                    }
                }

                @Override
                public void onFinish() {
                    mZoneText.setText(R.string.time_is_up);
                    setVariablesToStopMode();
                    // Create, setLooping true and start Media Player.
                    startMediaPlayer(R.raw.alarm_sound);
                }
            };
        }

        /* Set variables to the value they are supposed to be when running.*/
        void setVariablesToRunningMode() {
            mTimerIsPaused = false;
            mTimerIsRunning = true;
            mIsStop = false;
        }

        /* Set variables to the value they are supposed to be when pause.*/
        void setVariablesToPausedMode() {
            mTimerIsPaused = true;
            mTimerIsRunning = false;
            mIsStop = false;
        }

        /* Set variables to the value they are supposed to be when stop.*/
        void setVariablesToStopMode() {
            mTimerIsPaused = false;
            mTimerIsRunning = false;
            mIsStop = true;
        }


        /*
        * @param resId the of the audio file.
        * Create, setLooping true and start Media Player.
        * */
        private void startMediaPlayer(int resId) {
            mMediaPlayer = MediaPlayer.create(mContext, resId);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        }


        /**
         * Clean up the media player by releasing its resources.
         */
        void releaseMediaPlayer() {
            // If the media player is not null, then it may be currently playing a sound.
            if (mMediaPlayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
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
                    "mTimerIsPaused=" + mTimerIsPaused +
                    ", mTimerIsRunning=" + mTimerIsRunning +
                    ", mIsStop=" + mIsStop +
                    '}';
        }
    }
}