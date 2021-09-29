package com.android.app.nammaclinikks.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.app.nammaclinikks.R
import com.android.app.nammaclinikks.callback.GoogleMapTabCallback
import com.android.app.nammaclinikks.model.AddressX
import com.android.app.nammaclinikks.model.Clinic
import com.android.app.nammaclinikks.utils.Constants
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper

class SearchedClinicListAdapter(
    var clinic: List<Clinic>,
    val context: Context,
    val googleMapTabCallback: GoogleMapTabCallback
) :
    RecyclerView.Adapter<SearchedClinicListAdapter.SearchedClinicVH>() {
    public class SearchedClinicVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun updateData(
            clinic: Clinic,
            context: Context,
            googleMapTabCallback: GoogleMapTabCallback
        ) {
            val tvClinicName = itemView.findViewById<TextView>(R.id.tvClinicName)
            val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
            val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
            val btGoToMap = itemView.findViewById<ImageView>(R.id.btGoToMap)
            val tvOpenDays = itemView.findViewById<TextView>(R.id.tvOpenDays)
            val tvOpenTiming = itemView.findViewById<TextView>(R.id.tvOpenTiming)
            val iVClinic = itemView.findViewById<ViewPager>(R.id.iVClinic)



            tvClinicName.text = getTransitionName(clinic);

            var clinicAddress = getTransitionAddress(clinic)


            val distanceBuilder = StringBuffer()
            distanceBuilder.append(clinic.distance.value)
            distanceBuilder.append(clinic.distance.unit)
            tvDistance.text = distanceBuilder

//            tvAddress.text = clinic.address.
            tvOpenDays.text = "Open Today"
            tvOpenTiming.text = "10 am - 11 pm"
            val addressBuilder = StringBuffer()
            addressBuilder.append(clinicAddress.city)
            addressBuilder.append(", ")
            addressBuilder.append(clinicAddress.state)
            addressBuilder.append(", ")
            addressBuilder.append(clinicAddress.zipcode)
            addressBuilder.append(" - ")
            addressBuilder.append(clinicAddress.country)
            tvAddress.text = addressBuilder


            // Initializing the ViewPagerAdapter
            val mViewPagerAdapter = ImageSliderAdapter(context, clinic.images)

            // Adding the Adapter to the ViewPager

            // Adding the Adapter to the ViewPager
            iVClinic.setAdapter(mViewPagerAdapter)

            btGoToMap.setOnClickListener {
                googleMapTabCallback.clickToRedirect(
                    SharedPreferenceHelper.latitude.toString(),
                    SharedPreferenceHelper.longitude.toString(),
                    clinic.address?.lat.toString(),
                    clinic.address.lon.toString()
                )
            }

        }

        private fun getTransitionAddress(clinic: Clinic): AddressX {

            when (SharedPreferenceHelper.language) {
                Constants.ENGLISH -> {
                    return clinic.translations.en.address
                }
                Constants.MALAYALAM -> {
                    return clinic.translations.ml.address
                }
                Constants.TAMIL -> {
                    return clinic.translations.ta.address
                }
                Constants.PUNJABI -> {
                    return clinic.translations.pa.address
                }
                Constants.ODIA -> {
                    return clinic.translations.or.address
                }
                Constants.TELUGU -> {
                    return clinic.translations.te.address
                }
                Constants.KANNADA -> {
                    return clinic.translations.kn.address
                }
                Constants.BENGALI -> {
                    return clinic.translations.bn.address
                }
                Constants.MARATHI -> {
                    return clinic.translations.mr.address
                }
                Constants.GUJARATI -> {
                    return clinic.translations.gu.address
                }
            }

                return clinic.translations.hi.address
            }


            private fun getTransitionName(clinic: Clinic): String {

                when (SharedPreferenceHelper.language) {
                    Constants.ENGLISH -> {
                        return clinic.translations.en.name
                    }
                    Constants.MALAYALAM -> {
                        return clinic.translations.ml.name
                    }
                    Constants.TAMIL -> {
                        return clinic.translations.ta.name
                    }
                    Constants.PUNJABI -> {
                        return clinic.translations.pa.name
                    }
                    Constants.ODIA -> {
                        return clinic.translations.or.name
                    }
                    Constants.TELUGU -> {
                        return clinic.translations.te.name
                    }
                    Constants.KANNADA -> {
                        return clinic.translations.kn.name
                    }
                    Constants.BENGALI -> {
                        return clinic.translations.bn.name
                    }
                    Constants.MARATHI -> {
                        return clinic.translations.mr.name
                    }
                    Constants.GUJARATI -> {
                        return clinic.translations.gu.name
                    }
                }



                return clinic.translations.hi.name
            }

        }

        fun updateContent(clinic: List<Clinic>) {
            this.clinic = clinic
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedClinicVH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.child_searched_clinic, parent, false)
            return SearchedClinicVH(view)
        }

        override fun onBindViewHolder(holder: SearchedClinicVH, position: Int) {
            holder.updateData(clinic[position], context, googleMapTabCallback)
        }

        override fun getItemCount(): Int {
            return clinic.size
        }
    }