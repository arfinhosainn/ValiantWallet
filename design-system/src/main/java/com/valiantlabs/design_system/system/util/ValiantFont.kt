package com.valiantlabs.design_system.system.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.valiantlabs.design_system.R

// 1. Define your Font Families (you'll need to load these)
object ValiantFonts {
    val Poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal), // Replace with your font resources
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )
    val PlusJakartaSans = FontFamily(
        Font(R.font.plusjakartasans_regular, FontWeight.Normal), // Replace with your font resources
        Font(R.font.plusjakartasans_bold, FontWeight.Bold),
        Font(R.font.plusjakartasans_semibold, FontWeight.SemiBold)
    )

    val Rubik = FontFamily(
        Font(R.font.rubik_regular, FontWeight.Normal), // Replace with your font resources
        Font(R.font.rubik_medium, FontWeight.Medium),
        Font(R.font.rubik_semibold, FontWeight.SemiBold)
    )
    val SpaceGrotesk = FontFamily(
        Font(R.font.spacegrotesk_regular, FontWeight.Normal), // Replace with your font resources
        Font(R.font.spacegrotesk_bold, FontWeight.Bold),
        Font(R.font.spacegrotest_semibold, FontWeight.SemiBold)
    )
}


