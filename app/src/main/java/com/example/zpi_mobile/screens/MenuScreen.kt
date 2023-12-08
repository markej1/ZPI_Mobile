@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.navigation.Screen
import com.example.zpi_mobile.ui.theme.StatusBarColor
import java.util.logging.Level

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

    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = field,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                },
//                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xff0f9d58)),
            )
        },
        content = {
            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    LandscapeContent(infoArrangement, level, cycle, specialization, navController)
                }
                else -> {
                    PortraitContent(infoArrangement, level, cycle, specialization, navController)
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

@Composable
fun ColumnInformation(text: String, answer: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
        Text(answer)
    }
}

@Composable
fun LandscapeContent(
    infoArrangement: Arrangement.Vertical,
    level: String,
    cycle: String,
    specialization: String,
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxWidth().wrapContentWidth(),
        contentAlignment = Alignment.TopCenter
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
        ) {
            LazyColumn {
                item {
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
}

@Composable
fun PortraitContent(
    infoArrangement: Arrangement.Vertical,
    level: String,
    cycle: String,
    specialization: String,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = paddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(verticalArrangement = infoArrangement) {
            ColumnInformation(text = "Stopień studiów:", answer = level)
            ColumnInformation(text = "Cykl kształcenia:", answer = cycle)
            if (specialization != "") {
                ColumnInformation(text = "Specjalność:", answer = specialization)
            }
            ColumnInformation(text = "Profil:", answer = "ogólnoakademicki")
            ColumnInformation(text = "Poziom studiów:", answer = "pierwszy")
            ColumnInformation(text = "Forma studiów:", answer = "stacjonarna")
            ColumnInformation(text = "Język studiów:", answer = "polski")
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
