package com.podium.technicalchallenge.compose.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

private const val API_DATE_FORMAT = "yyyy-MM-dd"
private const val DISPLAY_DATE_FORMAT = "EE MMM d, yyyy"

val String?.dateFromAPIFormat: Date?
    get() = try {
        if (this?.isNotEmpty() == true) {
            SimpleDateFormat(
                API_DATE_FORMAT,
                Locale.getDefault()
            ).parse(this)
        } else {
            null
        }
    } catch (e: Throwable) {
        Log.e("DateTime", "Error formatting date \"${this ?: ""}\": ${e.message}")
        null
    }

val Date?.displayFormat: String
    get() = SimpleDateFormat(
        DISPLAY_DATE_FORMAT,
        Locale.getDefault()
    ).format(this ?: Date())
