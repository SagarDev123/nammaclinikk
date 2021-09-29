package com.android.app.nammaclinikks.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.widget.LinearLayout

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.app.nammaclinikks.R
import com.squareup.picasso.Picasso
import java.util.*


class ImageSliderAdapter(val context: Context,val images: List<String>):PagerAdapter() {

    var mLayoutInflater :LayoutInflater?=null


    init {
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    }


    override fun getCount(): Int {
       return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemView: View = mLayoutInflater?.inflate(R.layout.image_holder, container, false)!!

        // referencing the image view from the item.xml file

        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView.findViewById<View>(R.id.imageHolder) as ImageView

        // setting the image in the imageView

        Picasso.get().load(images[position]).into(imageView)

        // Adding the View

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)

        return itemView

    }



    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

//    fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
//        container.removeView(`object` as LinearLayout?)
//    }
}