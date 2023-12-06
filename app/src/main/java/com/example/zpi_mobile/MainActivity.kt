package com.example.zpi_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.zpi_mobile.navigation.Navigation
import com.example.zpi_mobile.screens.PlanScreen
import com.example.zpi_mobile.ui.theme.ZPIMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZPIMobileTheme() {
                Navigation()
            }
        }
    }
}
