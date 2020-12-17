package com.teknasyon.movieapptask.utils

class APIError {
    private val statusCode = 0
    private val message: String? = "Unknown error."
    private val error: String? = "Unknown request"
    fun status(): Int {
        return statusCode
    }

    fun message(): String? {
        return message
    }

    fun error(): String? {
        return error
    }
}