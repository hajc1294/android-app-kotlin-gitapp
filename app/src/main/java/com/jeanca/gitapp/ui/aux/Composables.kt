package com.jeanca.gitapp.ui.aux

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.jeanca.gitapp.R
import com.jeanca.gitapp.utils.Styles

class Composables {

    companion object {

        /**
         *
         */
        @Composable
        fun RoundImageWithDetail(avatar: String, userFullName: String, url: String) = Column(modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.clip(RoundedCornerShape(70.dp))) {
                Image(
                    painter = rememberAsyncImagePainter(avatar),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(text = userFullName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Text(text = url,
                fontSize = 14.sp)
            Spacer(Modifier.height(25.dp))
        }

        /**
         *
         */
        @Composable
        fun SizedImage(res: Int, color: Color = Color.Black, size: Int = 15) = Image(
            painterResource(res),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(size.dp)
                .width(size.dp),
            colorFilter = ColorFilter.tint(color = color)
        )

        /**
         *
         */
        @Composable
        fun SingleImage(res: Int, modifier: Modifier = Modifier.fillMaxWidth()) = Image(
            painterResource(res),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        /**
         *
         */
        @Composable
        fun ErrorText() = Text(
            text = stringResource(id = R.string.input_error),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.width(300.dp)
        )

        /**
         *
         */
        @Composable
        fun SingleDivider() = Divider(
            color = colorResource(id = R.color.input_disable),
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            startIndent = 10.dp
        )

        /**
         *
         */
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

        /**
         *
         */
        @Composable
        fun ClickableBottomContainer(callback: () -> Unit) = Row(modifier = Modifier
            .height(65.dp)
            .fillMaxWidth()
            .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            TextButton(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
                onClick = {
                    callback()
                }) {
                Text(text = stringResource(id = R.string.change_account),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            }
        }
    }
}