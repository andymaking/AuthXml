package io.king.authxml.responses

data class Data(
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