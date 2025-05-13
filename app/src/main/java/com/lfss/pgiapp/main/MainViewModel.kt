package com.lfss.pgiapp.main

import androidx.lifecycle.ViewModel
import com.lfss.pgiapp.EventRepository
import com.lfss.pgiapp.model.OccurrenceModel

class MainViewModel : ViewModel() {
    val eventRepository: EventRepository = EventRepository()

    fun createOccurrence(createdOccurrence: OccurrenceModel) {
        eventRepository.createOccurrence(createdOccurrence)
    }

    fun getUserOccurrencesList(userUid: String): List<OccurrenceModel> {
        return eventRepository.occurrencesListByUid(userUid)
    }
}