package com.android.app.nammaclinikks.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.nammaclinikks.model.ClinikksModel
import com.android.app.nammaclinikks.ui.home.HomePageRepository
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchClinikViewModel:ViewModel() {
    fun getAvailableClinic() {
        viewModelScope.launch {
            mRepository?.clinicDataWithoutLocation?.catch {
                Log.d(TAG, "exception: ${it.message}")
            }
                ?.collect {
                    Log.d(TAG, "clinic data: ${it?.clinics?.size}")
                    mClinic.postValue(it)
                }
        }
    }

    private  val TAG = "SearchClinikViewModel"
    var mRepository: SearchPageRepository?=null
    val mClinic = MutableLiveData<ClinikksModel>()
    val error = MutableLiveData<String>()
    init {
        mRepository = SearchPageRepository.getInstance()
        viewModelScope.launch {
            if (SharedPreferenceHelper.isLocationAvailable){
                mRepository?.clinicDataWithLocation?.catch {
                    Log.d(TAG, "exception: ${it.message}")
                    error.postValue(it.message)
                }
                    ?.collect {
                        Log.d(TAG, "clinic data: ${it?.clinics?.size}")
                        mClinic.postValue(it)
                    }
            }else{
                mRepository?.clinicDataWithoutLocation?.catch {
                    Log.d(TAG, "exception: ${it.message}")
                }
                    ?.collect {
                        Log.d(TAG, "clinic data: ${it?.clinics?.size}")
                        mClinic.postValue(it)
                    }
            }

        }
    }
}