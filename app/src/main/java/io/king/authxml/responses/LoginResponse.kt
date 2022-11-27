package io.king.authxml.responses

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val status: String,
    val token: String
)