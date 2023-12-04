@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlanScreen() {
    val semesters = listOf(
        "Wszystko",
        "Semestr 1.",
        "Semestr 2.",
        "Semestr 3.",
        "Semestr 4.",
        "Semestr 5.",
        "Semestr 6.",
        "Semestr 7."
    )

    Column() {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .padding(0.dp, 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Informatyka Stosowana",
                    color = Color.Black,
                    fontSize = 32.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray)
                    .padding(0.dp, 15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "left"
                        )
                    }
                    Text(
                        text = "Wszystko",
                        fontSize = 24.sp
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "right"
                        )
                    }
                }
            }

        }

        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                count = semesters.size,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                //                Text(text = semesters[index])
                Column {
                    Row {
                        SubjectTile()
                        SubjectTile()
                        SubjectTile()
                    }
                }
            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectTile() {
    ElevatedCard(
        onClick = {},
        colors = CardDefaults.cardColors(containerColor = Color.Green),
        modifier = Modifier
            .padding(all = 10.dp)
            .width(100.dp)

    ) {
        Text(
            text = "Aplikacje mobilne na platformę android",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(all = 10.dp)
        )
    }

}