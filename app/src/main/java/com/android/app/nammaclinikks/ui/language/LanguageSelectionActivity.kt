package com.android.app.nammaclinikks.ui.language

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.app.nammaclinikks.R
import com.android.app.nammaclinikks.model.language.LanguageModel
import com.android.app.nammaclinikks.ui.language.adapter.LanguageListAdapter
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper

class LanguageSelectionActivity :AppCompatActivity(){

    private  val TAG = "LanguageSelectionActivi"
    var languages = ArrayList<LanguageModel>()
    var languageCycle:RecyclerView?=null
    var adapter :LanguageListAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel=ViewModelProvider(this).get(LanguageSwitchViewModel::class.java)
        setContentView(R.layout.activity_language_selection_activity)

        viewModel.languageList.observe(this, Observer {
            languages = it
            if(adapter!=null){
                adapter?.updateContent(languages)
            }

        })
        initView()
    }

    private fun initView() {
        if (adapter==null){
            adapter = LanguageListAdapter(languages,this)
            languageCycle=findViewById(R.id.languageCycle)
            languageCycle?.layoutManager = LinearLayoutManager(this)
            languageCycle?.adapter = adapter
        }
        val ivProceed = findViewById<ImageView>(R.id.ivProceed)
        ivProceed.setOnClickListener {
            Log.d(TAG, "initView: ${SharedPreferenceHelper.language}")
            finish()
        }


    }
}