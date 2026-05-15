package com.example.studentassistant.feature.auth.data

import com.example.studentassistant.feature.auth.domain.AuthRepository
import com.example.studentassistant.feature.auth.domain.AuthResult
import com.example.studentassistant.feature.auth.domain.AuthSession
import com.example.studentassistant.feature.auth.domain.User
import com.example.studentassistant.feature.auth.domain.UserRole

class FakeAuthRepository : AuthRepository {
    private val users = mutableListOf(
        User(
            id = 1L,
            email = "student@test.ru",
            role = UserRole.STUDENT,
            firstName = "Алексей",
            lastName = "Студентов",
            universityName = "Колледж информационных технологий",
            groupName = "ИСП-23",
            course = 3
        ),
        User(
            id = 2L,
            email = "teacher@test.ru",
            role = UserRole.TEACHER,
            firstName = "Мария",
            lastName = "Петрова",
            universityName = "Колледж информационных технологий"
        )
    )

    private val passwords = mutableMapOf(
        "student@test.ru" to "123456",
        "teacher@test.ru" to "123456"
    )

    override suspend fun login(email: String, password: String): AuthResult {
        val user = users.firstOrNull { it.email.equals(email, ignoreCase = true) }
            ?: return AuthResult.Error("Пользователь не найден")

        if (passwords[user.email] != password) {
            return AuthResult.Error("Неверный пароль")
        }

        return AuthResult.Success(
            AuthSession(
                user = user,
                accessToken = "fake_access_token_${user.id}"
            )
        )
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: UserRole,
        universityName: String,
        groupName: String?
    ): AuthResult {
        val normalizedEmail = email.lowercase()

        if (users.any { it.email.equals(normalizedEmail, ignoreCase = true) }) {
            return AuthResult.Error("Пользователь с таким email уже существует")
        }

        val newUser = User(
            id = (users.maxOfOrNull { it.id } ?: 0L) + 1L,
            email = normalizedEmail,
            role = role,
            firstName = firstName,
            lastName = lastName,
            universityName = universityName,
            groupName = if (role == UserRole.STUDENT) groupName else null,
            course = if (role == UserRole.STUDENT) 1 else null
        )

        users.add(newUser)
        passwords[normalizedEmail] = password

        return AuthResult.Success(
            AuthSession(
                user = newUser,
                accessToken = "fake_access_token_${newUser.id}",
                isFirstLogin = true
            )
        )
    }
}
