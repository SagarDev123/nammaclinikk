package com.android.app.nammaclinikks.ui.search

import com.android.app.nammaclinikks.api.RetrofitBuilder
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class SearchPageRepository {
    companion object {
        private val mRepository: SearchPageRepository? = null
        fun getInstance(): SearchPageRepository {
            if (mRepository == null) {
                return SearchPageRepository()
            }
            return mRepository
        }
    }
    val clinicDataWithoutLocation = flow {
        val clinicData = RetrofitBuilder.apiServices?.getClinicWithOutPermission()
        emit(clinicData)
        delay(1000)
    }

    val clinicDataWithLocation = flow {
        val clinicData = RetrofitBuilder.apiServices?.getClinicWithPermission("42000",SharedPreferenceHelper.latitude.toString(),SharedPreferenceHelper.longitude.toString())
        emit(clinicData)
        delay(1000)
    }
}