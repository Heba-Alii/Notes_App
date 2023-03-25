package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreference {
    public static final String IS_LOGIN = "IsLoggedIn";

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

    public static String getName(Context context) {
        return getSharedPreference(context).getString("name", "");

    }

    public static String getMail(Context context) {
        return getSharedPreference(context).getString("mail", "");

    }

    public static String getPhone(Context context) {
        return getSharedPreference(context).getString("phone", "");

    }

    public static void deleteDataFromSharedPref(Context context) {
        getSharedPreference(context).edit().clear().apply();

    }

    public static void updateSharedPreference(Context context, String name, String mail, String phone) {
        getSharedPreference(context).edit().putString("name", name).commit();
        getSharedPreference(context).edit().putString("mail", mail).commit();
        getSharedPreference(context).edit().putString("phone", phone).commit();

    }

}
