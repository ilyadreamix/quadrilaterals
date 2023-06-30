package io.github.ilyadreamix.quadrilaterals.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun QTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useEdgeToEdge: Boolean = true,
    useDynamicColorScheme: Boolean = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current
    val activity = (context as Activity)

    val colorScheme = when {
        useDynamicColorScheme && useDarkTheme -> dynamicDarkColorScheme(context)
        useDynamicColorScheme && !useDarkTheme -> dynamicLightColorScheme(context)
        useDarkTheme -> QuadrilateralsDarkColorScheme
        else -> QuadrilateralsLightColorScheme
    }

    if (useEdgeToEdge && !view.isInEditMode) {
        SideEffect {
            val window = activity.window.apply {
                statusBarColor = Color.Transparent.toArgb()
                navigationBarColor = Color.Transparent.toArgb()
            }

            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightNavigationBars = !useDarkTheme
                isAppearanceLightStatusBars = !useDarkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
                content = content
            )
        }
    )
}

internal val QuadrilateralsLightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight
)

internal val QuadrilateralsDarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark
)
