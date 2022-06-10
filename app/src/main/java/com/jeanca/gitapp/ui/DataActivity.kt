package com.jeanca.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.jeanca.gitapp.models.MRepository
import com.jeanca.gitapp.ui.aux.Composables
import com.jeanca.gitapp.ui.theme.GitAppTheme
import com.jeanca.gitapp.utils.Alerts
import com.jeanca.gitapp.viewmodels.DataViewModel

class DataActivity : AppCompatActivity() {

    private var dataViewModel: DataViewModel = DataViewModel()
    private lateinit var storedData: StoredData

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storedData = StoredData(this)
        dataViewModel.getRepositories(storedData.getUsername(), storedData.getToken())
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
    private fun navigateToUserInfo() {
        this.startActivity(Intent(this, UserActivity::class.java))
    }

    /**
     *
     */
    @Composable
    fun NavButton(res: Int, callback: () -> Unit) = TextButton(onClick = {
        callback()
    }) {
        Composables.SizedImage(res, size = 40)
    }

    /**
     *
     */
    @Composable
    fun Body() {

        val mContext = LocalContext.current
        val data: List<MRepository> by dataViewModel.repositories().observeAsState(emptyList())

        ///
        ///
        ///
        Column {
            if (data.isEmpty()) {
                Alerts.Loading()
            } else {
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End) {
                    NavButton(R.drawable.ic_baseline_info, ::navigateToUserInfo)
                    NavButton(R.drawable.ic_baseline_exit, ::finish)
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Box(modifier = Modifier.padding(20.dp)) {
                    Text(text = stringResource(id = R.string.welcome),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(colorResource(id = R.color.input_disable))
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Card(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(10.dp)) {
                        Column {
                            data.forEachIndexed { _, item ->
                                TextButton(onClick = {
                                    val intent = Intent(mContext, RepositoryDetailActivity::class.java)
                                    intent.putExtra(PARAM, Gson().toJson(item))
                                    mContext.startActivity(intent)
                                }) {
                                    RepositoryCell.Init(repository = item)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }

    /**
     *
     */
    @Preview(showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        GitAppTheme {
            Body()
        }
    }
}

