package ro.smg.exchangerates.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String = SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.getDefault()).format(Date())