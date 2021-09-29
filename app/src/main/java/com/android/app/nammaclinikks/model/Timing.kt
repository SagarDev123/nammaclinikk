package com.android.app.nammaclinikks.model

data class Timing(
    val day: String,
    val shifts: List<Shift>,
    val status: String
)