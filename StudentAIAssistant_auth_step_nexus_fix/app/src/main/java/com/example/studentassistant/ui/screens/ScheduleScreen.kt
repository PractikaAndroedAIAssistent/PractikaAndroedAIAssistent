package com.example.studentassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studentassistant.ui.components.DeadlineCard
import com.example.studentassistant.ui.components.LessonCard
import com.example.studentassistant.ui.components.SectionTitle
import com.example.studentassistant.ui.theme.BackgroundLight
import com.example.studentassistant.ui.theme.DangerRed
import com.example.studentassistant.ui.theme.PurplePrimary
import com.example.studentassistant.ui.theme.TextSecondary
import com.example.studentassistant.ui.theme.WarningOrange

@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundLight).verticalScroll(rememberScrollState()).padding(top = 44.dp, bottom = 20.dp)
    ) {
        Text("Расписание", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(horizontal = 20.dp))
        Text("Вторник, 20 мая", color = TextSecondary, modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp))
        WeekRow()
        SectionTitle("Пары сегодня")
        LessonCard("08:30", "Физика", "Лекция", "204 ауд.", "Иванов И. И.", Color(0xFF3F8CFF))
        LessonCard("10:00", "Математическое моделирование", "Лекция", "305 ауд.", "Петров А. В.", PurplePrimary)
        LessonCard("12:00", "История философии", "Семинар", "101 ауд.", "Смирнова Е. С.", Color(0xFF2EAD62))
        SectionTitle("Ближайшие дедлайны", "Все")
        DeadlineCard("22\nМАЙ", "Домашнее задание №3", "Математическое моделирование", "Высокий", DangerRed)
        DeadlineCard("24\nМАЙ", "Презентация", "История философии", "Средний", WarningOrange)
    }
}

@Composable
private fun WeekRow() {
    val days = listOf("ПН\n19", "ВТ\n20", "СР\n21", "ЧТ\n22", "ПТ\n23", "СБ\n24", "ВС\n25")
    Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        days.forEachIndexed { index, day ->
            val selected = index == 1
            Box(
                modifier = Modifier.width(42.dp).clip(RoundedCornerShape(16.dp)).background(if (selected) PurplePrimary else Color.White).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(day, color = if (selected) Color.White else TextSecondary, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
