package com.lfss.pgiapp.listoccurrences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lfss.pgiapp.R
import com.lfss.pgiapp.model.OccurrenceModel

class ListOccurrencesAdapter(
    private val occurrenceList: List<OccurrenceModel>,
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<ListOccurrencesViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListOccurrencesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_list_ocurrences, viewGroup, false)
        return ListOccurrencesViewHolder(view) {
            itemId -> onItemClick()
        }
    }

    override fun onBindViewHolder(viewHolder: ListOccurrencesViewHolder, position: Int) {
        val occurrence = occurrenceList[position]
        viewHolder.bind(occurrence.area, occurrence.timestamp)
    }

    override fun getItemCount() = occurrenceList.size
}