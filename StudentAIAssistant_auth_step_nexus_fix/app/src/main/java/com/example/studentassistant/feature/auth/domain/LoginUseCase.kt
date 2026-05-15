package com.example.studentassistant.feature.auth.domain

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        val validationError = AuthValidator.validateLogin(email, password)
        if (validationError != null) return AuthResult.Error(validationError)

        return repository.login(email.trim(), password)
    }
}
