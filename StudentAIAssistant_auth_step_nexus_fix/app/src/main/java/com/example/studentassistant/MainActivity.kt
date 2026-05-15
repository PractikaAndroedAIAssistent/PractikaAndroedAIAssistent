package com.example.studentassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.studentassistant.ui.navigation.AppNavigation
import com.example.studentassistant.ui.theme.StudentAssistantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            StudentAssistantTheme {
                AppNavigation()
            }
        }
    }
}
