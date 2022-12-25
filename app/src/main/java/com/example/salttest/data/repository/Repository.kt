package com.example.salttest.data.repository

import com.example.salttest.data.model.LoginRequest
import com.example.salttest.data.model.LoginResponse
import java.util.concurrent.Flow

interface Repository {
  suspend fun getLogin(loginRequest: LoginRequest):Result<LoginResponse>
}