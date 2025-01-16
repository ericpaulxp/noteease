package com.example.noteease.utils

import java.util.Calendar
import java.util.Locale

fun getShortDate( timeStamp: Long?): String{
    if (timeStamp == null) return ""
    val calendar  = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = timeStamp
    return android.text.format.DateFormat.format("E, dd MMM yyyy, hh:mm a", calendar).toString()

}