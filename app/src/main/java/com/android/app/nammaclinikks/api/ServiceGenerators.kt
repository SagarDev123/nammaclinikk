package com.android.app.nammaclinikks.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerators {
    val BASE_URL="https://staging-api.clinikk.com/v1/"
    fun createServices():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

}