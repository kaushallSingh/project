package com.first.miniproject.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

class EventViewModel : ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    fun addEvent(name: String, date: LocalDateTime) {
        _events.value = _events.value + Event(name, date)
    }

    fun removeEvent(event: Event) {
        _events.value = _events.value - event
    }
}
