package com.jeanca.gitapp.ui.aux

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeanca.gitapp.R
import com.jeanca.gitapp.utils.Styles

class Composables {

    companion object {

        @Composable
        fun CreateImage(res: Int, color: Color = Color.Black, size: Int = 15) = Image(
            painterResource(res),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(size.dp)
                .width(size.dp),
            colorFilter = ColorFilter.tint(color = color)
        )


        @Composable
        fun CreateImages(res: Int, modifier: Modifier = Modifier.fillMaxWidth()) = Image(
            painterResource(res),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        @Composable
        fun ErrorText() = Text(
            text = stringResource(id = R.string.input_error),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.width(300.dp)
        )

        @Composable
        fun SingleDivider() = Divider(
            color = colorResource(id = R.color.input_disable),
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            startIndent = 10.dp
        )

        @Composable
        fun ErrorTextField(value: String, onChange: (it: String) -> Unit,
                           error: Boolean, labelRes: Int, enabled: Boolean = true) = Column {
            OutlinedTextField(
                value = value,
                onValueChange = { onChange(it) },
                maxLines = 1,
                singleLine = true,
                enabled = enabled,
                isError = error,
                label = { Text(text = if (enabled) stringResource(id = labelRes) else "") },
                shape = RoundedCornerShape(8.dp),
                colors = Styles.outlinedTextFieldStyle(),
                modifier = Modifier.width(300.dp)
            )
            if (error) {
                ErrorText()
            }
        }
    }
}