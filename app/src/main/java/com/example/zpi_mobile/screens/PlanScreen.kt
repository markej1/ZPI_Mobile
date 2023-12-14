@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.zpi_mobile.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.zpi_mobile.R
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.model.Block
import com.example.zpi_mobile.navigation.Screen
import com.example.zpi_mobile.http.receive.SubjectService
import com.example.zpi_mobile.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlanScreen(
    subjectService: SubjectService,
    navController: NavController
) {
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

    var allSubjects by remember { mutableStateOf(subjectService.allSubjects) }
    var allBlocks by remember { mutableStateOf(listOf<Block>()) }

    val context = LocalContext.current

    var semesterAmount by remember { mutableStateOf(subjectService.semesterAmount) }

    LaunchedEffect(Unit) {
        if(allSubjects.isEmpty()) {
            Log.d("dziala", "get all subjects...")
            subjectService.getAllSubjects(
                level = if(SharedPreferencesManager(context).getData("level", "") === "I stopień") 1 else 2,
                field = SharedPreferencesManager(context).getData("field", ""),
                cycle = 2023,
                specialization = SharedPreferencesManager(context).getData("specialization", "")
            )
            allSubjects = subjectService.allSubjects
            allBlocks = subjectService.allBlocks
            semesterAmount = subjectService.semesterAmount
        }
    }

    BackHandler {
        subjectService.allSubjects = listOf()
        navController.navigateUp()
    }

    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { navController.navigate(Screen.HelpScreen.route) },
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.question_mark),
                    contentDescription = "question_mark"
                )
            }
        }
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TitleColor)
                    .padding(0.dp, 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = SharedPreferencesManager(LocalContext.current)
                        .getData("field", ""),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = ChangeColor)
                    .padding(0.dp, 10.dp)
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
                        style = MaterialTheme.typography.bodyLarge
                    )
                    IconButton(
                        enabled = pagerState.currentPage != semesterAmount - 1,
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    ) {
                        if (pagerState.currentPage != semesterAmount - 1) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "right"
                            )
                        }
                    }
                }
            }
            Box {
                if (subjectService.isDialogShown) {
                    SubjectSelect(subjectService, navController, scope)
                }

                HorizontalPager(
                    count = semesterAmount,
                    state = pagerState,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxSize()
                ) { semester ->
                    when (semester) {
                        0 -> PlanViewAll(subjectService, navController, scope)
                        else -> PlanViewSemester(subjectService, navController, semester, scope)

                    }
                }

                if (subjectService.loading) {
                    CircularProgressIndicator(
                        color = StatusBarColor,
                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).zIndex(1000.0f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectTile(
    isInSelectDialog: Boolean,
    id: Int,
    block: Block,
    subjectService: SubjectService,
    navController: NavController,
    scope: CoroutineScope
) {
    val textStyleBody = MaterialTheme.typography.bodySmall
    var textStyle by remember { mutableStateOf(textStyleBody) }
    var readyToDraw by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        onClick = {
            subjectService.chooseBlock(block)
            if (block.subjects.size > 1 && !isInSelectDialog) {
                subjectService.showDialog(block)
            } else {
                val subject = subjectService.getSubjectByName(block.subjects[id].name, block)
                if (subject != null) {
                    subjectService.chooseSubject(subject)
                    scope.launch {
                        if(subject.lecture == null && subject.classes == null && subject.laboratory == null && subject.seminar == null && subject.project == null) {
                            subjectService.getSubjectDetails(
                                level = if(SharedPreferencesManager(context).getData("level", "") === "I stopień") 1 else 2,
                                field = SharedPreferencesManager(context).getData("field", ""),
                                cycle = 2023,
                                specialization = SharedPreferencesManager(context).getData("specialization", "")
                            )
                        }
//                        subjectService.getSubjectDetails()
                        navController.navigate("subject_card_screen")
                    }
                }
            }
                  },
        colors = CardDefaults.cardColors(
                        containerColor = cardColor(type = block.block_type, isBlock = block.subjects.size > 1 && !isInSelectDialog)
                 ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = 160.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .wrapContentWidth()
            ){
                Text(
                    text = block.ects.toString() + " ECTS",
                    textAlign = TextAlign.Start,
                    style = textStyle,
                    modifier = Modifier
                )
                Text(
                    text = block.exam,
                    textAlign = TextAlign.End,
                    style = textStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.End)
                )
            }
            Text(
                text = if(!isInSelectDialog) block.name else block.subjects[id].name,
                maxLines = 4,
                textAlign = TextAlign.Center,
                style = textStyle,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),

            )
            Text(
                text = block.hours,
                textAlign = TextAlign.Center,
                style = textStyle,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .drawWithContent {
                        if (readyToDraw) drawContent()
                    },
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowHeight) {
                        textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.95)
                    } else {
                        readyToDraw = true
                    }
                }
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PlanViewSemester(
    subjectService: SubjectService,
    navController: NavController,
    semester: Int,
    scope: CoroutineScope
) {
    val blocks = subjectService.getSubjectsBySemester(semester)

    Box(contentAlignment = Alignment.TopCenter) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 164.dp),
        ) {
            items(blocks.size) { index ->

               SubjectTile(false, 0, blocks[index], subjectService, navController, scope)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanViewAll(
    subjectService: SubjectService,
    navController: NavController,
    scope: CoroutineScope
) {
    val blocks: List<Block> = subjectService.allBlocks
    val context = LocalContext.current
    Box(contentAlignment = Alignment.TopCenter) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
        ) {
            items(blocks.size) { index ->
                Card(
                    onClick = {
                        subjectService.chooseBlock(blocks[index])
                        if(blocks[index].subjects.size > 1) {
                            subjectService.showDialog(blocks[index])
                        } else {
                            val subject = subjectService.getSubjectByName(blocks[index].subjects[0].name, blocks[index])
                            if (subject != null) {
                                subjectService.chooseSubject(subject)
                                scope.launch {
                                    if(subject.lecture == null && subject.classes == null && subject.laboratory == null && subject.seminar == null && subject.project == null) {
                                        subjectService.getSubjectDetails(
                                            level = if(SharedPreferencesManager(context).getData("level", "") === "I stopień") 1 else 2,
                                            field = SharedPreferencesManager(context).getData("field", ""),
                                            cycle = 2023,
                                            specialization = SharedPreferencesManager(context).getData("specialization", "")
                                        )
                                    }
                                    navController.navigate("subject_card_screen")
                                }
                            }
                        }
                              },
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor(type = blocks[index].block_type, isBlock = blocks[index].subjects.size > 1)
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
                            .heightIn(min = 110.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = blocks[index].name,
                            maxLines = 4,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectSelect(
    subjectService: SubjectService,
    navController: NavController,
    scope: CoroutineScope
) {
    val clickedBlock = subjectService.clickedBlock
    val subjects = clickedBlock?.subjects

    Dialog(
        onDismissRequest = { subjectService.dismissDialog() },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card {
            LazyColumn(modifier = Modifier.padding(8.dp)) {
                subjects?.size?.let {
                    items(it) { index ->
                        SubjectTile(true, index, clickedBlock, subjectService, navController, scope)
                    }
                }
            }
        }
    }
}


@Composable
fun cardColor(type: String, isBlock: Boolean): Color {
    return when (Pair(type, isBlock)) {
        Pair("Field Of Study", false) -> przedmiotKierunkowy
        Pair("Specialization", false) -> przedmiotSpecjalnosciowy
        Pair("Physics", false) -> przedmiotNaukPodstawowych
        Pair("Mathematics", false) -> przedmiotNaukPodstawowych
        Pair("Basic science", false) -> przedmiotNaukPodstawowych
        Pair("General education", false) -> przedmiotKsztalceniaOgolnego
        Pair("Information technologies", false) -> przedmiotKsztalceniaOgolnego
        Pair("Foreign languages", false) -> przedmiotKsztalceniaOgolnego
        Pair("Liberal-managerial" , false) -> przedmiotKsztalceniaOgolnego
        Pair("Sporting classes" , false) -> przedmiotKsztalceniaOgolnego

        Pair("Field Of Study", true) -> blokKierunkowy
        Pair("Specialization", true) -> blokSpecjalnosciowy
        Pair("Physics", true) -> blokNaukPodstawowych
        Pair("Mathematics", true) -> blokNaukPodstawowych
        Pair("Basic science", true) -> blokNaukPodstawowych
        Pair("General education", true) -> blokKsztalceniaOgolnego
        Pair("Information technologies", true) -> blokKsztalceniaOgolnego
        Pair("Foreign languages", true) -> blokKsztalceniaOgolnego
        Pair("Liberal-managerial" , true) -> blokKsztalceniaOgolnego
        Pair("Sporting classes" , true) -> blokKsztalceniaOgolnego
        else -> Color.LightGray
    }
}

