package com.example.elf.explodedwords;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * Settings Activity
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private boolean needLoadPrefsFromRes = false;

    @Override
    protected void onResume() {
        super.onResume();
        //Listening for chainges to be able to react
        PreferenceManager.getDefaultSharedPreferences(getApplication()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(getApplication()).unregisterOnSharedPreferenceChangeListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needLoadPrefsFromRes) {
            addPreferencesFromResource(R.xml.prefs_general);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        if (onIsHidingHeaders() || !onIsMultiPane()) {
            needLoadPrefsFromRes = true;
        } else {
            loadHeadersFromResource(R.xml.pref_headers, target);
        }
    }

    @Override
    public boolean isValidFragment(String fragmentName) {
        if (fragmentName.equals(GeneralPreferenceFragment.class.getName())) return true;
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("user_name")){
            TextView userNamePref = (TextView) findViewById(R.id.preference_value);
            userNamePref.setText(sharedPreferences.getString(key, getString(R.string.unknown_user)));
        }
    }


    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs_general);
        }
    }
}
