@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.zpi_mobile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.zpi_mobile.services.SubjectService

@Composable
fun SubjectCardScreen(
    subjectService: SubjectService
) {
    Column {
        SmallTopAppBar(
            title = { Text(text = "Nazwa przedmiotu") },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }, content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate back"
                    )
                })
            }
        )
        LazyColumn {
            items(1) {
                SubjectInfo()
                ListItem("Wykład")
                ListItem("Ćwiczenia")
                ListItem("Laboratorium")
                ListItem("Seminarium")
                ListItem("Projekt")
                ProgrammeContent(programmeContent = arrayOf<String>(
                    "Złożoności (1/4), iteratory.",
                    "Złożoności (2/4), listy wiązane.",
                    "Złożoności (3/4), stosy i kolejki zwykłe.",
                    "Złożoności (4/4), techniki rozwiązywania problemów",
                    "Komparatory, sortowania proste.",
                    "Sortowania efektywne. Kopiec. ",
                    "Wyszukiwania liniowe i binarne, kolejki priorytetowe, tablice mieszające",
                    "Drzewa przedziałowe, kopce dwumianowe, las zbiorów rozłącznych.",
                    "Przekazywanie akcji i danych – intencje, współdziałanie aktywności, użycie aktywności systemowych. Obsługa zmiany konfiguracji. "
                ))
                Link()
            }
        }
    }
}

@Composable
fun SubjectInfo() {
    ElevatedCard(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "Rodzaj przedmiotu:")
            Text(text = "Obowiązkowy", modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Composable
fun ListItem(
    course: String
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Row {
                Text(text = course)
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "expand card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                )
            }

            if (expanded) {
                Row {
                    Text(text = "Liczba godzin CNPS:")
                    Text(text = "30", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Liczba godzin ZZU:")
                    Text(text = "150", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Forma zaliczenia:")
                    Text(text = "Zaliczenie na ocenę", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Punkty ECTS:")
                    Text(text = "5", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Grupa kursów:")
                    Text(text = "Tak", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
            }
        }
    }
}

@Composable
fun ProgrammeContent(
    programmeContent: Array<String>
) {
    var i = 1
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 5.dp)) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "Treści programowe:", modifier = Modifier.padding(bottom = 5.dp))
            programmeContent.forEach { item ->
                Row {
                    Text(text = if (i < 10) "$i.   " else "$i. ")
                    Text(text = item)
                }
                i += 1
            }
        }
    }
}

@Composable
fun Link() {
    ElevatedCard(onClick = { /*TODO*/ }, modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 5.dp)) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "Link do oryginału:", modifier = Modifier.padding(bottom = 5.dp))
            Text(text = "https://wit.pwr.edu.pl/studenci/programy-studiow/2023-2024-studia-i-stopnia")
        }
    }
}
