package com.example.zpi_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.zpi_mobile.ui.theme.*

@Composable
fun HelpScreen() {
    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = "Zajęcia obowiązkowe:",
                style = MaterialTheme.typography.titleMedium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                RowSubjectType(color = przedmiotKierunkowy, type = "kierunkowe")
                RowSubjectType(color = przedmiotSpecjalnosciowy, type = "specjalnościowe")
                RowSubjectType(
                    color = przedmiotNaukPodstawowych,
                    type = "z zakresu nauk podstawowych"
                )
                RowSubjectType(color = przedmiotKsztalceniaOgolnego, type = "kształcenia ogólnego")
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = "Bloki wybieralne:",
                style = MaterialTheme.typography.titleMedium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                RowSubjectType(color = blokKierunkowy, type = "kierunkowe")
                RowSubjectType(color = blokSpecjalnosciowy, type = "specjalnościowe")
                RowSubjectType(color = blokNaukPodstawowych, type = "z zakresu nauk podstawowych")
                RowSubjectType(color = blokKsztalceniaOgolnego, type = "kształcenia ogólnego")
            }
        }
    }
}

@Composable
fun RowSubjectType(color: Color, type: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color)
        ) {}
        Text(text = type)
    }
}
