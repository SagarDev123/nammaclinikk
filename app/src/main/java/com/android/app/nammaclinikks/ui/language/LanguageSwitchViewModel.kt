package com.android.app.nammaclinikks.ui.language

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.app.nammaclinikks.model.language.LanguageModel
import com.android.app.nammaclinikks.utils.Constants

class LanguageSwitchViewModel:ViewModel() {
    val languageList = MutableLiveData<ArrayList<LanguageModel>>()

    init {
        getLanguageList()
    }


    fun getLanguageList(){

        val languages = ArrayList<LanguageModel>()

        val kannada = LanguageModel("Kannada",Constants.KANNADA)
        val english = LanguageModel("English",Constants.ENGLISH)
        val marathi = LanguageModel("Marathi",Constants.MARATHI)
        val punjabi = LanguageModel("Punjabi",Constants.PUNJABI)
        val odia = LanguageModel("Odia",Constants.ODIA)
        val bengali = LanguageModel("Bengali",Constants.BENGALI)
        val gujrati = LanguageModel("Gujrati",Constants.GUJARATI)
        val malayalam = LanguageModel("Malayalam",Constants.MALAYALAM)
        val tamil = LanguageModel("Tamil",Constants.TAMIL)
        val hindi =LanguageModel("Hindi",Constants.HINDI)
        val telugu = LanguageModel("Telugu",Constants.TELUGU)


        languages.add(kannada)
        languages.add(english)
        languages.add(marathi)
        languages.add(punjabi)
        languages.add(odia)
        languages.add(bengali)
        languages.add(gujrati)
        languages.add(malayalam)
        languages.add(tamil)
        languages.add(hindi)
        languages.add(telugu)

        languageList.postValue(languages)
    }
}