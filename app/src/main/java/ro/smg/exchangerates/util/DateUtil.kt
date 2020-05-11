package ro.smg.exchangerates.util

import java.text.SimpleDateFormat
import java.util.*

private fun getDate(format: String) = SimpleDateFormat(format, Locale.getDefault()).format(Date())

fun getDateTime(): String = getDate("dd-MM-yy HH:mm:ss")

fun getCurrentDate(): String = getDate("yyyy-MM-dd")

fun getDateDaysAgo(days: Int) = SimpleDateFormat("yyyy-MM-dd").format(
    Calendar.getInstance().apply {
        time = Date()
        add(Calendar.DAY_OF_YEAR, -days)
    }.time
)

fun getTimeFromDate(date: String): Long =  SimpleDateFormat("yyyy-MM-dd").parse(date).time

fun getDateFromTime(ms: Long): String = SimpleDateFormat("yyyy-MM-dd").format(
    Calendar.getInstance().apply {
        timeInMillis = ms
    }.time
)