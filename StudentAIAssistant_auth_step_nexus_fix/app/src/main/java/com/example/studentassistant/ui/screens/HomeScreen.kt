package com.example.studentassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material.icons.rounded.PictureAsPdf
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentassistant.ui.components.DeadlineCard
import com.example.studentassistant.ui.components.QuickActionCard
import com.example.studentassistant.ui.components.SectionTitle
import com.example.studentassistant.ui.components.SoftCard
import com.example.studentassistant.ui.theme.BackgroundLight
import com.example.studentassistant.ui.theme.BlueSoft
import com.example.studentassistant.ui.theme.DangerRed
import com.example.studentassistant.ui.theme.GreenSoft
import com.example.studentassistant.ui.theme.OrangeSoft
import com.example.studentassistant.ui.theme.PurpleLight
import com.example.studentassistant.ui.theme.PurplePrimary
import com.example.studentassistant.ui.theme.SuccessGreen
import com.example.studentassistant.ui.theme.TextSecondary
import com.example.studentassistant.ui.theme.WarningOrange

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
            .padding(top = 44.dp, bottom = 20.dp)
    ) {
        Header()
        SectionTitle(title = "Ближайшая пара")
        NextLessonCard()
        SectionTitle(title = "Дедлайны", actionText = "Все")
        DeadlineCard("22\nМАЙ", "Домашнее задание №3", "Математическое моделирование", "Высокий", DangerRed)
        DeadlineCard("24\nМАЙ", "Презентация", "История философии", "Средний", WarningOrange)
        SectionTitle(title = "Быстрые действия")
        Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard("Конспекты", "Мои записи", Icons.Rounded.NoteAlt, PurpleLight, Modifier.weight(1f))
            QuickActionCard("AI по PDF", "Чат с документами", Icons.Rounded.PictureAsPdf, BlueSoft, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard("Тесты", "Проверка знаний", Icons.Rounded.TaskAlt, GreenSoft, Modifier.weight(1f))
            QuickActionCard("AI", "Помощник", Icons.Rounded.AutoAwesome, OrangeSoft, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        AverageGradeCard()
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Привет, студент! 👋", style = MaterialTheme.typography.headlineMedium)
            Text("Хорошего и продуктивного дня", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
        }
        Box(modifier = Modifier.clip(RoundedCornerShape(18.dp)).background(PurpleLight).padding(12.dp)) {
            Icon(Icons.Rounded.School, contentDescription = null, tint = PurplePrimary)
        }
    }
}

@Composable
private fun NextLessonCard() {
    SoftCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Row(modifier = Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(PurpleLight).padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("10:00\n11:30", color = PurplePrimary, style = MaterialTheme.typography.titleMedium)
            }
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text("Математическое моделирование", style = MaterialTheme.typography.titleMedium)
                Text("Лекция • 305 ауд.", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                Text("Петров А. В.", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun AverageGradeCard() {
    SoftCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Средний балл", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                Text("4.3", style = MaterialTheme.typography.headlineLarge, color = PurplePrimary)
                Text("Хорошая динамика +0.4", color = SuccessGreen, style = MaterialTheme.typography.bodyMedium)
            }
            Box(modifier = Modifier.clip(RoundedCornerShape(24.dp)).background(PurplePrimary).padding(horizontal = 20.dp, vertical = 14.dp)) {
                Text("86%", color = Color.White, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
