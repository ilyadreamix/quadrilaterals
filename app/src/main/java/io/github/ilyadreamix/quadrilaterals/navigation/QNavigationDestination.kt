package io.github.ilyadreamix.quadrilaterals.navigation

sealed class QNavigationDestination(val route: String) {
    object Home : QNavigationDestination("/")
}
