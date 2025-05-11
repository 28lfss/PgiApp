package com.lfss.pgiapp

import com.lfss.pgiapp.api.PgiApi
import com.lfss.pgiapp.api.RetrofitClient
import com.lfss.pgiapp.dto.UserLoginDTO
import com.lfss.pgiapp.model.OccurrenceModel
import com.lfss.pgiapp.model.UserModel
import retrofit2.Response

class EventRepository{
    private val api = RetrofitClient.getInstance().create(PgiApi::class.java)

    suspend fun userLogin(email: String, password: String): Response<UserModel>{
        val loginData = UserLoginDTO(email, password)
        return api.userLogin(loginData)
    }

    fun occurrencesListByUid(userUid: String): List<OccurrenceModel> {
        val occurrencesList = listOf<OccurrenceModel>(
            OccurrenceModel(1, "User UID", null, "AREA 1", "DESCRIPTION 1", 219381912L),
            OccurrenceModel(2, "User UID", null, "AREA 2", "DESCRIPTION 2", 219381912L),
            OccurrenceModel(3, "User UID", null, "AREA 3", "DESCRIPTION 3", 219381912L),
            OccurrenceModel(4, "User UID", null, "AREA 4", "DESCRIPTION 4", 219381912L),
            OccurrenceModel(5, "User UID", null, "AREA 5", "DESCRIPTION 5", 219381912L)
        )
        return occurrencesList //TODO: return List<OccurrenceModel>
    }

    fun createOccurrence(createdOccurrence: OccurrenceModel) {
        //TODO: send occurrence to back-end
    }

}