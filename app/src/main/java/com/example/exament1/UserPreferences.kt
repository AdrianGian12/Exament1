package com.example.exament1

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun setUsername(username: String) {
        prefs.edit().putString("username", username).apply()
    }

    fun getUsername(): String? {
        return prefs.getString("username", null)
    }

    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean("logged_in", loggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("logged_in", false)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
