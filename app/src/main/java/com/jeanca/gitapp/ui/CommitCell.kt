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
import com.jeanca.gitapp.models.MCommit
import com.jeanca.gitapp.models.MRepository
import com.jeanca.gitapp.ui.aux.Composables
import com.jeanca.gitapp.utils.Colors

class CommitCell {

    companion object {

        /**
         *
         */
        @Composable
        fun Init(commit: MCommit) = Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(commit.commit.message,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = commit.commit.author.name,
                fontSize = 12.sp)
            Text(text = commit.commit.author.email,
                fontSize = 12.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End)
            {
                Text(text = commit.commit.author.date,
                    fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Composables.SingleDivider()
        }
    }
}