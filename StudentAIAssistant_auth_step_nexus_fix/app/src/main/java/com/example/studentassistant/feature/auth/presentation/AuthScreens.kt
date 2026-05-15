package com.example.studentassistant.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studentassistant.feature.auth.domain.UserRole
import com.example.studentassistant.ui.components.SoftCard
import com.example.studentassistant.ui.theme.BackgroundLight
import com.example.studentassistant.ui.theme.BlueSoft
import com.example.studentassistant.ui.theme.PurpleLight
import com.example.studentassistant.ui.theme.PurplePrimary
import com.example.studentassistant.ui.theme.TextSecondary

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(PurplePrimary, Color(0xFF8B75FF), Color(0xFFF8F7FF))
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(108.dp)
                    .clip(RoundedCornerShape(34.dp))
                    .background(Color.White.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(54.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "AI-ассистент\nстудента",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Расписание, конспекты, дедлайны\nи AI в одном приложении",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(44.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Войти",
                    color = PurplePrimary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            TextButton(onClick = onRegisterClick) {
                Text("Создать аккаунт", color = Color.White)
            }
        }
    }
}

@Composable
fun LoginScreen(
    state: AuthUiState,
    onLogin: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("student@test.ru") }
    var password by rememberSaveable { mutableStateOf("123456") }

    AuthContainer {
        Text(
            text = "Добро пожаловать 👋",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Войдите в аккаунт, чтобы продолжить учёбу",
            color = TextSecondary,
            modifier = Modifier.padding(top = 4.dp, bottom = 22.dp)
        )

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            leadingIcon = Icons.Rounded.Email,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Пароль",
            leadingIcon = Icons.Rounded.Lock,
            isPassword = true
        )

        ErrorBlock(state.errorMessage)

        PrimaryAuthButton(
            text = "Войти",
            isLoading = state.isLoading,
            onClick = { onLogin(email, password) }
        )

        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Нет аккаунта? Регистрация")
        }
    }
}

@Composable
fun RegisterScreen(
    state: AuthUiState,
    onRoleSelected: (UserRole) -> Unit,
    onRegister: (String, String, String, String, String, String?) -> Unit,
    onLoginClick: () -> Unit
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var universityName by rememberSaveable { mutableStateOf("Колледж информационных технологий") }
    var groupName by rememberSaveable { mutableStateOf("ИСП-23") }

    AuthContainer {
        Text(
            text = "Создание аккаунта",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Выберите роль и заполните профиль",
            color = TextSecondary,
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            FilterChip(
                selected = state.selectedRole == UserRole.STUDENT,
                onClick = { onRoleSelected(UserRole.STUDENT) },
                label = { Text("Студент") },
                leadingIcon = { Icon(Icons.Rounded.School, contentDescription = null) }
            )
            FilterChip(
                selected = state.selectedRole == UserRole.TEACHER,
                onClick = { onRoleSelected(UserRole.TEACHER) },
                label = { Text("Преподаватель") },
                leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = null) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(firstName, { firstName = it }, "Имя", Icons.Rounded.Person)
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextField(lastName, { lastName = it }, "Фамилия", Icons.Rounded.Person)
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextField(email, { email = it }, "Email", Icons.Rounded.Email, KeyboardType.Email)
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextField(password, { password = it }, "Пароль", Icons.Rounded.Lock, isPassword = true)
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextField(universityName, { universityName = it }, "Учебное заведение", Icons.Rounded.School)

        if (state.selectedRole == UserRole.STUDENT) {
            Spacer(modifier = Modifier.height(10.dp))
            AuthTextField(groupName, { groupName = it }, "Группа", Icons.Rounded.School)
        }

        ErrorBlock(state.errorMessage)

        PrimaryAuthButton(
            text = "Зарегистрироваться",
            isLoading = state.isLoading,
            onClick = {
                onRegister(
                    firstName,
                    lastName,
                    email,
                    password,
                    universityName,
                    groupName.takeIf { state.selectedRole == UserRole.STUDENT }
                )
            }
        )

        TextButton(
            onClick = onLoginClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Уже есть аккаунт? Войти")
        }
    }
}

@Composable
private fun AuthContainer(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        SoftCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(22.dp),
                content = content
            )
        }
    }
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(leadingIcon, contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PurplePrimary,
            focusedLabelColor = PurplePrimary,
            cursorColor = PurplePrimary,
            focusedLeadingIconColor = PurplePrimary,
            unfocusedContainerColor = BlueSoft.copy(alpha = 0.35f),
            focusedContainerColor = Color.White
        )
    )
}

@Composable
private fun ErrorBlock(message: String?) {
    if (message != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFE8E8))
                .padding(12.dp)
        ) {
            Text(
                text = message,
                color = Color(0xFFE53935),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun PrimaryAuthButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(18.dp))
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = !isLoading,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(22.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
