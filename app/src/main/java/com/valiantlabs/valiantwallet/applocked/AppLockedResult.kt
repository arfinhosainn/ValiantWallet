package com.valiantlabs.valiantwallet.applocked

sealed interface AppLockedResult {
    data object HardwareUnavailable: AppLockedResult
    data object FeatureUnavailable: AppLockedResult
    data class AuthenticationError(val error: String): AppLockedResult
    data object AuthenticationFailed: AppLockedResult
    data object AuthenticationSuccess: AppLockedResult
    data object AuthenticationNotSet: AppLockedResult
}