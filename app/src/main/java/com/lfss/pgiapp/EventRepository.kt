package com.lfss.pgiapp

import com.lfss.pgiapp.model.OccurrenceModel

class EventRepository{
    fun authenticateUserLogin(userId: String, userPassword: String): Boolean{
        return true
    }

    fun occurrencesListByUid(userUid: String): List<OccurrenceModel> {
        val occurrenceList = listOf(
            OccurrenceModel(1, "AREA 1", "DESCRIPTION 1", "30/03/2025"),
            OccurrenceModel(2, "AREA 2", "DESCRIPTION 2", "01/04/2025"),
            OccurrenceModel(3, "AREA 3", "DESCRIPTION 3", "05/04/2025"),
            OccurrenceModel(4, "AREA 4", "DESCRIPTION 4", "09/04/2025"),
            OccurrenceModel(5, "AREA 5", "DESCRIPTION 5", "13/04/2025"),
            OccurrenceModel(6, "AREA 6", "DESCRIPTION 6", "22/04/2025")
        )
        return occurrenceList
    }

}