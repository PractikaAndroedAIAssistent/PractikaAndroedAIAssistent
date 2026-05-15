package com.example.studentassistant.feature.auth.domain

data class User(
    val id: Long,
    val email: String,
    val role: UserRole,
    val firstName: String,
    val lastName: String,
    val universityName: String,
    val groupName: String? = null,
    val course: Int? = null
) {
    val fullName: String
        get() = "$firstName $lastName".trim()
}
