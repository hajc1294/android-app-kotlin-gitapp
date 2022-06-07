package com.jeanca.gitapp.utils

import androidx.compose.ui.graphics.Color

class Colors {

    companion object {

        fun getColorFromLanguage(language: String): Color = when (language) {
            "Java" -> Color(185, 119, 14)
            "Dart" -> Color(26, 188, 156)
            "Kotlin" -> Color(175, 122, 197)
            "Swift" -> Color(231, 76, 60)
            "JavaScript" -> Color(247, 220, 111)
            else -> Color.Black
        }
    }
}