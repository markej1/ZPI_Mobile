@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    var cycles = remember { mutableStateListOf<String>() }
    var specializations = remember { mutableStateListOf<String>() }

    val levelNames = levels.map { it.levelName }

    val scope = rememberCoroutineScope()


    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = "Programy studiów", color = Color.Black) },
//                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xff0f9d58)),
//            )
//        },
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
                                    visible2 = true
                                    visible3 = false
                                    visible4 = false
                                    sharedPreferencesManager.saveData("field", "")
                                    sharedPreferencesManager.saveData("cycle", "")
                                    sharedPreferencesManager.saveData("specialization", "")

                                    scope.launch {
                                        try {
                                            fields = startService.getFields(
                                                getLevelInt(
                                                    sharedPreferencesManager = sharedPreferencesManager,
                                                    levels = levels
                                                )
                                            )
                                        }
                                        catch (e: Exception) {
                                            Log.d("ococho6", e.toString())
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
                                    visible3 = true
                                    visible4 = false
                                    sharedPreferencesManager.saveData("cycle", "")
                                    sharedPreferencesManager.saveData("specialization", "")
                                    cycles = startService.getCycles()
                                }
                            )
                            StartProgramChoice(
                                text = "Wybierz cykl kształcenia",
                                visibility = visible3,
                                possibilities = cycles,
                                key = "cycle",
                                onClick = {
                                    visible4 = true
                                    sharedPreferencesManager.saveData("specialization", "")
                                    specializations = startService.getSpecializations()
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
    onClick: () -> Unit
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
                        sharedPreferencesManager.saveData(key, it)
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
                            }
                        )
                    }
                }
            }
        }

    }
}

fun getLevelInt(sharedPreferencesManager: SharedPreferencesManager, levels: List<Level>): Int {
    val levelName = sharedPreferencesManager.getData("level","")
    for (level in levels) {
        if (levelName == level.levelName) {
            return level.number
        }
    }
    return -1
}