package com.android.app.nammaclinikks.ui.home

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.app.nammaclinikks.R
import com.android.app.nammaclinikks.ui.language.LanguageSelectionActivity
import com.android.app.nammaclinikks.ui.search.SearchClinikActivity
import com.android.app.nammaclinikks.utils.Constants
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.content.DialogInterface





class HomePage : AppCompatActivity(), View.OnClickListener {
    /**
     * Represents a geographical location.
     */
    protected var mLastLocation: Location? = null
    private val TAG = "HomePage"
    private val PERMISSION_REQUEST_CODE = 200

    /**
     * Provides the entry point to the Fused Location Provider API.
     */
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        val viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
//        viewModel.mClinic.observe(this, Observer {
//
//        })
    }

    private fun initView() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val btnLocateNow = findViewById<ImageView>(R.id.btnLocateNow)
        val btnLanguageSwitch = findViewById<ImageView>(R.id.btnLanguageSwitch)
        val iconBackPress = findViewById<ImageView>(R.id.iconBackPress)
        btnLanguageSwitch.setOnClickListener(this)
        btnLocateNow.setOnClickListener(this)
        iconBackPress.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btnLocateNow -> {
                if (!checkPermissions()) {
                    requestPermissions()
                } else {
                    getLastLocation()
                }
            }
            R.id.btnLanguageSwitch -> {
                startActivity(Intent(this, LanguageSelectionActivity::class.java))
            }
            R.id.iconBackPress->{
                exitAlert()
            }
        }
    }

    private fun exitAlert() {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setMessage("Are you sure you want to exit from the App?")
        alertBuilder.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener { dialog, id -> exitApp(dialog) })
        alertBuilder.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alertDialog:AlertDialog = alertBuilder.create()
        alertDialog.show()

    }

    private fun exitApp(dialog: DialogInterface) {
        dialog.dismiss()
        SharedPreferenceHelper.getClearCacheData()
        finish();
        System.exit(0);
    }

    /**
     * Return the current state of the permissions needed.
     */
    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
//        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
//            Manifest.permission.ACCESS_COARSE_LOCATION)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
            PERMISSION_REQUEST_CODE
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted.
            getLastLocation()
        } else {
            SharedPreferenceHelper.isLocationAvailable=false
            gotoNewSearch()
        }
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     *
     *
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        mFusedLocationClient!!.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    mLastLocation = task.result;
                    Log.d(
                        TAG,
                        "getLastLocation: ${Constants.latitude} :- ${mLastLocation!!.latitude}"
                    )
                    Log.d(
                        TAG,
                        "getLastLocation: ${Constants.longitude} :- ${mLastLocation!!.longitude}"
                    )
                    Log.d(TAG, "getLastLocation: Altitude :- ${mLastLocation!!.altitude}")
                    SharedPreferenceHelper.altitude = mLastLocation!!.altitude.toString()
                    SharedPreferenceHelper.latitude = mLastLocation!!.latitude.toString()
                    SharedPreferenceHelper.longitude = mLastLocation!!.longitude.toString()
                    SharedPreferenceHelper.isLocationAvailable=true
                    gotoNewSearch()
                } else {
                    SharedPreferenceHelper.isLocationAvailable=false
                    Log.w(TAG, "getLastLocation:exception :- ${task.exception}")
                    Toast.makeText(this,"Issue in getting your location",Toast.LENGTH_SHORT).show()
                    gotoNewSearch()
                }
            }
    }

    private fun gotoNewSearch() {
        startActivity(Intent(this, SearchClinikActivity::class.java))
    }
}