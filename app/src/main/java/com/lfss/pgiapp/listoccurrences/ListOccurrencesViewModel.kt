package com.lfss.pgiapp.listoccurrences

import androidx.lifecycle.ViewModel
import com.lfss.pgiapp.EventRepository
import com.lfss.pgiapp.model.OccurrenceModel

class ListOccurrencesViewModel: ViewModel() {
    val eventRepository: EventRepository = EventRepository()

    fun getUserOccurrencesList(userUid: String): List<OccurrenceModel> {
        return eventRepository.occurrencesListByUid(userUid)
    }
}