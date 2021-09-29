package com.android.app.nammaclinikks.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.app.nammaclinikks.R
import com.android.app.nammaclinikks.callback.GoogleMapTabCallback
import com.android.app.nammaclinikks.model.Clinic
import com.android.app.nammaclinikks.ui.search.adapter.SearchedClinicListAdapter
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper


class SearchClinikActivity : AppCompatActivity(), GoogleMapTabCallback {
    private val TAG = "SearchClinikActivity"
    var loaderScreenLayout: ConstraintLayout? = null
    var no_clinic_: LinearLayout? = null
    var loadAvailable: TextView? = null
    var adapter: SearchedClinicListAdapter? = null
    var clinic: List<Clinic> = ArrayList<Clinic>()
    var viewModel: SearchClinikViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_clinik)
        loaderScreenLayout = findViewById(R.id.loaderScreenLayout)
        no_clinic_ = findViewById(R.id.no_clinic_)
        loadAvailable = findViewById(R.id.loadAvailable)
        initView()
        viewModel = ViewModelProvider(this).get(SearchClinikViewModel::class.java)
        viewModel?.mClinic?.observe(this, Observer {
            loaderScreenLayout?.visibility = View.GONE
            if (it.clinics.isNullOrEmpty()) {
                no_clinic_?.visibility = View.VISIBLE
            } else {
                clinic = it.clinics
                no_clinic_?.visibility = View.GONE
                if (adapter != null) {
                    adapter?.updateContent(it.clinics)
                }
            }
        })

        viewModel?.error?.observe(this, Observer {
            Toast.makeText(this,"Error :- ${it}",Toast.LENGTH_SHORT).show()
        })
    }

    private fun initView() {
        adapter = SearchedClinicListAdapter(clinic, this, this)
        val clinicList = findViewById<RecyclerView>(R.id.clinicList)
        clinicList.layoutManager = LinearLayoutManager(this)
        clinicList.adapter = adapter

        loadAvailable?.setOnClickListener {
            loaderScreenLayout?.visibility = View.VISIBLE
            viewModel?.getAvailableClinic()
        }


    }

    override fun clickToRedirect(fromLat: String, fromLon: String, toLat: String, toLon: String) {

        // http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345


        val stringBuilder = StringBuffer()
        stringBuilder.append("http://maps.google.com/maps?")

        if (SharedPreferenceHelper.isLocationAvailable) {
            stringBuilder.append("saddr=")
            stringBuilder.append(fromLat)
            stringBuilder.append(",")
            stringBuilder.append(fromLon)
            stringBuilder.append("&")
        }
        stringBuilder.append("daddr=")
        stringBuilder.append(toLat)
        stringBuilder.append(",")
        stringBuilder.append(toLon)


        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(stringBuilder.toString())
        )
        startActivity(intent)

    }
}