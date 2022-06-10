package com.jeanca.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.jeanca.gitapp.R
import com.jeanca.gitapp.common.Constants.PARAM
import com.jeanca.gitapp.local.StoredData
import com.jeanca.gitapp.models.MCommit
import com.jeanca.gitapp.models.MRepository
import com.jeanca.gitapp.ui.aux.Composables
import com.jeanca.gitapp.ui.ui.theme.GitAppTheme
import com.jeanca.gitapp.viewmodels.DataViewModel

class RepositoryDetailActivity : AppCompatActivity() {

    private var dataViewModel: DataViewModel = DataViewModel()
    private lateinit var storedData: StoredData
    private lateinit var repository: MRepository

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Gson().fromJson(intent.getStringExtra(PARAM), MRepository::class.java)
        storedData = StoredData(this)
        dataViewModel.getCommits(storedData.getUsername(), repository.name, storedData.getToken())
        setContent {
            GitAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Body()
                }
            }
        }
    }

    /**
     *
     */
    @Composable
    fun Body() {

        val data: List<MCommit> by dataViewModel.commits().observeAsState(emptyList())
        
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            Composables.BackButton(::finish)
            Spacer(Modifier.height(15.dp))
            Text(modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                text = repository.name,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
            Spacer(modifier = Modifier.height(10.dp))
            repository.description?.let {
                Text(
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                    text = it,
                    fontSize = 15.sp
                )
            }
            Text(modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                text = repository.fullName,
                color = Color.Gray,
                fontSize = 12.sp)
            Spacer(modifier = Modifier.height(15.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(colorResource(id = R.color.input_disable))
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Card(modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column {
                        data.forEachIndexed { _, item ->
                           CommitCell.Init(item)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }

    /**
     *
     */
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        GitAppTheme {
            Body()
        }
    }
}

