package com.app.compulynx.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.app.compulynx.R

val geist =
    FontFamily(
        Font(R.font.geist_regular, FontWeight.Normal),
        Font(R.font.geist_black, FontWeight.Black),
        Font(R.font.geist_semi_bold, FontWeight.SemiBold),
        Font(R.font.geist_bold, FontWeight.Bold),
        Font(R.font.geist_extra_bold, FontWeight.ExtraBold),
        Font(R.font.geist_medium, FontWeight.Medium),
        Font(R.font.geist_thin, FontWeight.Thin),
        Font(R.font.geist_light, FontWeight.Light),
        Font(R.font.geist_extra_light, FontWeight.ExtraLight),
    )

val baseline = Typography()
val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = geist),
    displayMedium = baseline.displayMedium.copy(fontFamily = geist),
    displaySmall = baseline.displaySmall.copy(fontFamily = geist),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = geist),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = geist),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = geist),
    titleLarge = baseline.titleLarge.copy(fontFamily = geist),
    titleMedium = baseline.titleMedium.copy(fontFamily = geist),
    titleSmall = baseline.titleSmall.copy(fontFamily = geist),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = geist),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = geist),
    bodySmall = baseline.bodySmall.copy(fontFamily = geist),
    labelLarge = baseline.labelLarge.copy(fontFamily = geist),
    labelMedium = baseline.labelMedium.copy(fontFamily = geist),
    labelSmall = baseline.labelSmall.copy(fontFamily = geist)
)