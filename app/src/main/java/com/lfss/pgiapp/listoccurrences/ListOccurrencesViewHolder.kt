package com.lfss.pgiapp.listoccurrences

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lfss.pgiapp.R
import com.lfss.pgiapp.model.OccurrenceModel

class ListOccurrencesViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    val areaTextView: TextView = view.findViewById(R.id.adapter_area)
    val timeTextView: TextView = view.findViewById(R.id.adapter_time)

    fun bind(occurrence: OccurrenceModel, onItemClicked: () -> Unit) {
        areaTextView.text = occurrence.area
        timeTextView.text = occurrence.timestamp.toString() //TODO: format timestamp to date/time
        itemView.setOnClickListener {
                onItemClicked()
        }
    }
}