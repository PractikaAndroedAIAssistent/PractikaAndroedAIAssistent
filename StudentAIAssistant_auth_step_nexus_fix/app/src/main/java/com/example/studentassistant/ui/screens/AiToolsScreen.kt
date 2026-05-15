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
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.PictureAsPdf
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.Style
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentassistant.ui.components.QuickActionCard
import com.example.studentassistant.ui.components.SectionTitle
import com.example.studentassistant.ui.components.SoftCard
import com.example.studentassistant.ui.theme.BackgroundLight
import com.example.studentassistant.ui.theme.BlueSoft
import com.example.studentassistant.ui.theme.GreenSoft
import com.example.studentassistant.ui.theme.OrangeSoft
import com.example.studentassistant.ui.theme.PurpleLight
import com.example.studentassistant.ui.theme.PurplePrimary
import com.example.studentassistant.ui.theme.SuccessGreen
import com.example.studentassistant.ui.theme.TextSecondary

@Composable
fun AiToolsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundLight).verticalScroll(rememberScrollState()).padding(top = 44.dp, bottom = 20.dp)
    ) {
        Text("AI-инструменты", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(horizontal = 20.dp))
        Text("PDF, тесты, карточки и аналитика", color = TextSecondary, modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp))
        SectionTitle("Чат по PDF")
        ChatPreviewCard()
        SectionTitle("Создать через AI")
        Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard("Суммаризация", "Краткий конспект", Icons.Rounded.AutoAwesome, PurpleLight, Modifier.weight(1f))
            QuickActionCard("Тест", "Проверка знаний", Icons.Rounded.Quiz, BlueSoft, Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
        Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard("Карточки", "Повторение", Icons.Rounded.Style, GreenSoft, Modifier.weight(1f))
            QuickActionCard("PDF", "Загрузить файл", Icons.Rounded.PictureAsPdf, OrangeSoft, Modifier.weight(1f))
        }
        SectionTitle("Аналитика")
        AnalyticsCard()
    }
}

@Composable
private fun ChatPreviewCard() {
    SoftCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.align(Alignment.End).clip(RoundedCornerShape(22.dp)).background(PurplePrimary).padding(14.dp)) {
                Text("Объясни тему из PDF простыми словами", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(12.dp))
            Box(modifier = Modifier.clip(RoundedCornerShape(22.dp)).background(PurpleLight).padding(14.dp)) {
                Text("Конечно. Я выделю главное, объясню термины и сделаю короткий конспект для подготовки.", color = Color(0xFF30276B), style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AiChip("Суммаризация")
                AiChip("Тест")
                AiChip("Карточки")
            }
        }
    }
}

@Composable
private fun AiChip(text: String) {
    AssistChip(
        onClick = {},
        label = { Text(text) },
        colors = AssistChipDefaults.assistChipColors(containerColor = PurpleLight, labelColor = PurplePrimary)
    )
}

@Composable
private fun AnalyticsCard() {
    SoftCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.BarChart, contentDescription = null, tint = PurplePrimary)
                Text("Успеваемость", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(start = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
            Text("Средний балл: 4.3 / 5.0", style = MaterialTheme.typography.titleMedium)
            Text("+0.4 за месяц", color = SuccessGreen, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(14.dp))
            SubjectProgress("Математическое моделирование", 0.86f, "4.7")
            SubjectProgress("МДК 02.02", 0.78f, "4.2")
            SubjectProgress("История", 0.72f, "4.0")
        }
    }
}

@Composable
private fun SubjectProgress(title: String, progress: Float, grade: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row {
            Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
            Text(grade, color = PurplePrimary, style = MaterialTheme.typography.bodyMedium)
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(20.dp)),
            color = PurplePrimary,
            trackColor = PurpleLight
        )
    }
}
