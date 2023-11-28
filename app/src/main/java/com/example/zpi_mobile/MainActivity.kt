@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.zpi_mobile.ui.theme.ZPIMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZPIMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartScreen()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StartScreen() {
        val visible1 by remember { mutableStateOf(true) }
        var visible2 by remember { mutableStateOf(false) }
        var visible3 by remember { mutableStateOf(false) }
        var visible4 by remember { mutableStateOf(false) }

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
                            visibility = visible1,
                            onClick = {
                                visible2 = true
                                visible3 = false
                                visible4 = false
                            }
                        )
                        StartProgramChoice(
                            text = "Wybierz kierunek studiów",
                            visibility = visible2,
                            onClick = {
                                visible3 = true
                                visible4 = false
                            }
                        )
                        StartProgramChoice(
                            text = "Wybierz cykl kształcenia",
                            visibility = visible3,
                            onClick = {
                                visible4 = true
                            }
                        )
                        StartProgramChoice(
                            text = "Wybierz specjalność",
                            visibility = visible4,
                            onClick = {

                            }
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun StartProgramChoice(
        text: String = "M",
        visibility: Boolean? = false,
        possibilities: List<String> = listOf("IST", "IST - ang.", "IS", "IZ", "Z"),
        onClick: () -> Unit
    ) {
        if (visibility == true) {
            var selectedText by remember { mutableStateOf("") }
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
                    value = selectedText,
                    onValueChange = { selectedText = it },
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
                                selectedText = possibility
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZPIMobileTheme {}
}