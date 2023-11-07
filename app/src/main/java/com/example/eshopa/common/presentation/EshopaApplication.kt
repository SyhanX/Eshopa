package com.example.eshopa.common.presentation

import android.app.Application
import com.example.eshopa.common.data.AppContainer
import com.example.eshopa.common.data.DefaultAppContainer

class EshopaApplication: Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer(this)
    }
}