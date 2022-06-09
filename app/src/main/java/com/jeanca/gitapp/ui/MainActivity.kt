package com.jeanca.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.jeanca.gitapp.R
import com.jeanca.gitapp.local.StoredData
import com.jeanca.gitapp.ui.aux.Composables
import com.jeanca.gitapp.ui.theme.GitAppTheme

class MainActivity : AppCompatActivity() {

    private var canAuthenticate = false
    private val username = mutableStateOf("")
    private val token = mutableStateOf("")
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var storedData: StoredData

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storedData = StoredData(this)
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
        initAuth()
    }

    /**
     *
     */
    override fun onResume() {
        super.onResume()
        username.value = storedData.getUsername()
        token.value = ""
    }

    /**
     *
     */
    private fun initAuth() {
        if (BiometricManager.from(this)
                .canAuthenticate(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL) ==
            BiometricManager.BIOMETRIC_SUCCESS) {
            canAuthenticate = true
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometric_auth))
                .setSubtitle(getString(R.string.biometric_sensor))
                .setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                .build()
        }
    }

    /**
     *
     */
    private fun authenticate(auth: (auth: Boolean) -> Unit) {
        if (canAuthenticate) {
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                object: BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promptInfo)
        } else {
            auth(true)
        }
    }

    /**
     *
     */
    @Composable
    fun Body() {

        val mContext = LocalContext.current
        val userError = remember { mutableStateOf(false) }
        val tokenError = remember { mutableStateOf(false) }

        ///
        ///
        ///
        Column(modifier = Modifier.background(Color.White).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween)
        {
            Spacer(modifier = Modifier.weight(1f))
            Composables.SingleImage(R.drawable.git,
                modifier = Modifier.height(150.dp))
            Spacer(modifier = Modifier.height(40.dp))
            Composables.ErrorTextField(username.value, {
                username.value = it
                userError.value = false
            }, userError.value, R.string.user, enabled = storedData.getUsername().isEmpty())
            Spacer(modifier = Modifier.height(10.dp))

            ///
            ///
            ///
            if (storedData.getUsername().isNotEmpty()) {
                Spacer(Modifier.height(20.dp))
                Text(text = stringResource(id = R.string.fingerprint),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
                TextButton(onClick = {
                    authenticate {
                        mContext.startActivity(Intent(mContext, DataActivity::class.java))
                    }
                }) {
                    Composables.SizedImage(res = androidx.biometric.R.drawable.fingerprint_dialog_fp_icon,
                        color = Color.Gray,
                        size = 80)
                }
                Composables.SingleImage(R.drawable.binary_code)
            } else {
                Composables.ErrorTextField(token.value, {
                    token.value = it
                    tokenError.value = false
                }, tokenError.value, R.string.token)
                Spacer(Modifier.height(15.dp))
                Button(onClick = {
                    if (username.value.isNotEmpty() && token.value.isNotEmpty()) {
                        storedData.saveData(username.value, token.value)
                        mContext.startActivity(Intent(mContext, DataActivity::class.java))
                    } else {
                        userError.value = username.value.isEmpty()
                        tokenError.value = token.value.isEmpty()
                    }
                }, modifier = Modifier.width(200.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
                Spacer(modifier = Modifier.weight(1f))
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