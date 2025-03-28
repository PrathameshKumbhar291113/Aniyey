package com.prathameshkumbhar.aniyey

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AniyeyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}