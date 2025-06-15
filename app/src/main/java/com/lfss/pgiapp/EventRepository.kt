package com.lfss.pgiapp

import com.lfss.pgiapp.api.PgiApi
import com.lfss.pgiapp.api.RetrofitClient
import com.lfss.pgiapp.dto.UserLoginDTO
import com.lfss.pgiapp.model.OccurrenceModel
import com.lfss.pgiapp.model.UserModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class EventRepository {
    private val api = RetrofitClient.getInstance().create(PgiApi::class.java)

    suspend fun userLogin(email: String, password: String): Response<UserModel> {
        val loginData = UserLoginDTO(email, password)
        return api.userLogin(loginData)
    }

    suspend fun createOccurrence(
        occurrence: OccurrenceModel,
        occurrenceImage: MultipartBody.Part
    ): Response<OccurrenceModel> {
        val area = occurrence.area
            .toRequestBody("text/plain".toMediaTypeOrNull())

        val description = occurrence.description
            .toRequestBody("text/plain".toMediaTypeOrNull())

        val userId = occurrence.registrantId.toString()
            .toRequestBody("text/plain".toMediaTypeOrNull())

        return api.createOccurrence(
            area = area,
            description = description,
            occurrenceRegistrantId = userId,
            imageFile = occurrenceImage
        )
    }

    fun occurrencesListByUid(userUid: String): List<OccurrenceModel> {
        val occurrencesList = listOf<OccurrenceModel>(
            OccurrenceModel(1, "AREA 1", "DESCRIPTION 1", "image_path", 12345, 1),
            OccurrenceModel(2, "AREA 2", "DESCRIPTION 2", "image_path", 12345, 1),
            OccurrenceModel(3, "AREA 3", "DESCRIPTION 3", "image_path", 12345, 1),
            OccurrenceModel(4, "AREA 4", "DESCRIPTION 4", "image_path", 12345, 1),
            OccurrenceModel(5, "AREA 5", "DESCRIPTION 5", "image_path", 12345, 1)
        )
        return occurrencesList //TODO: return List<OccurrenceModel>
    }

}