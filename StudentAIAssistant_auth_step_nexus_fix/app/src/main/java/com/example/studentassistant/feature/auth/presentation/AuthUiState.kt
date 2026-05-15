package com.example.studentassistant.feature.auth.presentation

import com.example.studentassistant.feature.auth.domain.User
import com.example.studentassistant.feature.auth.domain.UserRole

data class AuthUiState(
    val isLoading: Boolean = false,
    val isAuthorized: Boolean = false,
    val currentUser: User? = null,
    val errorMessage: String? = null,
    val selectedRole: UserRole = UserRole.STUDENT
)
