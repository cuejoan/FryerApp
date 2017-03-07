package com.example.android.fryerapp;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;


/**
 * Created by Best Buy Demo on 2/8/2017.
 */

abstract class CustomCountDownTimer {  /**
     * Millis since epoch when alarm should stop.
     */
    private long mMillisInFuture;

    /**
     * The interval in millis that the user receives callbacks
     */
    private long mCountdownInterval;

    private long mStopTimeInFuture;

    private long mPauseTime;

    private boolean mCancelled = false;

    private boolean mPaused = false;

    private TextView textView;

    /**
     *
     */
    CustomCountDownTimer() {
    }


    /*

    * @param millisInFuture The number of millis in the future from the call
    *   to {@link #start()} until the countdown is done and {@link #onFinish()}
    *   is called.
    * @param countDownInterval The interval along the way to receive
    * */
    void setTimeAndStart(long millisInFuture, long countDownInterval, TextView tv) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
        textView = tv;
        start();
    }

    /*
    *
    *
    * */
    void setTimeAndStart(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
        start();
    }


    /**
     * Cancel the countdown.
     *
     * Do not call it from inside CountDownTimer threads
     */
    final void cancel() {
        mHandler.removeMessages(MSG);
        mCancelled = true;
    }

    /**
     * Start the countdown.
     */
    synchronized final CustomCountDownTimer start() {
        if (mMillisInFuture <= 0) {
            onFinish(textView);
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        mCancelled = false;
        mPaused = false;
        return this;
    }

    /**
     * Pause the countdown.
     */
    long pause() {
        mPauseTime = mStopTimeInFuture - SystemClock.elapsedRealtime();
        mPaused = true;
        return mPauseTime;
    }

    /**
     * Resume the countdown.
     */
    long resume() {
        mStopTimeInFuture = mPauseTime + SystemClock.elapsedRealtime();
        mPaused = false;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return mPauseTime;
    }

    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished, TextView view);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish(TextView view);


    private static final int MSG = 1;


    // handles counting down
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CustomCountDownTimer.this) {
                if (!mPaused) {
                    final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

                    if (millisLeft <= 0) {
                        onFinish(textView);
                    } else if (millisLeft < mCountdownInterval) {
                        // no tick, just delay until done
                        sendMessageDelayed(obtainMessage(MSG), millisLeft);
                    } else {
                        long lastTickStart = SystemClock.elapsedRealtime();
                        onTick(millisLeft, textView);

                        // take into account user's onTick taking time to execute
                        long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                        // special case: user's onTick took more than interval to
                        // complete, skip to next interval
                        while (delay < 0) delay += mCountdownInterval;

                        if (!mCancelled) {
                            sendMessageDelayed(obtainMessage(MSG), delay);
                        }
                    }
                }
            }
        }
    };
}