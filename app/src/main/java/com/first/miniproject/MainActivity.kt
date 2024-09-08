package com.first.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.first.miniproject.data.CountdownScreen
import com.first.miniproject.ui.theme.MiniProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CountdownScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


