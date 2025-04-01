package com.lfss.pgiapp.listoccurrences

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lfss.pgiapp.R

class ListOccurrencesAdapter(
    private val occurrenceList: List<ListOccurrencesFragment.Occurrence>
) : RecyclerView.Adapter<ListOccurrencesAdapter.OccurrenceViewHolder>() {

    class OccurrenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val areaTextView: TextView = view.findViewById(R.id.adapter_area)
        val timeTextView: TextView = view.findViewById(R.id.adapter_time)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): OccurrenceViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_list_ocurrences, viewGroup, false)
        return OccurrenceViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: OccurrenceViewHolder, position: Int) {
        val occurrence = occurrenceList[position]
        viewHolder.areaTextView.text = occurrence.area
        viewHolder.timeTextView.text = occurrence.time
    }

    override fun getItemCount() = occurrenceList.size
}