@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zpi_mobile.model.Block
import com.example.zpi_mobile.services.SubjectService
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
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

    val pagerState = rememberPagerState()
    val semester = semesters[pagerState.currentPage]
    val scope = rememberCoroutineScope()

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
                    IconButton(
                        enabled = pagerState.currentPage != 0,
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    ) {
                        if (pagerState.currentPage != 0) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "left"
                            )
                        }
                    }
                    Text(
                        text = semester,
                        fontSize = 24.sp
                    )
                    IconButton(
                        enabled = pagerState.currentPage != semesters.size - 1,
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    ) {
                        if (pagerState.currentPage != semesters.size - 1) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "right"
                            )
                        }
                    }
                }
            }

        }


        HorizontalPager(
            count = semesters.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            when (index) {
                0 -> PlanViewAll()
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanViewAll() {
    val subjectService = SubjectService()
    val subjects: List<Block> = subjectService.getBlocks()
    Box(contentAlignment = Alignment.TopCenter) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
        ) {
            items(subjects.size) { index ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Blue
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .heightIn(min = 120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = subjects[index].name,
                            maxLines = 4,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}}