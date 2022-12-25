package com.example.salttest.data.use_case

import com.example.salttest.data.model.LoginRequest
import com.example.salttest.data.model.LoginResponse
import com.example.salttest.data.repository.Repository

class GetLogin(private val repository: Repository) {

    suspend operator fun invoke(
        loginRequest: LoginRequest
    ) :Result<LoginResponse>{
        return repository.getLogin(loginRequest)
    }
}