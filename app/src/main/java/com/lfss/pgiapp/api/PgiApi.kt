package com.lfss.pgiapp.api

import com.lfss.pgiapp.dto.UserLoginDTO
import com.lfss.pgiapp.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PgiApi {
    @POST("user/login")
    suspend fun userLogin(
        @Body user: UserLoginDTO
    ): Response<UserModel>
}