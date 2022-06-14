package com.lab2_amanpreetkaur_c0854967_android;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static final String IS_UPDATED = "IS_UPDATED";
    private final SharedPreferences mSharedPreferences;
    public SharedPreference(Context context) {
        mSharedPreferences = context.getSharedPreferences(context.getString( R.string.shared_preference_file),
                Context.MODE_PRIVATE);
    }


    public void setIsUpdated(boolean status) {
        mSharedPreferences.edit()
                .putBoolean(IS_UPDATED, status)
                .apply();
    }



    public boolean getIsUpdated() {
        return mSharedPreferences.getBoolean(IS_UPDATED, false);
    }


}
