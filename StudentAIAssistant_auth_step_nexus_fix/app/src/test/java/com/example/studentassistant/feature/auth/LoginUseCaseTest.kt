package com.example.studentassistant.feature.auth

import com.example.studentassistant.feature.auth.data.FakeAuthRepository
import com.example.studentassistant.feature.auth.domain.AuthResult
import com.example.studentassistant.feature.auth.domain.LoginUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = LoginUseCase(FakeAuthRepository())
    }

    @Test
    fun loginWithCorrectStudentDataShouldReturnSuccess() = runTest {
        val result = loginUseCase("student@test.ru", "123456")

        assertTrue(result is AuthResult.Success)
        val success = result as AuthResult.Success
        assertEquals("student@test.ru", success.session.user.email)
        assertEquals("Алексей", success.session.user.firstName)
        assertEquals("fake_access_token_1", success.session.accessToken)
    }

    @Test
    fun loginWithCorrectTeacherDataShouldReturnSuccess() = runTest {
        val result = loginUseCase("teacher@test.ru", "123456")

        assertTrue(result is AuthResult.Success)
        val success = result as AuthResult.Success
        assertEquals("teacher@test.ru", success.session.user.email)
        assertEquals("Мария", success.session.user.firstName)
    }

    @Test
    fun loginWithUnknownUserShouldReturnError() = runTest {
        val result = loginUseCase("unknown@test.ru", "123456")

        assertTrue(result is AuthResult.Error)
        val error = result as AuthResult.Error
        assertEquals("Пользователь не найден", error.message)
    }

    @Test
    fun loginWithWrongPasswordShouldReturnError() = runTest {
        val result = loginUseCase("student@test.ru", "wrong123")

        assertTrue(result is AuthResult.Error)
        val error = result as AuthResult.Error
        assertEquals("Неверный пароль", error.message)
    }
}
