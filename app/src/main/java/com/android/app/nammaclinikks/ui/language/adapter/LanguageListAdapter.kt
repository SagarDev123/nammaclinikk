package com.android.app.nammaclinikks.ui.language.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.app.nammaclinikks.R
import com.android.app.nammaclinikks.model.language.LanguageModel
import com.android.app.nammaclinikks.ui.language.LanguageSelectionActivity
import com.android.app.nammaclinikks.utils.SharedPreferenceHelper


class LanguageListAdapter(
    var languages: ArrayList<LanguageModel>,
   var languageSelectionActivity: LanguageSelectionActivity
):
    RecyclerView.Adapter<LanguageListAdapter.LanguageVH>() {
    private var lastSelectedPosition = -1
    private  val TAG = "LanguageListAdapter"

    public class LanguageVH(itemView:View):RecyclerView.ViewHolder(itemView){

        val layout_language_select = itemView.findViewById<ConstraintLayout>(R.id.layout_language_select)
        val checkBox = itemView.findViewById<RadioButton>(R.id.languageSelect)
        val language = itemView.findViewById<TextView>(R.id.language)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_languages,parent,false)
        return LanguageVH(view)
    }

    override fun onBindViewHolder(holder: LanguageVH, @SuppressLint("RecyclerView") position: Int) {

       val languageModel = languages[position]
       holder.language.text = languageModel.language
        holder.checkBox.setChecked(position == lastSelectedPosition)


        holder.checkBox.setOnClickListener { v ->
            if (position === lastSelectedPosition) {
                holder.checkBox.isChecked = false
                lastSelectedPosition = -1
           } else {
                lastSelectedPosition = position
                notifyDataSetChanged()
              Log.d(TAG, "onBindViewHolder: ${languageModel.language}")
                SharedPreferenceHelper.language=languageModel.code
            }
        }


    }



    override fun getItemCount(): Int {
        return languages.size
    }

    fun updateContent(languages_: ArrayList<LanguageModel>) {
        this.languages = languages_

    }
}