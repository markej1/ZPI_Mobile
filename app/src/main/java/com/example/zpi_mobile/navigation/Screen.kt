package com.example.zpi_mobile.navigation

sealed class Screen(val route: String) {
    object StartScreen: Screen("start_screen")
    object MenuScreen: Screen("menu_screen")
}
