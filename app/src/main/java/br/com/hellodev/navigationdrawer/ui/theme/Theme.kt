package br.com.hellodev.navigationdrawer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

private val MyLightColorScheme = MyColorScheme(
    primary = PrimaryColorLight,
    onPrimary = OnPrimaryColorLight,
    text = TextColorLight,
    background = BackgroundColorLight
)

private val MyDarkColorScheme = MyColorScheme(
    primary = SecondaryColorDark,
    onPrimary = OnSecondaryColorDark,
    text = TextColorDark,
    background = BackgroundColorDark
)

private val LocalColorScheme = compositionLocalOf { MyLightColorScheme }

object NavigationDrawerTheme {
    val colorScheme: MyColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

@Composable
fun NavigationDrawerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme by remember(isDarkTheme) {
        mutableStateOf(if (isDarkTheme) MyDarkColorScheme else MyLightColorScheme)
    }

    CompositionLocalProvider(LocalColorScheme provides colorScheme) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}