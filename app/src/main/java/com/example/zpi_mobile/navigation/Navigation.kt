package com.example.zpi_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zpi_mobile.screens.*
import com.example.zpi_mobile.services.SubjectService

@Composable
fun Navigation(
    subjectService: SubjectService
) {
    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
    NavHost(navController = navController, startDestination = Screen.PlanScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = Screen.MenuScreen.route) {
            MenuScreen()
        }
        composable(route = Screen.SubjectCardScreen.route) {
            SubjectCardScreen(subjectService = subjectService)
        }
        composable(route = Screen.PlanScreen.route) {
            PlanScreen(subjectService = subjectService, navController = navController)
        }
    }
}