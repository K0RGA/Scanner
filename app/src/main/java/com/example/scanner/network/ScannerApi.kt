package com.example.scanner.network

import com.example.scanner.data.model.ResponseLoginDTO
import com.example.scanner.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ScannerApi {
    @POST("/api/UserLogin/log")
    suspend fun login (@Body userDTO: UserDTO): Response<ResponseLoginDTO>
}