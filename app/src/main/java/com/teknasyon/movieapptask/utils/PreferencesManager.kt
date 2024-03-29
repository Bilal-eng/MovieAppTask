package com.teknasyon.movieapptask.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

abstract class PreferencesManager @JvmOverloads protected constructor(
    targetContext: Context,
    preferenceName: String = defaultPackageName
) {
    private val targetPreferences: SharedPreferences =
        targetContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun clearKey(key: String) {
        if (targetPreferences.contains(key)) {
            putString(key, null)
        }
    }

    fun getBooleanValue(key: String): Boolean {
        return targetPreferences.getBoolean(key, false)
    }

    fun getBooleanValue(key: String, defaultValue: Boolean): Boolean {
        return targetPreferences.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String): Float {
        return targetPreferences.getFloat(key, 0f)
    }

    fun getInt(key: String): Int {
        return targetPreferences.getInt(key, 0)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return targetPreferences.getInt(key, defaultValue)
    }

    fun getLong(key: String): Long {
        return targetPreferences.getLong(key, 0)
    }

    fun <T> getObject(key: String, targetType: Class<T>): T? {
        return getObject(key, targetType, null)
    }

    fun <T> getObject(key: String, targetType: Class<T>, defaultValue: T?): T? {
        if (targetPreferences.contains(key)) {
            val preferenceTarget = targetPreferences.getString(key, "")
            if (preferenceTarget != "") {
                return gson.fromJson(preferenceTarget, targetType)
            }
        }
        return defaultValue
    }

    @JvmOverloads
    fun getString(key: String, defaultValue: String = emptyString): String {
        if (targetPreferences.getString(key, defaultValue) != null) {
            return targetPreferences.getString(key, defaultValue)!!
        } else {
            return defaultValue
        }
    }

    fun putBoolean(key: String, value: Boolean): Boolean {
        val targetEditor = targetPreferences.edit()
        targetEditor.putBoolean(key, value)
        return targetEditor.commit()
    }

    fun putFloat(key: String, targetValue: Float): Boolean {
        val targetEditor = targetPreferences.edit()
        return targetEditor.putFloat(key, targetValue).commit()
    }

    fun putInt(key: String, targetValue: Int): Boolean {
        val targetEditor = targetPreferences.edit()
        return targetEditor.putInt(key, targetValue).commit()
    }

    fun putLong(key: String, targetValue: Long): Boolean {
        val targetEditor = targetPreferences.edit()
        return targetEditor.putLong(key, targetValue).commit()
    }

    fun putObject(key: String, targetObject: Any?) {
        putString(key, gson.toJson(targetObject))
    }

    fun putString(keys: Array<String>, values: Array<String>) {
        val targetEditor = targetPreferences.edit()
        var counter = 0
        for (key in keys) {
            targetEditor.putString(key, values[counter++])
        }
        targetEditor.apply()
    }

    fun putString(key: String, value: String?) {
        val targetEditor = targetPreferences.edit()
        targetEditor.putString(key, value)
        targetEditor.apply()
    }

    companion object {

        private val defaultPackageName = "packageSettings"
        private val emptyString = ""
    }

}