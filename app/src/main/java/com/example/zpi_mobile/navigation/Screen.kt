package com.example.zpi_mobile.navigation

sealed class Screen(val route: String) {
    object StartScreen: Screen("start_screen")
    object MenuScreen: Screen("menu_screen")
    object SubjectCardScreen: Screen("subject_card_screen")
    object PlanScreen: Screen("plan_screen")
    object HelpScreen: Screen("help_screen")
}
