package io.github.ilyadreamix.quadrilaterals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.github.ilyadreamix.quadrilaterals.navigation.LocalNavigationHostController
import io.github.ilyadreamix.quadrilaterals.navigation.QNavigationHost
import io.github.ilyadreamix.quadrilaterals.theme.QTheme

class QActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QTheme {
                CompositionLocalProvider(
                    LocalNavigationHostController provides rememberAnimatedNavController()
                ) {
                    QNavigationHost()
                }
            }
        }
    }
}
