package com.example.android.fryerapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by yajos on 2/10/17.
 */

class Fryer {

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
        boolean mIsPaused;
        boolean mIsRunning;
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
                public void onTick(long millisUntilFinished, TextView textView) {
                    // Converts the millisecond to hh:mm:ss
                    String counter = formatTime(millisUntilFinished);
                    textView.setText(counter);
                }

                @Override
                public void onFinish(TextView view) {
                    view.setText(R.string.time_is_up);
                    setVariablesToStopMode();
                    mMediaPlayer = MediaPlayer.create(mContext, R.raw.alarm_sound);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.start();
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
                    "mIsPaused=" + mIsPaused +
                    ", mIsRunning=" + mIsRunning +
                    ", mIsStop=" + mIsStop +
                    '}';
        }
    }
}