package com.lfss.pgiapp.main.components

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lfss.pgiapp.R
import com.lfss.pgiapp.model.OccurrenceModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ListOccurrencesViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    val areaTextView: TextView = view.findViewById(R.id.adapter_area)
    val timeTextView: TextView = view.findViewById(R.id.adapter_time)

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
        .withZone(ZoneId.systemDefault())

    fun bind(occurrence: OccurrenceModel, onItemClicked: () -> Unit) {
        areaTextView.text = occurrence.area
        occurrence.timeCreated?.let { timeCreated->
            timeTextView.text = dateFormatter.format(Instant.ofEpochMilli(timeCreated))
        } ?: {
            timeTextView.text = " "
        }
        timeTextView.text = dateFormatter.format(Instant.ofEpochMilli(occurrence.timeCreated!!))
        itemView.setOnClickListener {
                onItemClicked()
        }
    }
}