@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.zpi_mobile.R
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.http.receive.StartService
import com.example.zpi_mobile.model.Level
import com.example.zpi_mobile.navigation.Screen
import com.example.zpi_mobile.ui.theme.StartBackgroundColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController) {
    var visible2 by remember { mutableStateOf(false) }
    var visible3 by remember { mutableStateOf(false) }
    var visible4 by remember { mutableStateOf(false) }

    val sharedPreferencesManager = SharedPreferencesManager(LocalContext.current)
    sharedPreferencesManager.saveData("level", "")
    sharedPreferencesManager.saveData("field", "")
    sharedPreferencesManager.saveData("cycle", "")
    sharedPreferencesManager.saveData("specialization", "")

    val startService = StartService()
    val levels = startService.getLevels()
    var fields = remember { mutableStateListOf<String>() }
    var specializations = remember { mutableStateListOf<String>() }

    val levelNames = levels.map { it.levelName }
    var levelNumber by remember { mutableStateOf(0) }
    var cyclesDisplay = remember { mutableStateListOf<String>() }

    val scope = rememberCoroutineScope()

    val loading = startService.loading

    Scaffold(
        content = {
            Box(modifier = Modifier.background(StartBackgroundColor)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 50.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.title_logo),
                            contentDescription = "logo"
                        )
                        Text(
                            text = "Programy studiów",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Column {
                            StartProgramChoice(
                                text = "Wybierz stopień studiów",
                                visibility = true,
                                possibilities = levelNames,
                                key = "level",
                                onClick = {
                                    visible2 = false
                                },
                                onValueChanged = {
                                    scope.launch {
                                        try {
                                            levelNumber = getLevelInt(
                                                sharedPreferencesManager = sharedPreferencesManager,
                                                levels = levels
                                            )
                                            fields = startService.getFields(levelNumber)
                                            visible2 = true
                                            visible3 = false
                                            visible4 = false
                                            sharedPreferencesManager.saveData("field", "")
                                            sharedPreferencesManager.saveData("cycle", "")
                                            sharedPreferencesManager.saveData("specialization", "")
                                        } catch (_: Exception) {
                                        }
                                    }
                                }
                            )
                            StartProgramChoice(
                                text = "Wybierz kierunek studiów",
                                visibility = visible2,
                                possibilities = fields,
                                key = "field",
                                onClick = {
                                    visible3 = false
                                },
                                onValueChanged = {
                                    scope.launch {
                                        try {
                                            val cycles = startService.getCycles(
                                                level = levelNumber,
                                                field = sharedPreferencesManager
                                                    .getData("field", "")
                                            )
                                            cyclesDisplay = getDisplayedCycles(cycles)
                                            visible3 = true
                                            visible4 = false
                                            sharedPreferencesManager.saveData("cycle", "")
                                            sharedPreferencesManager.saveData("specialization", "")
                                        } catch (_: Exception) {
                                        }
                                    }
                                }
                            )
                            StartProgramChoice(
                                text = "Wybierz cykl kształcenia",
                                visibility = visible3,
                                possibilities = cyclesDisplay,
                                key = "cycle",
                                onClick = {
                                    visible4 = false
                                },
                                onValueChanged = {
                                    scope.launch {
                                        try {
                                            specializations = startService.getSpecializations(
                                                level = levelNumber,
                                                field = sharedPreferencesManager
                                                    .getData("field", ""),
                                                cycle = makeCycleInt(
                                                    sharedPreferencesManager
                                                        .getData("cycle", "")
                                                )
                                            )
                                            visible4 = true
                                            sharedPreferencesManager.saveData("specialization", "")
                                            if (specializations.size == 0) {
                                                navController.navigate(Screen.MenuScreen.route)
                                            }
                                        } catch (_: Exception) {
                                        }
                                    }
                                }
                            )
                            StartProgramChoice(
                                text = "Wybierz specjalność",
                                visibility = visible4,
                                possibilities = specializations,
                                key = "specialization",
                                onClick = {
                                    navController.navigate(Screen.MenuScreen.route)
                                }
                            )
                        }
                    }
                }
            }
            if (loading.value) {
                CircularProgressIndicator()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartProgramChoice(
    text: String,
    visibility: Boolean? = false,
    possibilities: List<String>,
    key: String,
    onClick: () -> Unit = {},
    onValueChanged: () -> Unit = {}
) {
    if (visibility == true) {
        val sharedPreferencesManager = SharedPreferencesManager(LocalContext.current)
        var expanded by remember { mutableStateOf(false) }
        val icon = if (expanded) {
            Icons.Filled.KeyboardArrowUp
        } else {
            Icons.Filled.KeyboardArrowDown
        }
        var outlineTextFieldSize by remember { mutableStateOf(Size.Zero) }

        Box {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                OutlinedTextField(
                    value = sharedPreferencesManager.getData(key, ""),
                    onValueChange = {
//                        sharedPreferencesManager.saveData(key, it)
                    },
                    label = { Text(text) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            outlineTextFieldSize = coordinates.size.toSize()
                        },
                    trailingIcon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Expand",
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    },
                    readOnly = true
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(
                        with(LocalDensity.current) {
                            outlineTextFieldSize.width.toDp()
                        }
                    )
                ) {
                    possibilities.forEach { possibility ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = possibility,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onClick = {
                                sharedPreferencesManager.saveData(key, possibility)
                                expanded = false
                                onClick()
                                onValueChanged()
                            }
                        )
                    }
                }
            }
        }

    }
}

fun getLevelInt(sharedPreferencesManager: SharedPreferencesManager, levels: List<Level>): Int {
    val levelName = sharedPreferencesManager.getData("level", "")
    for (level in levels) {
        if (levelName == level.levelName) {
            return level.number
        }
    }
    return -1
}

fun getDisplayedCycles(cyclesInt: SnapshotStateList<Int>): SnapshotStateList<String> {
    val displayCyclesList = mutableStateListOf<String>()
    for (cycle in cyclesInt) {
        var cycleText = cycle.toString()
        cycleText += "/${cycle + 1}"
        displayCyclesList.add(cycleText)
    }
    return displayCyclesList
}

fun makeCycleInt(cycle: String): Int {
    return Integer.parseInt(cycle.split("/")[0])
}