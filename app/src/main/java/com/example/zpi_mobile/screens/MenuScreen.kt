@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.navigation.Screen
import com.example.zpi_mobile.ui.theme.StatusBarColor

val rowArrangement = Arrangement.SpaceBetween
val paddingHorizontal = 20.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    val sharedPreferencesManager = SharedPreferencesManager(LocalContext.current)
    val level = sharedPreferencesManager.getData("level", "")
    val field = sharedPreferencesManager.getData("field", "")
    val cycle = sharedPreferencesManager.getData("cycle", "")
    val specialization = sharedPreferencesManager.getData("specialization", "")

    val infoArrangement = Arrangement.spacedBy(10.dp)

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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Column(verticalArrangement = infoArrangement) {
                    RowInformation(text = "Stopień studiów:", answer = level)
                    RowInformation(text = "Cykl kształcenia:", answer = cycle)
                    if (specialization != "") {
                        RowInformation(text = "Specjalność:", answer = specialization)
                    }
                    RowInformation(text = "Profil:", answer = "ogólnoakademicki")
                    RowInformation(text = "Poziom studiów:", answer = "pierwszy")
                    RowInformation(text = "Forma studiów:", answer = "stacjonarna")
                    RowInformation(text = "Język studiów:", answer = "polski")
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.PlanScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = StatusBarColor)
                ) {
                    Text(
                        text = "Plan studiów",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    )

}

@Composable
fun RowInformation(text: String, answer: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal),
        horizontalArrangement = rowArrangement
    ) {
        Text(text)
        Text(answer)
    }
}
