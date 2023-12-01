@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

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

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = semesters.size,
            modifier = Modifier.fillMaxSize()
        ) {
            index -> Text(text = semesters[index])
        }
    }

}