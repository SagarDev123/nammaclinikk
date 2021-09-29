package com.android.app.nammaclinikks.utils

import android.app.Application

class NammaClinikk: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object{
        lateinit var instance : NammaClinikk
    }
}