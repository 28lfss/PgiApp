package com.lfss.pgiapp.listoccurrences

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lfss.pgiapp.R

class ListOccurrencesViewHolder(
    view: View,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    val areaTextView: TextView = view.findViewById(R.id.adapter_area)
    val timeTextView: TextView = view.findViewById(R.id.adapter_time)

    init {
        itemView.setOnClickListener {
            onItemClicked(itemView.id)
        }
    }

}