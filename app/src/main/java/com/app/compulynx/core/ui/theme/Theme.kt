package com.app.compulynx.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkScheme =
    darkColorScheme(
        primary = White,
        onPrimary = Black,
        primaryContainer = DarkGray,
        onPrimaryContainer = White,
        secondary = LightGray,
        onSecondary = Black,
        secondaryContainer = DarkGray,
        onSecondaryContainer = White,
        tertiary = LightGray,
        onTertiary = Black,
        tertiaryContainer = DarkGray,
        onTertiaryContainer = White,
        error = Color.Red,
        onError = Black,
        errorContainer = DarkGray,
        onErrorContainer = White,
        background = Black,
        onBackground = White,
        surface = DarkGray,
        onSurface = White,
        surfaceVariant = DarkGray,
        onSurfaceVariant = LightGray,
        outline = MidGray,
        surfaceContainer = Purple,
    )

@Composable
fun CompuLynxTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkScheme,
        typography = Typography,
        content = content,
    )
}
