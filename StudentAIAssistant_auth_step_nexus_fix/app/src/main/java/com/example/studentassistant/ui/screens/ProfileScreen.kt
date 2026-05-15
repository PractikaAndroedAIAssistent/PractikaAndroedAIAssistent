package com.example.studentassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentassistant.ui.components.QuickActionCard
import com.example.studentassistant.ui.theme.BackgroundLight
import com.example.studentassistant.ui.theme.BlueSoft
import com.example.studentassistant.ui.theme.GreenSoft
import com.example.studentassistant.ui.theme.PurpleLight
import com.example.studentassistant.ui.theme.PurplePrimary
import com.example.studentassistant.ui.theme.TextSecondary

@Composable
fun ProfileScreen(
    userName: String = "Студент",
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(20.dp)
    ) {
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "$userName • студент • AI-ассистент",
            color = TextSecondary,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        QuickActionCard(
            title = "Мой профиль",
            subtitle = "Данные аккаунта и роль",
            icon = Icons.Rounded.Person,
            backgroundColor = PurpleLight
        )

        QuickActionCard(
            title = "Уведомления",
            subtitle = "Пары, дедлайны, карточки",
            icon = Icons.Rounded.Notifications,
            backgroundColor = BlueSoft,
            modifier = Modifier.padding(top = 12.dp)
        )

        QuickActionCard(
            title = "Настройки",
            subtitle = "Тема, язык, AI",
            icon = Icons.Rounded.Settings,
            backgroundColor = GreenSoft,
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
        ) {
            Text(
                text = "Выйти из аккаунта",
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
