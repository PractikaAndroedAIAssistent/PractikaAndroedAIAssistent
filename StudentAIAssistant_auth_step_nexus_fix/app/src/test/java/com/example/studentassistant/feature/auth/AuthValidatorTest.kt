package com.example.studentassistant.feature.auth

import com.example.studentassistant.feature.auth.domain.AuthValidator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthValidatorTest {
    @Test
    fun emptyEmailShouldBeInvalid() {
        assertFalse(AuthValidator.isEmailValid(""))
    }

    @Test
    fun wrongEmailShouldBeInvalid() {
        assertFalse(AuthValidator.isEmailValid("studenttest.ru"))
    }

    @Test
    fun correctEmailShouldBeValid() {
        assertTrue(AuthValidator.isEmailValid("student@test.ru"))
    }

    @Test
    fun shortPasswordShouldBeInvalid() {
        assertFalse(AuthValidator.isPasswordValid("123"))
    }

    @Test
    fun passwordWithSixSymbolsShouldBeValid() {
        assertTrue(AuthValidator.isPasswordValid("123456"))
    }

    @Test
    fun oneSymbolNameShouldBeInvalid() {
        assertFalse(AuthValidator.isNameValid("А"))
    }

    @Test
    fun normalNameShouldBeValid() {
        assertTrue(AuthValidator.isNameValid("Алексей"))
    }

    @Test
    fun loginValidationShouldReturnEmailError() {
        val result = AuthValidator.validateLogin("wrong", "123456")
        assertEquals("Некорректный email", result)
    }

    @Test
    fun loginValidationShouldReturnPasswordError() {
        val result = AuthValidator.validateLogin("student@test.ru", "123")
        assertEquals("Пароль должен содержать минимум 6 символов", result)
    }

    @Test
    fun loginValidationShouldReturnNullForCorrectData() {
        val result = AuthValidator.validateLogin("student@test.ru", "123456")
        assertNull(result)
    }

    @Test
    fun registerValidationShouldReturnUniversityError() {
        val result = AuthValidator.validateRegister(
            firstName = "Алексей",
            lastName = "Иванов",
            email = "student@test.ru",
            password = "123456",
            universityName = ""
        )
        assertEquals("Укажите учебное заведение", result)
    }
}
