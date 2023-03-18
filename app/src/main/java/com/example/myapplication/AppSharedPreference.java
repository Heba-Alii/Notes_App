package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreference {
    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("notesLogin", Context.MODE_PRIVATE);

    }

    public static void writeToSharedPref(Context context, String name, String mail, String phone) {
        getSharedPreference(context).edit().putString("name", name).apply();
        getSharedPreference(context).edit().putString("mail", mail).apply();
        getSharedPreference(context).edit().putString("phone", phone).apply();
    }

    public static boolean isUserLogin(Context context) {
        return getSharedPreference(context).contains("name");
    }
}
