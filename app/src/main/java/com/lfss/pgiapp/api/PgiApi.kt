package com.lfss.pgiapp.api

import com.lfss.pgiapp.dto.UserLoginDTO
import com.lfss.pgiapp.model.OccurrenceModel
import com.lfss.pgiapp.model.UserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PgiApi {
    // USER ENDPOINTS
    @POST("user/login")
    suspend fun userLogin(
        @Body user: UserLoginDTO
    ): Response<UserModel>

    // OCCURRENCE ENDPOINTS
    @Multipart
    @POST("occurrence")
    suspend fun createOccurrence(
        @Part("area") area: RequestBody,
        @Part("description") description: RequestBody,
        @Part("occurrenceRegistrantId") occurrenceRegistrantId: RequestBody,
        @Part imageFile: MultipartBody.Part
    ): Response<OccurrenceModel>

    @GET("occurrence/all/{id}")
    suspend fun getUserOccurrences(
        @Path("id") userId: Long
    ): Response<List<OccurrenceModel>>
}