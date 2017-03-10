package com.example.android.fryerapp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.LongFunction;

/**
 * Created by yajos on 3/6/17.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    // Constants
    private static final String TAG = SettingsFragment.class.getName();
    private static final String SIXTEEN_SPACES = "                ";
    private static final String FIFTEEN_SPACES = "               ";
    private static final String THIRTEEN_SPACES = "            ";

    // todo iterate this
    private static final String[] BUTTON_NAME_KEYS = {"button1_text", "button2_text", "button3_text",
            "button4_text", "button5_text", "button6_text", "button7_text", "button8_text"};
    private static final String[] BUTTON_TIME_KEYS = {"button1_time", "button2_time", "button3_time",
            "button4_time", "button5_time", "button6_time", "button7_time", "button8_time"};

    // This list will store all the preferences's keys
    private List<String[]> arrayButtonKeys;


    // These variables will be use to remove and add PreferenceCategories
    PreferenceScreen fryersPreferenceScreen;
    Preference[] fryerCategories;
    Preference fryer1Category;
    Preference fryer2Category;
    Preference fryer3Category;
    Preference fryer4Category;


    // These variables are to meant to make the code more readable and avoid typos
    private String mNameETTitle;
    private String mTimeETTitle;
    private String mHowManyFryers;
    private int mSizeNameETTitle;
    private int mSizeTimeETTitle;
    private int mSizeHowManyFryers;
    private int mSizeHowManyZones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_race);

        // Initializes the variable that could be done before onCreate
        initializeVariables();

        // Set OnPreferenceChangeListener and summary to all the preferences
        for (String[] buttonKeys : arrayButtonKeys) {
            for (String buttonKey : buttonKeys) {
                bindPreferenceSummaryToValue(findPreference(buttonKey));
            }
        }
/*
        for (String button : BUTTON_TIME_KEYS){
            bindPreferenceSummaryToValue(findPreference(button));
        }*/
        Log.i(TAG, findPreference("button1_time").toString());
        Log.i(TAG, findPreference("button2_time").toString());
        Log.i(TAG, findPreference("button3_time").toString());
    }

    /*
    *
    * */
    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        // Although, the Fragment extends the Preference.OnPreferenceChangeListener, you most set
        // the listener to each preference.
        if (preference != null) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());

            if (!preference.getKey().contains("time")) {
                String preferenceString = preferences.getString(preference.getKey(), "");
                // Trigger the listener when the app is launched with the preference's current value to
                // the summary text gotten from the SharePreferences.
                onPreferenceChange(preference, preferenceString);
            } else {
                Log.i(TAG, "trigered");
                int preferenceInt = preferences.getInt(preference.getKey(), 15);
                Log.i(TAG, String.valueOf(preferenceInt));
                // Trigger the listener when the app is launched with the preference's current value to
                // the summary text gotten from the SharePreferences.
                onPreferenceChange(preference, preferenceInt);
            }
        }
    }

    /*
    *
    * */
    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {

        Log.i(TAG, "listener");

        if (preference.getKey().equals(getString(R.string.number_of_fryers_key))) {
            int numOfFryers = Integer.parseInt(value.toString());

            switch (numOfFryers) {
                case 1:
                    // If the user selects 1 Fryer on the ListPreference, remove fryer2,3,4 Category
                    fryersPreferenceScreen.removePreference(fryer2Category);
                    fryersPreferenceScreen.removePreference(fryer3Category);
                    fryersPreferenceScreen.removePreference(fryer4Category);
                    break;
                case 2:
                    // If the user selects 2 Fryer on the ListPreference, remove fryer3,4 Category and
                    // and add the fryer2Category
                    fryersPreferenceScreen.addPreference(fryer2Category);

                    fryersPreferenceScreen.removePreference(fryer3Category);
                    fryersPreferenceScreen.removePreference(fryer4Category);
                    break;
                case 3:
                    // If the user selects 3 Fryer on the ListPreference, remove fryer4 Category and
                    // and add the fryer2,3Category
                    fryersPreferenceScreen.addPreference(fryer2Category);
                    fryersPreferenceScreen.addPreference(fryer3Category);

                    fryersPreferenceScreen.removePreference(fryer4Category);
                    break;
                case 4:
                    // If the user selects 4 Fryer on the ListPreference, add fryer2,3,4Category
                    fryersPreferenceScreen.addPreference(fryer2Category);
                    fryersPreferenceScreen.addPreference(fryer3Category);
                    fryersPreferenceScreen.addPreference(fryer4Category);
                    break;
            }
        }
        setPreferenceSummary(preference, value);

        return true;
    }

    /*
    *
    * */
    // TODO: change for title.contains(mTimeETTitle) for key.contains
    private void setPreferenceSummary(Preference preference, Object value) {
        String title = preference.getTitle().toString();
        int newSizeTittle = title.length();
        // Get the first four characters, "Time" or "Name" and add the value, I did this to avoid
        // making a custom preference to set the summary next to the title, if you don't do
        // subString the title keeps growing.

        Log.i(TAG, "titleeeeeee");

        if (title.contains(mTimeETTitle)) {
            // Math.min(sizeTittle, SIZE_TIME_STRING)  to protect the app against a crash, if for
            // some reason the
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeTimeETTitle))
                    + SIXTEEN_SPACES + formatTime(Long.valueOf(String.valueOf(value)) * 1000));




        } else if (title.contains(mNameETTitle)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeNameETTitle)) +
                    FIFTEEN_SPACES + value);

        } else if (title.contains(mHowManyFryers)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeHowManyFryers))
                    + SIXTEEN_SPACES + value + " Fryers");

        } else if (title.contains(getString(R.string.number_of_zones_fryer1_key))) {
            preference.setSummary(value + " Zones");

        } else if (title.contains(getString(R.string.number_of_zones_fryer2_key))) {
            preference.setSummary(value + " Zones");

        }   else if (title.contains(getString(R.string.number_of_zones_fryer3_key))) {
            preference.setSummary(value + " Zones");

        } else if (title.contains(getString(R.string.number_of_zones_fryer4_key))) {
            preference.setSummary(value + " Zones");

        }

        /*if (title.contains(mTimeETTitle)) {
        // Math.min(sizeTittle, SIZE_TIME_STRING)  to protect the app against a crash, if for
        // some reason the
        preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeTimeETTitle))
                + SIXTEEN_SPACES + value);

        } else if (title.contains(mNameETTitle)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeNameETTitle)) +
                    FIFTEEN_SPACES + value);

        } else if (title.contains(mHowManyFryers)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeHowManyFryers))
                    + SIXTEEN_SPACES + value + " Fryers");

        } else if (title.contains(getString(R.string.number_of_zones_fryer1_title))) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeHowManyZones))
                    + THIRTEEN_SPACES + value + " Zones");

        } else if (title.contains(getString(R.string.number_of_zones_fryer2_title))) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeHowManyZones)) +
                    THIRTEEN_SPACES + value + " Zones");

        }   else if (title.contains(getString(R.string.number_of_zones_fryer3_title))) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeHowManyZones)) +
                    THIRTEEN_SPACES + value + " Zones");

        } else if (title.contains(getString(R.string.number_of_zones_fryer4_title))) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeHowManyZones)) +
                    THIRTEEN_SPACES + value + " Zones");

        }
*/
    }


    /*
    *
    * */
    // Todo add these notes
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        super.onPreferenceTreeClick(preferenceScreen, preference);

        if (preference!=null) {
            if (preference instanceof PreferenceScreen) {
                if (((PreferenceScreen) preference).getDialog() != null) {
                    ((PreferenceScreen) preference).getDialog().getWindow().getDecorView().setBackgroundDrawable(getActivity().getWindow().getDecorView().getBackground().getConstantState().newDrawable());
                    setUpNestedScreen((PreferenceScreen) preference);
                }
            }
        }

        return false;
    }

    // todo add this to notes
    public void setUpNestedScreen(PreferenceScreen preferenceScreen) {
        final Dialog dialog = preferenceScreen.getDialog();

        AppBarLayout appBar;

        View listRoot = dialog.findViewById(android.R.id.list);
        ViewGroup mRootView = (ViewGroup) dialog.findViewById(android.R.id.content);

        // If API <= 24, inflate an app bar in the nested PreferenceScreen this way.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LinearLayout root = (LinearLayout) dialog.findViewById(android.R.id.list).getParent().getParent();
            appBar = (AppBarLayout) LayoutInflater.from(getActivity()).inflate(R.layout.toolbar_settings, root, false);
            root.addView(appBar, 0);
            // If API <= 16, inflate an app bar in the nested PreferenceScreen this way.
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            LinearLayout root = (LinearLayout) dialog.findViewById(android.R.id.list).getParent();
            appBar = (AppBarLayout) LayoutInflater.from(getActivity()).inflate(R.layout.toolbar_settings, root, false);
            root.addView(appBar, 0);
            // If API < 16, Inflate an app bar in the nested PreferenceScreen this way.
        } else {
            ListView content = (ListView) mRootView.getChildAt(0);
            mRootView.removeAllViews();

            LinearLayout LL = new LinearLayout(getActivity());
            LL.setOrientation(LinearLayout.VERTICAL);

            ViewGroup.LayoutParams LLParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            LL.setLayoutParams(LLParams);

            appBar = (AppBarLayout) LayoutInflater.from(getActivity()).inflate(R.layout.toolbar_settings, mRootView, false);

            LL.addView(appBar);
            LL.addView(content);

            mRootView.addView(LL);
        }

        Toolbar tbar = (Toolbar) appBar.getChildAt(0);

        tbar.setTitle(preferenceScreen.getTitle());

        tbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    /*
    *
    * */
    private void initializeVariables() {

        fryersPreferenceScreen = (PreferenceScreen) findPreference("fryers");
        fryer1Category = findPreference("fryer1_category");
        fryer2Category = findPreference("fryer2_category");
        fryer3Category = findPreference("fryer3_category");
        fryer4Category = findPreference("fryer4_category");

        mNameETTitle = getString(R.string.edit_text_name_title);
        mTimeETTitle = getString(R.string.edit_text_time_title);
        mHowManyFryers = getString(R.string.number_of_fryers_title);
        //mNumberOfZonesTitles = {getString(R.string.number_of_zones_fryer1_title)};

        mSizeNameETTitle = mNameETTitle.length();
        mSizeTimeETTitle = mTimeETTitle.length();
        mSizeHowManyFryers = mHowManyFryers.length();
        mSizeHowManyZones = getString(R.string.number_of_zones_fryer1_key).length();

        String[] zonesTitles = {getString(R.string.number_of_zones_fryer1_key),
                getString(R.string.number_of_zones_fryer2_key),
                getString(R.string.number_of_zones_fryer3_key),
                getString(R.string.number_of_zones_fryer4_key)};

        String[] numOfFryersKey = {getString(R.string.number_of_fryers_key)};

        arrayButtonKeys = Arrays.asList(BUTTON_NAME_KEYS, BUTTON_TIME_KEYS, zonesTitles, numOfFryersKey);
    }


    /*
    *
    * Convert milliseconds to a String with format hh:mm:ss
    *
    * @param milliseconds
    * */
    // // TODO: declare this function only one time
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
}
