package com.womensafety.app;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtility {

    private static SharedPreferences sharedPreferences = null;

    private static final String STORE_FILE_NAME = "WOMEN_SAFETY_PREF";

    public static SharedPreferences getSharedPreferenceObject(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static void saveToSharedPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferenceObject(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String readFromSharedPref(Context context, String key) {
        String value = getSharedPreferenceObject(context).getString(key, "");
        return value;
    }

}
