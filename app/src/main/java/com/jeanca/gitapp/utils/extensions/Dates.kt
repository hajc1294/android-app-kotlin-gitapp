package com.jeanca.gitapp.utils.extensions

import com.jeanca.gitapp.common.Constants.SIMPLE_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringFormat(): String {
    val dateFormatter = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.getDefault())
    return dateFormatter.format(this)
}