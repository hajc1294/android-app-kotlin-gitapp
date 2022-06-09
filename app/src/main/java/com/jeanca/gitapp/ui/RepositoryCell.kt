package com.jeanca.gitapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanca.gitapp.R
import com.jeanca.gitapp.models.MRepository
import com.jeanca.gitapp.ui.aux.Composables
import com.jeanca.gitapp.utils.Colors

class RepositoryCell {

    companion object {

        /**
         *
         */
        @Composable
        private fun GetText(text: String) = Row {
            Text(text)
            Spacer(modifier = Modifier.width(10.dp))
        }

        /**
         *
         */
        @Composable
        private fun GetImage(res: Int, color: Color, size: Int = 15) = Row {
            Composables.SizedImage(res,
                color = color,
                size = size)
            Spacer(modifier = Modifier.width(5.dp))
        }

        /**
         *
         */
        @Composable
        fun Init(repository: MRepository) = Column(modifier =
            Modifier.padding(start = 20.dp, end = 20.dp))
        {
            Spacer(modifier = Modifier.height(30.dp))
            Text(repository.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            repository.description?.let { Text(it) }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End)
            {
                GetImage(R.drawable.ic_baseline_share, Color.Black)
                GetText(text = repository.forks.toString())
                GetImage(R.drawable.ic_baseline_star_border,
                    Color(241, 196, 15),
                    size = 20)
                GetText(text = repository.stargazersCount.toString())
                GetImage(R.drawable.ic_baseline_circle, Colors
                    .getColorFromLanguage(repository.language))
                GetText(text = repository.language)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Composables.SingleDivider()
        }
    }
}