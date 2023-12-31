@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.content.Context
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.model.CourseDetails
import com.example.zpi_mobile.http.receive.SubjectService
import com.example.zpi_mobile.model.Subject

@Composable
fun SubjectCardScreen(
    subjectService: SubjectService,
    navController: NavController
) {
    val textStyle = MaterialTheme.typography.bodySmall
    val context = LocalContext.current
    Column {
        SmallTopAppBar(
            title = { Text(
                text = subjectService.chosenSubject!!.name,
                style = MaterialTheme.typography.titleSmall
            ) },
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
                if(subjectService.chosenSubject?.lecture != null) ListItem("Wykład", subjectService.chosenSubject?.lecture, textStyle)
                if(subjectService.chosenSubject?.classes != null) ListItem("Ćwiczenia", subjectService.chosenSubject?.classes, textStyle)
                if(subjectService.chosenSubject?.laboratory != null) ListItem("Laboratorium", subjectService.chosenSubject?.laboratory, textStyle)
                if(subjectService.chosenSubject?.seminar != null) ListItem("Seminarium", subjectService.chosenSubject?.seminar, textStyle)
                if(subjectService.chosenSubject?.project != null) ListItem("Projekt", subjectService.chosenSubject?.project, textStyle)
                if(subjectService.chosenSubject?.curriculumContent?.isEmpty() == false) ProgrammeContent(programmeContent = subjectService.chosenSubject?.curriculumContent, textStyle)
                Link(getLink(subjectService.chosenSubject, context), textStyle)
            }
        }
    }
}

@Composable
fun ListItem(
    courseType: String,
    course: CourseDetails?,
    textStyle: TextStyle
) {
    var expanded by remember { mutableStateOf(true) }

    ElevatedCard(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Row {
                Text(text = courseType, style = textStyle)
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
                    Text(text = "Liczba godzin CNPS:", style = textStyle)
                    Text(text = course?.cnps?.toString() ?: "", style = textStyle, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Liczba godzin ZZU:", style = textStyle)
                    Text(text = course?.zzu?.toString() ?: "", style = textStyle, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Forma zaliczenia:", style = textStyle)
                    if(course != null) {
                        Text(
                            text = course.crediting,
                            style = textStyle,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                        )
                    }
                }
                Row {
                    Text(text = "Punkty ECTS:", style = textStyle)
                    Text(text = course?.ects?.toString() ?: "", style = textStyle, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End))
                }
                Row {
                    Text(text = "Grupa kursów:", style = textStyle)
                    if(course != null) {
                        Text(text = if (course.inGroupCourse) "Tak" else "Nie", style = textStyle, modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProgrammeContent(
    programmeContent: List<String>?,
    textStyle: TextStyle
) {
    var i = 1
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 5.dp)) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "Treści programowe:", style = textStyle, modifier = Modifier.padding(bottom = 5.dp))
            programmeContent?.forEach { item ->
                Row {
                    Text(text = if (i < 10) "$i.   " else "$i. ", style = textStyle)
                    Text(text = item, style = textStyle)
                }
                i += 1
            }
        }
    }
}

@Composable
fun Link(
    url: String,
    textStyle: TextStyle
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
            Text(text = "Link do oryginału:", style = textStyle, modifier = Modifier.padding(bottom = 5.dp))
            Text(text = url, style = textStyle)
        }
    }
}

fun getLink(subject: Subject?, context: Context): String {
    return when(subject?.category) {
        "Physics" -> "https://wppt.pwr.edu.pl/studenci/karty-przedmiotow-poza-wppt"
        "Mathematics" -> "https://wmat.pwr.edu.pl/studenci/kursy-ogolnouczelniane/karty-przedmiotow/karty-przedmiotow-ogolnouczelnianych/studia-stacjonarne"
        "Foreign languages" -> "https://sjo.pwr.edu.pl/"
        "Sporting classes" -> "https://swfis.pwr.edu.pl/"
        else -> "https://wit.pwr.edu.pl/studenci/programy-studiow/${getCycle(context)}-studia-${getLevel(context)}-stopnia"
    }
}

fun getLevel(context: Context): String {
    return if(SharedPreferencesManager(context).getData("level", "") === "I stopień") "i"
    else "ii"
}
fun getCycle(context: Context): String {
    return SharedPreferencesManager(context).getData("cycle", "").split("/").joinToString("-")
}
