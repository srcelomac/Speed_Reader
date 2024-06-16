package com.example.speedreader_1

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    companion object {
        private const val PREF_NAME = "MyAppPreferences"
        private const val KEY_SPEED_MIN = "speedMin"
        private const val KEY_SPEED_MAX = "speedMax"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var speedMin: Int
        get() = preferences.getInt(KEY_SPEED_MIN, 250)
        set(value) = preferences.edit().putInt(KEY_SPEED_MIN, value).apply()

    var speedMax: Int
        get() = preferences.getInt(KEY_SPEED_MAX, 1000)
        set(value) = preferences.edit().putInt(KEY_SPEED_MAX, value).apply()
}
