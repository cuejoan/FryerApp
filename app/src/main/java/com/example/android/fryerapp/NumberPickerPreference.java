package com.example.android.fryerapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by yajos on 3/8/17.
 */

public class NumberPickerPreference extends DialogPreference implements NumberPicker.OnValueChangeListener {

    // In this logic DEFAULT_VALUE should be a number bigger than 3600, that means 60 minutes,
    // this way the app identifies whether is a persisted value o not and can set the pickers.
    private static final int DEFAULT_VALUE = 10000;
    private static final int SECONDS_PER_MINUTE = 60;

    // Maximum and Minimum values that can be selected in each picker.
    private static final int MAX_VALUE = 59;
    private static final int MIN_VALUE = 0;

    // Current total of seconds
    private int mCurrentValue;
    // New total of seconds
    private int mNewValue;

    // Total of minutes
    private int mPickerMinutesValue;
    // Seconds
    private int mPickerSecondsValue;

    private NumberPicker mPickerMinutes;
    private NumberPicker mPickerSeconds;

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDialogLayoutResource(R.layout.numberpicker_dialog);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);

        setDialogIcon(null);
    }

    /*
    *
    * Here is where you can cast the NumberPicker objects, this method will be called each time
    * the dialog NumberPicker is open
    * */
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mPickerMinutes = (NumberPicker) view.findViewById(R.id.picker);
        mPickerSeconds = (NumberPicker) view.findViewById(R.id.picker2);

        mPickerMinutes.setOnValueChangedListener(this);
        mPickerSeconds.setOnValueChangedListener(this);

        // Set a minimum and maximum of 0 and 59 to each picker.
        setMaxAndMinPickersValue();

        mCurrentValue = getPersistedInt(DEFAULT_VALUE);
        // If there is a persisted value, proceed
        if (mCurrentValue != DEFAULT_VALUE) {
            mPickerMinutesValue = getPersistedInt(DEFAULT_VALUE) / SECONDS_PER_MINUTE;
            mPickerSecondsValue = getPersistedInt(DEFAULT_VALUE) % SECONDS_PER_MINUTE;

            mPickerMinutes.setValue(mPickerMinutesValue);
            mPickerSeconds.setValue(mPickerSecondsValue);
            // If there isn't a persisted value, proceed
        } else {
            mPickerMinutes.setValue(MIN_VALUE);
            mPickerSeconds.setValue(MIN_VALUE);
        }
    }


    /*

    * Set a minimum and maximum of 0 and 59 to each picker.
    * */
    private void setMaxAndMinPickersValue() {
        mPickerMinutes.setMinValue(MIN_VALUE);
        mPickerMinutes.setMaxValue(MAX_VALUE);

        mPickerSeconds.setMinValue(MIN_VALUE);
        mPickerSeconds.setMaxValue(MAX_VALUE);
    }

    /*
    *
    * If one or both pickers were modified, store the total of seconds in mNewValue that
    * would be persisted later when onDialogClosed is triggered.
    * */
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (mPickerMinutes == picker) {
            mPickerMinutesValue = newVal;
        }
        if (mPickerSeconds == picker) {
            mPickerSecondsValue = newVal;
        }
        mNewValue = (mPickerMinutesValue * SECONDS_PER_MINUTE) + mPickerSecondsValue;
        // Call this method after the user changes the preference, but before the internal
        // state is set. This will call the Preference.onPreferenceChange in the SettingFragment.
        callChangeListener(mNewValue);
    }

    /*
   * This was implemented following Android documentation
   * https://developer.android.com/guide/topics/ui/settings.html
   * */
    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            persistInt(mNewValue);
        }
    }

    /*
      * This was implemented following Android documentation
      * https://developer.android.com/guide/topics/ui/settings.html
      * */
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInteger(index, DEFAULT_VALUE);
    }


    /*
    * This was implemented following Android documentation
    * https://developer.android.com/guide/topics/ui/settings.html
    * */
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            // Restore existing state
            // todo this
            mCurrentValue = this.getPersistedInt(DEFAULT_VALUE);
        } else {
            // Set default state from the XML attribute
            //mCurrentValue = (Integer) defaultValue;
            persistInt(mCurrentValue);
        }
    }
}
