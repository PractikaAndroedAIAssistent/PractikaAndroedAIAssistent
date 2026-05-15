package com.example.studentassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentassistant.ui.components.QuickActionCard
import com.example.studentassistant.ui.theme.BackgroundLight
import com.example.studentassistant.ui.theme.BlueSoft
import com.example.studentassistant.ui.theme.PurpleLight
import com.example.studentassistant.ui.theme.TextSecondary

@Composable
fun NotesScreen() {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight).padding(top = 44.dp, start = 20.dp, end = 20.dp)) {
        Text("Конспекты", style = MaterialTheme.typography.headlineMedium)
        Text("Сохраняй материалы и создавай краткие версии через AI", color = TextSecondary, modifier = Modifier.padding(top = 4.dp, bottom = 16.dp))
        QuickActionCard("Математическое моделирование", "5 конспектов • 2 PDF", Icons.Rounded.NoteAlt, PurpleLight)
        QuickActionCard("МДК 02.02", "8 конспектов • 1 PDF", Icons.Rounded.Description, BlueSoft, modifier = Modifier.padding(top = 12.dp))
    }
}
