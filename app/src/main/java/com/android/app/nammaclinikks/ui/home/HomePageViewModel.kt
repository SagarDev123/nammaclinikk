package com.android.app.nammaclinikks.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.nammaclinikks.model.ClinikksModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomePageViewModel:ViewModel() {
    private  val TAG = "HomePageViewModel"
    var mRepository: HomePageRepository?=null
    val mClinic = MutableLiveData<ClinikksModel>()
    init {
        mRepository = HomePageRepository.getInstance()
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
}