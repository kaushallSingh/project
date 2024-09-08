package com.first.miniproject.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun EventCountdownColumn(event: Event, onRemove: () -> Unit) {
    val columContentColor = Color.hsl(240f,0.25f,0.5f)
    val timerColor = Color.Black

    var timeLeft by remember { mutableStateOf(Duration.between(LocalDateTime.now(), event.date)) }

    LaunchedEffect(event) {
        while (timeLeft > Duration.ZERO) {
            timeLeft = Duration.between(LocalDateTime.now(), event.date)
            if (timeLeft < Duration.ZERO) {
                timeLeft = Duration.ZERO
            }
            delay(1000L) // Update every second
        }
    }

    // Ensure the timer is exactly zero when it finishes
    if (timeLeft < Duration.ZERO) {
        timeLeft = Duration.ZERO
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(columContentColor) // Set background color to orange
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = event.name,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = formatDuration(timeLeft),
            style = MaterialTheme.typography.displaySmall.copy(fontSize = 20.sp),
            color = timerColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRemove) {
            Text("Remove Event")
        }
    }
}


fun formatDuration(duration: Duration): String {
    val days = duration.toDays()
    val hours = (duration.toHours() % 24)
    val minutes = (duration.toMinutes() % 60)
    val seconds = (duration.seconds % 60)

    return buildString {
        if (days > 0) append("${days}d ")
        if (hours > 0) append("${hours}h ")
        if (minutes > 0) append("${minutes}m ")
        append("${seconds}s")
    }.trim()
}

