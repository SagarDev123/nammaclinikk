package com.android.app.nammaclinikks.model

data class Clinic(
    val address: Address,
    val distance: Distance,
    val id: String,
    val images: List<String>,
    val name: String,
    val status: String,
    val timings: List<Timing>,
    val translations: Translations
)