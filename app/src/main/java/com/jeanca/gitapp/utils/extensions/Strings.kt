package com.jeanca.gitapp.utils.extensions

import com.jeanca.gitapp.common.Constants.SERVER_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date {
    val format = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
    return format.parse(this) ?: Date()
}
