package com.example.salttest.data.repository

import com.example.salttest.data.model.LoginRequest
import com.example.salttest.data.model.LoginResponse
import com.example.salttest.data.network.ApiServices

class RepositoryImpl(
    private val api: ApiServices
) : Repository {
    override suspend fun getLogin(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val data = api.getLogin(loginRequest)
            Result.success(
                data
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}