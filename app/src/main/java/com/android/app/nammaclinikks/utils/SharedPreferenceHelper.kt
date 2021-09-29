package com.android.app.nammaclinikks.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceHelper {
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    init {
        pref = NammaClinikk.instance.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editor = pref?.edit()
    }

    var altitude: String?
        get() = pref?.getString(Constants.altitude, null)
        set(value) = pref?.edit()?.putString(Constants.altitude, value)!!.apply()

    var latitude: String?
        get() = pref?.getString(Constants.latitude, null)
        set(value) = pref?.edit()?.putString(Constants.latitude, value)!!.apply()

    var longitude: String?
        get() = pref?.getString(Constants.longitude, null)
        set(value) = pref?.edit()?.putString(Constants.longitude, value)!!.apply()

    var language: String?
        get() = pref?.getString(Constants.language, Constants.ENGLISH)
        set(value) = pref?.edit()?.putString(Constants.language, value)!!.apply()

    var isLocationAvailable: Boolean
        get() = pref?.getBoolean(Constants.isLocationSelected, false) == true
        set(value) = pref?.edit()?.putBoolean(Constants.isLocationSelected, value)!!.apply()


    fun getClearCacheData(){
        longitude=""
        latitude=""
        isLocationAvailable=false
        language =Constants.ENGLISH

    }
}