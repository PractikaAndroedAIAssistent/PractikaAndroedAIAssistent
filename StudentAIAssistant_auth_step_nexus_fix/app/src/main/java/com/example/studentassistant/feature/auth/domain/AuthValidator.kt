package com.example.studentassistant.feature.auth.domain

object AuthValidator {
    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && emailRegex.matches(email.trim())
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun isNameValid(name: String): Boolean {
        return name.trim().length >= 2
    }

    fun isUniversityValid(universityName: String): Boolean {
        return universityName.trim().length >= 2
    }

    fun validateLogin(email: String, password: String): String? {
        if (!isEmailValid(email)) return "Некорректный email"
        if (!isPasswordValid(password)) return "Пароль должен содержать минимум 6 символов"
        return null
    }

    fun validateRegister(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        universityName: String
    ): String? {
        if (!isNameValid(firstName)) return "Имя должно содержать минимум 2 символа"
        if (!isNameValid(lastName)) return "Фамилия должна содержать минимум 2 символа"
        if (!isEmailValid(email)) return "Некорректный email"
        if (!isPasswordValid(password)) return "Пароль должен содержать минимум 6 символов"
        if (!isUniversityValid(universityName)) return "Укажите учебное заведение"
        return null
    }
}
