@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.zpi_mobile.SharedPreferencesManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {
    val sharedPreferencesManager = SharedPreferencesManager(LocalContext.current)
    val level = sharedPreferencesManager.getData("level", "")
    val field = sharedPreferencesManager.getData("field", "")
    val cycle = sharedPreferencesManager.getData("cycle", "")
    val specialization = sharedPreferencesManager.getData("specialization", "")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = field,
                        color = Color.Black
                    )
                },
//                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xff0f9d58)),
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Column() {
                        Row() {
                            Text("Stopień studiów:")
                            Text(level)
                        }
                        Row() {
                            Text("Cykl kształcenia:")
                            Text(cycle)
                        }
                        if (specialization != "") {
                            Row() {
                                Text("Specjalność:")
                                Text(specialization)
                            }
                        }
                    }
                    Button(onClick = {}) {
                        Text(text = "Plan studiów")
                    }
                }
            }
        }
    )

}
