package com.example.salttest.data.model

import com.squareup.moshi.Json

data class LoginResponse(

    @field:Json(name = "token")
    val token: String

)