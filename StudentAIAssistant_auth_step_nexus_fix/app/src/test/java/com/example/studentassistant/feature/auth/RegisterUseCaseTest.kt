package com.example.studentassistant.feature.auth

import com.example.studentassistant.feature.auth.data.FakeAuthRepository
import com.example.studentassistant.feature.auth.domain.AuthResult
import com.example.studentassistant.feature.auth.domain.RegisterUseCase
import com.example.studentassistant.feature.auth.domain.UserRole
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RegisterUseCaseTest {
    private lateinit var registerUseCase: RegisterUseCase

    @Before
    fun setup() {
        registerUseCase = RegisterUseCase(FakeAuthRepository())
    }

    @Test
    fun registerStudentWithCorrectDataShouldReturnSuccess() = runTest {
        val result = registerUseCase(
            firstName = "Иван",
            lastName = "Смирнов",
            email = "ivan@test.ru",
            password = "123456",
            role = UserRole.STUDENT,
            universityName = "Колледж информационных технологий",
            groupName = "ИСП-23"
        )

        assertTrue(result is AuthResult.Success)
        val success = result as AuthResult.Success
        assertEquals("ivan@test.ru", success.session.user.email)
        assertEquals(UserRole.STUDENT, success.session.user.role)
        assertEquals("ИСП-23", success.session.user.groupName)
    }

    @Test
    fun registerTeacherWithCorrectDataShouldReturnSuccess() = runTest {
        val result = registerUseCase(
            firstName = "Ольга",
            lastName = "Иванова",
            email = "olga@test.ru",
            password = "123456",
            role = UserRole.TEACHER,
            universityName = "Колледж информационных технологий",
            groupName = "ИСП-23"
        )

        assertTrue(result is AuthResult.Success)
        val success = result as AuthResult.Success
        assertEquals(UserRole.TEACHER, success.session.user.role)
        assertEquals(null, success.session.user.groupName)
    }

    @Test
    fun registerWithExistingEmailShouldReturnError() = runTest {
        val result = registerUseCase(
            firstName = "Алексей",
            lastName = "Студентов",
            email = "student@test.ru",
            password = "123456",
            role = UserRole.STUDENT,
            universityName = "Колледж информационных технологий",
            groupName = "ИСП-23"
        )

        assertTrue(result is AuthResult.Error)
        val error = result as AuthResult.Error
        assertEquals("Пользователь с таким email уже существует", error.message)
    }

    @Test
    fun registerWithShortPasswordShouldReturnValidationError() = runTest {
        val result = registerUseCase(
            firstName = "Иван",
            lastName = "Смирнов",
            email = "ivan@test.ru",
            password = "123",
            role = UserRole.STUDENT,
            universityName = "Колледж информационных технологий",
            groupName = "ИСП-23"
        )

        assertTrue(result is AuthResult.Error)
        val error = result as AuthResult.Error
        assertEquals("Пароль должен содержать минимум 6 символов", error.message)
    }
}
