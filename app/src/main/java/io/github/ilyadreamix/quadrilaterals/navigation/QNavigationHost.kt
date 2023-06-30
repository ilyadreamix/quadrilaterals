package io.github.ilyadreamix.quadrilaterals.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import io.github.ilyadreamix.quadrilaterals.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QNavigationHost() {
    AnimatedNavHost(
        navController = LocalNavigationHostController.current,
        startDestination = QNavigationDestination.Home.route
    ) {
        composable(route = QNavigationDestination.Home.route) {
            HomeScreen()
        }
    }
}

val LocalNavigationHostController = compositionLocalOf<NavHostController> {
    error("NavigationHostController was not provided!")
}
