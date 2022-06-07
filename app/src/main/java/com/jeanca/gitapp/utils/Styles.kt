package com.jeanca.gitapp.utils

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.jeanca.gitapp.R

class Styles {

    companion object {

        @Composable
        fun outlinedTextFieldStyle(enabled: Boolean = true) = TextFieldDefaults.textFieldColors(
            backgroundColor = if (enabled)
                Color.White else colorResource(id = R.color.input_disable),
            cursorColor = Color.Black,
            disabledLabelColor = Color.Gray,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Gray,
        )
    }
}