@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.zpi_mobile.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zpi_mobile.model.Course
import com.example.zpi_mobile.services.SubjectService

@Composable
fun SubjectCardScreen(
    subjectService: SubjectService,
    navController: NavController
) {
    Column {
        SmallTopAppBar(
            title = { Text(text = subjectService.chosenSubject!!.name) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }, content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate back"
                    )
                })
            }
        )
        LazyColumn {
            items(1) {
                SubjectInfo(subjectService)
                ListItem("Wykład", subjectService.chosenSubject!!.lecture)
                ListItem("Ćwiczenia", subjectService.chosenSubject!!.classes)
                ListItem("Laboratorium", subjectService.chosenSubject!!.laboratory)
                ListItem("Seminarium", subjectService.chosenSubject!!.seminar)
                ListItem("Projekt", subjectService.chosenSubject!!.project)
                ProgrammeContent(programmeContent = subjectService.chosenSubject!!.programme_content)
                Link("https://wit.pwr.edu.pl/studenci/programy-studiow/2023-2024-studia-i-stopnia")
            }
        }
    }
}

@Composable
fun SubjectInfo(
    subjectService: SubjectService
) {
    ElevatedCard(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "Rodzaj przedmiotu:")
            Text(text = subjectService.chosenSubject!!.kind_of_subject, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Composable
fun ListItem(
    courseType: String,
    course: Course
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
                Text(text = courseType)
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
                    Text(text = course.CNPS, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Liczba godzin ZZU:")
                    Text(text = course.ZZU, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Forma zaliczenia:")
                    Text(text = course.crediting, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Punkty ECTS:")
                    Text(text = course.ECTS, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Grupa kursów:")
                    Text(text = course.crediting, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
            }
        }
    }
}

@Composable
fun ProgrammeContent(
    programmeContent: List<String>
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
fun Link(
    url: String
) {
    val context = LocalContext.current
    ElevatedCard(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
                  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "Link do oryginału:", modifier = Modifier.padding(bottom = 5.dp))
            Text(text = url)
        }
    }
}
