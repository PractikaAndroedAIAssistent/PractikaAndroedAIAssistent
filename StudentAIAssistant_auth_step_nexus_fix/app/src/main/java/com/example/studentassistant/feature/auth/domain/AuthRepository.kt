package com.example.studentassistant.feature.auth.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: UserRole,
        universityName: String,
        groupName: String?
    ): AuthResult
}
