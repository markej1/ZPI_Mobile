@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.Start
import com.example.zpi_mobile.navigation.Screen

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

    val start = Start()
    val levels = start.getLevels()
    var fields = remember { mutableStateListOf<String>() }
    var cycles = remember { mutableStateListOf<String>() }
    var specializations = remember { mutableStateListOf<String>() }


    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = "Programy studiów", color = Color.Black) },
//                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xff0f9d58)),
//            )
//        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 50.dp)
            ) {
                Column {
                    StartProgramChoice(
                        text = "Wybierz stopień studiów",
                        visibility = true,
                        possibilities = levels,
                        key = "level",
                        onClick = {
                            visible2 = true
                            visible3 = false
                            visible4 = false
                            sharedPreferencesManager.saveData("field", "")
                            sharedPreferencesManager.saveData("cycle", "")
                            sharedPreferencesManager.saveData("specialization", "")
                            fields = start.getFields()
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
                            cycles = start.getCycles()
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
                            specializations = start.getSpecializations()
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
    )
}

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

        Column(
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
                modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                    outlineTextFieldSize = layoutCoordinates.size.toSize()
                },
                trailingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Expand",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                }
            )
            DropdownMenu(
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
                        text = { Text(possibility) },
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