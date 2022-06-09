package com.jeanca.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanca.gitapp.R
import com.jeanca.gitapp.common.Constants.EXIT
import com.jeanca.gitapp.local.StoredData
import com.jeanca.gitapp.models.MUser
import com.jeanca.gitapp.ui.aux.Composables
import com.jeanca.gitapp.ui.theme.GitAppTheme
import com.jeanca.gitapp.viewmodels.UserViewModel

class UserActivity : AppCompatActivity() {

    private var userViewModel: UserViewModel = UserViewModel()
    private lateinit var storedData: StoredData

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storedData = StoredData(this)
        userViewModel.getUserInfo(storedData.getUsername(), storedData.getToken())
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
    fun BackButton() = TextButton(onClick = {
        finish()
    }) {
        Row {
            Composables.SizedImage(R.drawable.ic_baseline_arrow_back_ios,
                color = Color.Red,
                size = 20)
            Text(text = stringResource(id = R.string.welcome),
                color = Color.Red)
        }
    }

    /**
     *
     */
    @Composable
    fun UserInfoSections(dataSet: List<Pair<Int, String?>>) = Column(modifier = Modifier
        .padding(start = 35.dp, end = 35.dp)
        .fillMaxWidth()) {
        dataSet.map {
            if (it.second != null) {
                Row {
                    Composables.SizedImage(it.first, Color.Red, size = 20)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = it.second!!, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    /**
     *
     */
    @Composable
    fun Body() {

        val data: MUser by userViewModel.getData().observeAsState(MUser())

        Column {
            Spacer(modifier = Modifier.height(10.dp))
            BackButton()
            Spacer(Modifier.height(25.dp))
            Text(modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                text = stringResource(id = R.string.account),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
            Spacer(Modifier.weight(1f))
            Composables.RoundImageWithDetail(data.avatarUrl, data.name, data.htmlUrl)
            Spacer(Modifier.height(20.dp))
            UserInfoSections(listOf(
                Pair(R.drawable.ic_baseline_apartment, data.company),
                Pair(R.drawable.ic_baseline_location, data.location),
                Pair(R.drawable.ic_baseline_cloud,
                    stringResource(id = R.string.public_repos, data.publicRepos)
                ),
                Pair(R.drawable.ic_baseline_access_time,
                    stringResource(id = R.string.created_at, data.createdAt)
                ))
            )
            Spacer(Modifier.weight(1f))
            Composables.ClickableBottomContainer(callback = {
                storedData.clear()
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra(EXIT, true)
                startActivity(intent)
            })
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

