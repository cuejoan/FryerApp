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

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by yajos on 3/6/17.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    // Constants
    private static final String TAG = SettingsFragment.class.getName();
    private static final String SIXTEEN_SPACES = "                ";
    private static final String FIFTEEN_SPACES = "               ";

    private ArrayList<String> mKeys = new ArrayList<String>() {
    };

    // These variables will be use to remove and add PreferenceCategories
    PreferenceScreen fryersPreferenceScreen;
    Preference fryer1Category;
    Preference fryer2Category;
    Preference fryer3Category;
    Preference fryer4Category;


    // These variables are to meant to make the code more readable and avoid typos
    private String mMenuItemName;
    private String mMenuItemTime;
    private String mActionName;
    private String mActionTime;
    private String mHowManyFryers;
    private int mSizeMenuItemName;
    private int mSizeActionName;
    private int mSizeMenuItemTime;
    private int mSizeActionTime;
    private int mSizeHowManyFryers;
    private int mSizeHowManyZones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_race);

        // Initializes the variable that could be done before onCreate
        initializeVariables();

        // Set OnPreferenceChangeListener and summary to all the preferences
        for (String key : mKeys) {
            bindPreferenceSummaryToValue(findPreference(key));
        }
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

            // If Preference is any less than Action Time or Menu Item Time, proceed.
            if (!preference.getKey().contains("time")) {
                String preferenceString = preferences.getString(preference.getKey(), "");
                // Trigger the listener when the app is launched with the preference's current value to
                // the summary text gotten from the SharePreferences.
                onPreferenceChange(preference, preferenceString);

            // If is an Action Time Preference, proceed.
            } else if (preference.getKey().contains("action")) {
                int preferenceInt = preferences.getInt(preference.getKey(), 11);
                Log.i(TAG, String.valueOf(preferenceInt));
                // Trigger the listener when the app is launched with the preference's current value to
                // the summary text gotten from the SharePreferences.
                onPreferenceChange(preference, preferenceInt);

            // If is an Action Time Preference, proceed.
            } else {
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
    // TODO: change for title.contains(mMenuItemTime) for key.contains
    private void setPreferenceSummary(Preference preference, Object value) {
        String title = preference.getTitle().toString();
        int newSizeTittle = title.length();

        if (title.contains(mActionName)) {
            // Math.min(sizeTittle, SIZE_TIME_STRING)  to protect the app against a crash, if for
            // some reason the
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeActionName)) +
                    FIFTEEN_SPACES + value);

        } else if (title.contains(mActionTime)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeActionTime))
                    + SIXTEEN_SPACES + formatTime(Long.valueOf(String.valueOf(value)) * 1000));

        } else if (title.contains(mMenuItemTime)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeMenuItemTime))
                    + SIXTEEN_SPACES + formatTime(Long.valueOf(String.valueOf(value)) * 1000));

        } else if (title.contains(mMenuItemName)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeMenuItemName)) +
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

        /*if (title.contains(mMenuItemTime)) {
        // Math.min(sizeTittle, SIZE_TIME_STRING)  to protect the app against a crash, if for
        // some reason the
        preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeMenuItemTime))
                + SIXTEEN_SPACES + value);

        } else if (title.contains(mMenuItemName)) {
            preference.setTitle(title.substring(0, Math.min(newSizeTittle, mSizeMenuItemName)) +
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

        for (int i = 1; i <= FryerActivity.NUMBER_MENU_ITEMS; i++ ) {
            mKeys.add("menu_item_name" + i);
            mKeys.add("menu_item_time" + i);
            mKeys.add("action_name" + i);
            mKeys.add("action_time" + i);
        }
        mKeys.add(getString(R.string.number_of_zones_fryer1_key));
        mKeys.add(getString(R.string.number_of_zones_fryer2_key));
        mKeys.add(getString(R.string.number_of_zones_fryer3_key));
        mKeys.add(getString(R.string.number_of_zones_fryer4_key));
        mKeys.add(getString(R.string.number_of_fryers_key));

        fryersPreferenceScreen = (PreferenceScreen) findPreference("fryers");
        fryer1Category = findPreference("fryer1_category");
        fryer2Category = findPreference("fryer2_category");
        fryer3Category = findPreference("fryer3_category");
        fryer4Category = findPreference("fryer4_category");

        mMenuItemName = getString(R.string.menu_item_name);
        mMenuItemTime = getString(R.string.menu_item_time);
        mActionName = getString(R.string.action_name);
        mActionTime = getString(R.string.action_time);
        mHowManyFryers = getString(R.string.number_of_fryers_title);
        //mNumberOfZonesTitles = {getString(R.string.number_of_zones_fryer1_title)};

        mSizeMenuItemName = mMenuItemName.length();
        mSizeMenuItemTime = mMenuItemTime.length();
        mSizeActionName = mActionName.length();
        mSizeActionTime = mActionTime.length();
        mSizeHowManyFryers = mHowManyFryers.length();
        mSizeHowManyZones = getString(R.string.number_of_zones_fryer1_key).length();
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
