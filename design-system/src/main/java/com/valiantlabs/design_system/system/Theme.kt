package com.valiantlabs.design_system.system

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Light Theme Color Scheme (using your custom colors)
private val LightValiantColorScheme = lightColorScheme(
    primary = ValiantGreen,
    onPrimary = ValiantBlack,
    secondary = ValiantRed,
    onSecondary = ValiantWhite,
    tertiary = ValiantOrange,
    onTertiary = ValiantWhite,
    background = ValiantWhite,
    onBackground = ValiantBlack,
    surface = ValiantWhite,
    onSurface = ValiantBlack,
    error = ValiantBloodRed,
    onError = ValiantWhite
)

// Dark Theme Color Scheme (using your custom colors)
private val DarkValiantColorScheme = darkColorScheme(
    primary = ValiantGreen,
    onPrimary = ValiantBlack,
    secondary = ValiantRed,
    onSecondary = ValiantWhite,
    tertiary = ValiantOrange,
    onTertiary = ValiantWhite,
    background = ValiantBlack,
    onBackground = ValiantWhite,
    surface = ValiantGrey,
    onSurface = ValiantWhite,
    error = ValiantBloodRed,
    onError = ValiantWhite
)

@Composable
fun ValiantWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Keep the dynamic color option
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkValiantColorScheme // Use your custom dark theme
        else -> LightValiantColorScheme      // Use your custom light theme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Assuming you have a Typography defined
        content = content
    )
}
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ))