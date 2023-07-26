package com.example.iecagto.apis

import com.example.iecagto.datas.LoginRequest
import com.example.iecagto.datas.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}