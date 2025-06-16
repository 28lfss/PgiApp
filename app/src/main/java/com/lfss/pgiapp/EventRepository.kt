package com.lfss.pgiapp

import android.util.Log
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

    suspend fun occurrencesListByUid(userId: Long): List<OccurrenceModel>? {
        val response = api.getUserOccurrences(userId)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body
            } else {
                Log.e("OCCURRENCES LIST", "Response body is null")
                throw IllegalArgumentException("Response body is null")
            }
        } else {
            Log.e("OCCURRENCES LIST", "API call failed: ${response.errorBody()?.string()}")
            throw IllegalArgumentException("API call failed with code: ${response.code()}")
        }
    }
}