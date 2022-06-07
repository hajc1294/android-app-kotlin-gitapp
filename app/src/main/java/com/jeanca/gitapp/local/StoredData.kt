package com.jeanca.gitapp.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.jeanca.gitapp.common.Constants.STORE_TAG
import com.jeanca.gitapp.common.Constants.TOKEN_TAG
import com.jeanca.gitapp.common.Constants.USER_TAG

class StoredData(context: Context) {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(STORE_TAG,
        Context.MODE_PRIVATE)

    fun saveData(user: String, token: String) {
        sharedPreferences.edit().putString(USER_TAG, user).apply()
        sharedPreferences.edit().putString(TOKEN_TAG, user).apply()
    }

    fun getUser(): String {
        return sharedPreferences.getString(USER_TAG, "") ?: ""
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_TAG, "") ?: ""
    }

}