package com.android.app.nammaclinikks.model

data class Address(
    val city: String,
    val country: String,
    val lat: Double,
    val line: String,
    val lon: Double,
    val state: String,
    val zipcode: String
)