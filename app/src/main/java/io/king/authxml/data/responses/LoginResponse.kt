package io.king.authxml.data.responses

data class LoginResponse(
    val `data`: _Data,
    val message: String,
    val status: String,
    val token: String
)

data class _Data(
    val created_at: String,
    val email: String,
    val email_verified_at: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val phone_number: String,
    val role_id: String,
    val updated_at: String
)