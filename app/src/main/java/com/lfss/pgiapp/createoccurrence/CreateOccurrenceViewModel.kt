package com.lfss.pgiapp.createoccurrence

import androidx.lifecycle.ViewModel
import com.lfss.pgiapp.EventRepository
import com.lfss.pgiapp.model.OccurrenceModel

class CreateOccurrenceViewModel : ViewModel() {

    val eventRepository: EventRepository = EventRepository()

    fun createOccurrence(createdOccurrence: OccurrenceModel) {
        eventRepository.createOccurrence(createdOccurrence)
    }
}