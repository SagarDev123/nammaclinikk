package com.android.app.nammaclinikks.api

import com.android.app.nammaclinikks.model.ClinikksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("clinic/search")
    suspend fun getClinicWithOutPermission(): ClinikksModel

    @GET("clinic/search")
    suspend fun getClinicWithPermission(
        @Query("radius") radius: String,
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): ClinikksModel
}