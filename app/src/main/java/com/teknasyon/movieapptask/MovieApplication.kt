package com.teknasyon.movieapptask

import android.app.Application
import com.teknasyon.movieapptask.connection.RestControllerFactory
import com.teknasyon.movieapptask.utils.ClientPreferences

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeApi()
    }

    private fun initializeApi() {
        clientPreferences = ClientPreferences(this)
        restControllerFactory = RestControllerFactory(this)
        appContext = this
    }

    companion object {

        lateinit var clientPreferences: ClientPreferences
        lateinit var restControllerFactory: RestControllerFactory
        lateinit var appContext: MovieApplication

        fun refreshConnectionInstance() {
            restControllerFactory = RestControllerFactory(appContext)
        }
    }
}