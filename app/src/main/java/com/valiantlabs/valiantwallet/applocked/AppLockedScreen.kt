package com.valiantlabs.valiantwallet.applocked


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.valiantlabs.design_system.system.components.ValiantButton
import com.valiantlabs.valiantwallet.R
import com.valiantlabs.valiantwallet.ui.theme.ValiantBlack

// UI (Composable)
@Composable
fun AppLockedScreen(viewModel: BiometricAuthViewModel, onBiometricSuccess: () -> Unit) {
    val biometricResult by viewModel.biometricResult.collectAsState()
    val enrollmentIntent by viewModel.enrollmentIntent.collectAsState()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle result if needed
        println("Enrollment Activity result: $result")
    }

    LaunchedEffect(biometricResult) {
        viewModel.handleBiometricResult(biometricResult)
    }

    LaunchedEffect(enrollmentIntent) {
        enrollmentIntent?.let { intent ->
            launcher.launch(intent)
            viewModel.clearEnrollmentIntent() // Clear the intent after launching
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .background(ValiantBlack, MaterialTheme.shapes.extraLarge)
                .padding(vertical = 12.dp)
                .padding(horizontal = 32.dp),
            text = stringResource(R.string.app_locked)
        )

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(width = 96.dp, height = 138.dp),
            painter = painterResource(id = R.drawable.ic_fingerprint),
            contentScale = ContentScale.FillBounds,
            contentDescription = "unlock icon"
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(R.string.authenticate_text),
        )

        Spacer(Modifier.height(24.dp))

        ValiantButton(
            modifier = Modifier.padding(horizontal = 16.dp),

            text = stringResource(R.string.unlock),

            buttonHeight = 52.dp,

            buttonWidth = 372.dp,

            textStyle = TextStyle(),

        ){
            viewModel.authenticate()

        }


        Spacer(Modifier.height(24.dp))

        biometricResult?.let { result ->
            val message = when (result) {
                is AppLockedResult.AuthenticationError -> result.error
                AppLockedResult.AuthenticationFailed -> "Authentication failed"
                AppLockedResult.AuthenticationNotSet -> "Authentication not set"
                AppLockedResult.AuthenticationSuccess -> {
                    onBiometricSuccess() // Call the callback on success
                    "Authentication success"
                }
                AppLockedResult.FeatureUnavailable -> "Feature unavailable"
                AppLockedResult.HardwareUnavailable -> "Hardware unavailable"
            }
            Text(text = message)
        }
    }
}
