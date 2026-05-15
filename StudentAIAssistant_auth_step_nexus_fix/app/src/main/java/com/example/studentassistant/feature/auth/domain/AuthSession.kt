package com.example.studentassistant.feature.auth.domain

data class AuthSession(
    val user: User,
    val accessToken: String,
    val isFirstLogin: Boolean = false
)
