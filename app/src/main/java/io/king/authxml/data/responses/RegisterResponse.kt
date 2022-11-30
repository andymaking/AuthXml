package io.king.authxml.data.responses

data class RegisterResponse(
    val `data`: DataX,
    val message: String,
    val status: String
)

data class DataX(
    val patient: _Patient,
    val token: String,
    val user: _User,
    val wallet: _Wallet
)

data class _Wallet(
    val created_at: String,
    val id: Int,
    val updated_at: String,
    val user_id: Int
)

data class _User(
    val created_at: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val phone_number: String,
    val role_id: Int,
    val updated_at: String
)

data class _Patient(
    val card_id: String,
    val created_at: String,
    val id: Int,
    val updated_at: String,
    val user_id: Int
)