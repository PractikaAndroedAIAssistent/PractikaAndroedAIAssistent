package com.example.studentassistant.feature.auth.domain

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: UserRole,
        universityName: String,
        groupName: String?
    ): AuthResult {
        val validationError = AuthValidator.validateRegister(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            universityName = universityName
        )
        if (validationError != null) return AuthResult.Error(validationError)

        return repository.register(
            firstName = firstName.trim(),
            lastName = lastName.trim(),
            email = email.trim(),
            password = password,
            role = role,
            universityName = universityName.trim(),
            groupName = groupName?.trim()?.takeIf { it.isNotEmpty() }
        )
    }
}
