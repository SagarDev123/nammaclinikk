package com.android.app.nammaclinikks.ui.home

import com.android.app.nammaclinikks.api.RetrofitBuilder
import kotlinx.coroutines.flow.flow

class HomePageRepository {
    companion object {
        private val mRepository: HomePageRepository? = null
        fun getInstance(): HomePageRepository {
            if (mRepository == null) {
                return HomePageRepository()
            }
            return mRepository
        }
    }

    val clinicDataWithoutLocation = flow {
        val clinicData = RetrofitBuilder.apiServices?.getClinicWithOutPermission()
        emit(clinicData)
        kotlinx.coroutines.delay(1000)
    }

    val clinicDataWithLocation = flow {
        val clinicData = RetrofitBuilder.apiServices?.getClinicWithOutPermission()
        emit(clinicData)
        kotlinx.coroutines.delay(1000)
    }
}