package com.valiantlabs.valiantwallet.applocked

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ViewModel (Data/Logic)
class BiometricAuthViewModel() {

    private val promptManager by lazy {
        AppLockedPromptManager(this)
    }

    private val _biometricResult = MutableStateFlow<AppLockedResult?>(null)
    val biometricResult: StateFlow<AppLockedResult?> = _biometricResult.asStateFlow()

    private val _enrollmentIntent = MutableStateFlow<Intent?>(null)
    val enrollmentIntent: StateFlow<Intent?> = _enrollmentIntent.asStateFlow()

    fun authenticate() {
        promptManager.showAppLockedPrompt(
            title = "Sample prompt",
            description = "Sample prompt description"
        )
    }


    fun handleBiometricResult(result: AppLockedResult?) {
        _biometricResult.value = result

        if (result is AppLockedResult.AuthenticationNotSet) {
            if (Build.VERSION.SDK_INT >= 30) {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }
                _enrollmentIntent.value = enrollIntent // Set the intent for the UI to launch
            }
        } else {
            _enrollmentIntent.value = null // Reset the intent if not needed
        }
    }

    fun clearEnrollmentIntent(){
        _enrollmentIntent.value = null
    }

}