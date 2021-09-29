package com.android.app.nammaclinikks.api

object RetrofitBuilder {
    val apiServices: ApiServices?
        get() {
            return ServiceGenerators.createServices().create(ApiServices::class.java)
        }
}