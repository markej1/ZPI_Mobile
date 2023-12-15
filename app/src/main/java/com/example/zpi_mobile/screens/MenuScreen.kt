@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.http.receive.MenuService
import com.example.zpi_mobile.http.receive.StartService
import com.example.zpi_mobile.model.ChosenProgram
import com.example.zpi_mobile.navigation.Screen
import com.example.zpi_mobile.repo.StartInformation
import com.example.zpi_mobile.ui.theme.StatusBarColor
import kotlinx.coroutines.launch

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

    val menuService = MenuService()
    val scope = rememberCoroutineScope()
    var chosenProgram by remember {
        mutableStateOf(
            ChosenProgram("", "Próba", false, false, "", true)
        )
    }
    var educationLevel by remember { mutableStateOf("") }
    var isFullTime by remember { mutableStateOf(false) }
    var isGeneralAcademic by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf("") }
    var inPolish by remember { mutableStateOf(true) }

    val startInformation = StartInformation()
    val startService = StartService()
    val levels = startService.getLevels()

    LaunchedEffect(true) {
        scope.launch {
            try {
                val chosenProgramGet = if (specialization == "") {
                    menuService.getChosenProgram(
                        level = startInformation.getLevelInt(sharedPreferencesManager, levels),
                        field = startInformation.improveText(field),
                        cycle = startInformation.makeCycleInt(cycle)
                    )
                } else {
                    menuService.getChosenProgramSpecialization(
                        level = startInformation.getLevelInt(sharedPreferencesManager, levels),
                        field = startInformation.improveText(field),
                        cycle = startInformation.makeCycleInt(cycle),
                        specialization = startInformation.improveText(specialization)
                    )
                }
                chosenProgram = chosenProgramGet
                educationLevel = chosenProgram.education_level
                isFullTime = chosenProgram.is_full_time
                isGeneralAcademic = chosenProgram.is_general_academic
                language = chosenProgram.language
                inPolish = chosenProgram.inPolish
            } catch (_: Exception) {
            }
        }
    }

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
            )
        },
        content = {
            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    LandscapeContent(
                        infoArrangement, level, cycle, specialization, navController,
                        educationLevel, isFullTime, isGeneralAcademic, language, inPolish
                    )
                }
                else -> {
                    PortraitContent(
                        infoArrangement, level, cycle, specialization, navController,
                        educationLevel, isFullTime, isGeneralAcademic, language, inPolish
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
    navController: NavController,
    educationLevel: String,
    isFullTime: Boolean,
    isGeneralAcademic: Boolean,
    language: String,
    inPolish: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(),
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
                    RowInformation(text = "Profil:", answer = getIsGeneralAcademic(isGeneralAcademic, inPolish))
                    RowInformation(text = "Poziom studiów:", answer = getEducationLevel(educationLevel, inPolish))
                    RowInformation(text = "Forma studiów:", answer = getIsFullTime(isFullTime, inPolish))
                    RowInformation(text = "Język studiów:", answer = language)
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
    navController: NavController,
    educationLevel: String,
    isFullTime: Boolean,
    isGeneralAcademic: Boolean,
    language: String,
    inPolish: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(verticalArrangement = infoArrangement) {
            ColumnInformation(text = "Stopień studiów:", answer = level)
            ColumnInformation(text = "Cykl kształcenia:", answer = cycle)
            if (specialization != "") {
                ColumnInformation(text = "Specjalność:", answer = specialization)
            }
            ColumnInformation(text = "Profil:", answer = getIsGeneralAcademic(isGeneralAcademic, inPolish))
            ColumnInformation(text = "Poziom studiów:", answer = getEducationLevel(educationLevel, inPolish))
            ColumnInformation(text = "Forma studiów:", answer = getIsFullTime(isFullTime, inPolish))
            ColumnInformation(text = "Język studiów:", answer = language)
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

fun getEducationLevel(educationLevel: String, inPolish: Boolean): String {
    when (educationLevel) {
        "First-level (inżynier) studies" -> {
            return if (inPolish) {
                "studia pierwszego stopnia (inżynierskie)"
            } else {
                "first-level (inżynier) studies"
            }
        }
        "First-level (licencjat) studies" -> {
            return if (inPolish) {
                "studia pierwszego stopnia (licencjackie)"
            } else {
                "first-level (licencjat) studies"
            }
        }
        "Second-level studies" -> {
            return if (inPolish) {
                "studia drugiego stopnia"
            } else {
                "second-level studies"
            }
        }
        "Magister uniform studies" -> {
            return if (inPolish) {
                "jednolite studia magisterskie"
            } else {
                "magister uniform studies"
            }
        }
    }
    return ""
}

fun getIsFullTime(isFullTime: Boolean, inPolish: Boolean): String {
    return if (isFullTime) {
        if (inPolish) {
            "stacjonarna";
        } else {
            "full-time studies"
        }
    } else {
        if (inPolish) {
            "niestacjonarna";
        } else {
            "part-time studies"
        }
    }
}

fun getIsGeneralAcademic(isGeneralAcademic: Boolean, inPolish: Boolean): String {
    return if (isGeneralAcademic) {
        if (inPolish) {
            "ogólnoakademicki";
        } else {
            "general academic"
        }
    } else {
        if (inPolish) {
            "praktyczny";
        } else {
            "practical"
        }
    }
}
