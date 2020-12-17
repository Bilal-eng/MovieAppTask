package com.teknasyon.movieapptask.utils

import android.util.Log

object Logger {
    private const val TAG = "MovieApp"
    fun debugErrorLogMessage(message: String) {
        Log.d(TAG, " 🤬 Message : $message")
    }

    fun debugSuccessLogMessage(message: String) {
        Log.d(TAG, " 😄 Message : $message")
    }
}
