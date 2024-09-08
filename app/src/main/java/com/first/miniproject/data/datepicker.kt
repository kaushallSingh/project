package com.first.miniproject.data

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun showDateTimePickerDialog(context: Context, onDateTimeSelected: (LocalDateTime) -> Unit) {
    val calendar = java.util.Calendar.getInstance()
    val year = calendar.get(java.util.Calendar.YEAR)
    val month = calendar.get(java.util.Calendar.MONTH)
    val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
    val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
    val minute = calendar.get(java.util.Calendar.MINUTE)

    DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
        TimePickerDialog(context, { _, selectedHour, selectedMinute ->
            val selectedDateTime = LocalDateTime.of(
                selectedYear,
                selectedMonth + 1,
                selectedDay,
                selectedHour,
                selectedMinute
            )
            onDateTimeSelected(selectedDateTime)
        }, hour, minute, true).show()
    }, year, month, day).show()
}
