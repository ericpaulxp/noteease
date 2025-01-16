package com.example.noteease.ui.theme

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import com.example.domain.model.Theme
import com.example.noteease.ui.feature_settings.SettingsViewmodel
import org.koin.androidx.compose.koinViewModel


private val LightColorScheme = lightColorScheme(
    primary = onBackGroundHeading,
    onPrimary = Color.White,
    secondary = onBackGroundParagraph,
    onSecondary = Color.White,
    onSecondaryContainer = onBackGroundLabel,
    background = background,
    tertiary = accent,
    onBackground = Color.Black,
    surface = surface,
    onSurface = Color.Black,
    error = Color(0xFFBB102E),
    onError = Color.White,
)
private val DarkColorScheme = darkColorScheme(
    primary = onDarkBackGroundHeading,
    onPrimary = Color.Black,
    secondary = onDarkBackGroundParagraph,
    onSecondary = Color.White,
    onSecondaryContainer = onDarkBackGroundLabel,
    tertiary = darkAccent,
    background = darkBackground,
    onBackground = Color.White,
    surface = darkSurface,
    onSurface = Color.White,
    error = Color(0xFFBB102E),
)

@Composable
internal fun UpdateEdgeToEdge(darkVariant: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) return

    SideEffect {
        val barStyle =
            if (darkVariant) SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
            else SystemBarStyle.light(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT)
        (view.context as ComponentActivity).enableEdgeToEdge(barStyle, barStyle)
    }
}

@Composable
fun NoteEaseTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val viewmodel: SettingsViewmodel = koinViewModel()
    val theme = viewmodel.theme.collectAsState().value
    UpdateEdgeToEdge(theme.darkVariant ?: isDarkTheme)

    val colorScheme = when(theme){
        Theme.System -> {
            if (isDarkTheme){
                DarkColorScheme
            }else{
                LightColorScheme
            }
        }
        Theme.Light -> { LightColorScheme }
        Theme.Dark -> { DarkColorScheme }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}




