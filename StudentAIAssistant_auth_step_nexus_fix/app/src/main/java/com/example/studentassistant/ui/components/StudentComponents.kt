package com.example.studentassistant.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.studentassistant.ui.theme.PurpleLight
import com.example.studentassistant.ui.theme.PurplePrimary
import com.example.studentassistant.ui.theme.RedSoft
import com.example.studentassistant.ui.theme.TextSecondary

@Composable
fun SectionTitle(title: String, actionText: String? = null) {
    Row(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
        if (actionText != null) {
            Text(text = actionText, color = PurplePrimary, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun SoftCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) { content() }
}

@Composable
fun QuickActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    SoftCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor)
                    .padding(10.dp)
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = PurplePrimary)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = subtitle, color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun DeadlinePriorityBadge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }
}

@Composable
fun LessonCard(
    time: String,
    title: String,
    type: String,
    room: String,
    teacher: String,
    accentColor: Color = PurplePrimary
) {
    SoftCard(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(18.dp)).background(PurpleLight).padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Rounded.Timer, contentDescription = null, tint = PurplePrimary)
                    Text(text = time, color = PurplePrimary, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = "$type • $room", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                Text(text = teacher, color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
            }
            Box(modifier = Modifier.height(48.dp).width(5.dp).clip(CircleShape).background(accentColor))
        }
    }
}

@Composable
fun DeadlineCard(
    date: String,
    title: String,
    subject: String,
    priority: String,
    priorityColor: Color
) {
    SoftCard(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(18.dp)).background(RedSoft).padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = date, color = priorityColor, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = subject, color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
            }
            DeadlinePriorityBadge(text = priority, color = priorityColor)
        }
    }
}
