package com.first.miniproject.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountdownScreen(viewModel: EventViewModel = viewModel(), modifier: Modifier) {
    val fabColor = Color.White
    val fabContentColor = Color.Black

    val scaffColor = Color.hsl(240f,0.1f,0.5f)

    val events by viewModel.events.collectAsState()
    var showDateTimePicker by remember { mutableStateOf(false) }

    if (showDateTimePicker) {
        showDateTimePickerDialog(LocalContext.current) { selectedDateTime ->
            viewModel.addEvent("New Event", selectedDateTime)
            showDateTimePicker = false
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = scaffColor,
//        contentPadding = PaddingValues(all = 16.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDateTimePicker = true },
                backgroundColor = fabColor, // Set FAB background color
                contentColor = fabContentColor // Set FAB icon color
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Event")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                modifier = Modifier.background(scaffColor),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(events) { event ->
                    EventCountdownColumn(event = event, onRemove = { viewModel.removeEvent(event) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}




