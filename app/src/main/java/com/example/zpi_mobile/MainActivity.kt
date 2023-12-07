package com.example.zpi_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.zpi_mobile.navigation.Navigation
import com.example.zpi_mobile.screens.PlanScreen
import com.example.zpi_mobile.services.SubjectService

class MainActivity : ComponentActivity() {
    private val viewModel: SubjectService by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(viewModel)
        }
    }
}
