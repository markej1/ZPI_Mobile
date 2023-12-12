package com.example.zpi_mobile.services

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.zpi_mobile.model.*
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToString

class SubjectService: ViewModel() {

    private val httpClient = KtorHttpClient()

    private val _url = "https://susel.pythonanywhere.com/"
    var allSubjects by mutableStateOf<List<Subject>>(listOf())
    var allBlocks by mutableStateOf<List<Block>>(listOf())
    var program by mutableStateOf<Program?>(null)
    var loading by mutableStateOf(false)

    fun getBlocks(): List<Block> {return listOf()}
    suspend fun getAllSubjects(level: Int, field: String, cycle: String, specialization: Int) {
        val blocks: MutableList<Block> = mutableListOf()
        val subjects: MutableList<Subject> = mutableListOf()
        val semesters: MutableList<List<Subject>> = mutableListOf()

        loading = true

        val program: List<Program> = httpClient
            .getHttpClient()
//            .get(_url + "list-subjects/$level/$field/$cycle/$specialization")
            .get(_url + "list-subjects/1/Informatyka_Stosowana/2023/")
            .body()

        semesters.add(program[0].semester1)
        semesters.add(program[0].semester2)
        semesters.add(program[0].semester3)
        semesters.add(program[0].semester4)
        semesters.add(program[0].semester5)
        semesters.add(program[0].semester6)
        semesters.add(program[0].semester7)

        var semesterId = 1
        for (semester in semesters) {
            for (subject in semester) {
                subjects.add(subject)
                val tempBlock = blocks.find { it.name == subject.module }
                // jezeli w liscie blokow znajduje sie juz blok o danej nazwie to nalezy dodac ten przedmiot do listy przedmiotow w bloku
                if(tempBlock == null) {
                    blocks.add(Block(name = subject.module ?: subject.name, block_type = subject.category, ects = subject.ects, exam = if(subject.hasExam) "E" else "", hours = subject.hours, subjects = mutableListOf(subject), semester = semesterId))
                } else {
                    tempBlock.subjects.add(subject)
                }
            }
            semesterId += 1
        }
        allSubjects = subjects
        allBlocks = blocks.sortedBy { block -> block.name }
        loading = false
    }

    suspend fun getSubjectDetails() {
        loading = true

        val courses: List<ICourse> = httpClient
            .getHttpClient()
            .get(_url + "desc/1/Informatyka_Stosowana/2023/${chosenSubject?.moduleId}/${chosenSubject?.subjectId}")
            .body()

        for (course in courses) {
            when (course) {
                is Lecture -> {
                    chosenSubject?.lecture = course.details
                }
                is Classes -> {
                    chosenSubject?.classes = course.details
                }
                is Laboratory -> {
                    chosenSubject?.laboratory = course.details
                }
                is Seminar -> {
                    chosenSubject?.seminar = course.details
                }
                is Project -> {
                    chosenSubject?.project = course.details
                }
            }
        }
        loading = false
    }

    fun getSubjectsBySemester(semester: Int): List<Block> {
        val resultBlock = mutableListOf<Block>()
        for (block in allBlocks) {
            if (block.semester == semester) resultBlock.add(block)
        }
        return resultBlock.sortedBy { block -> block.name }
    }

    var isDialogShown by mutableStateOf(false)
        private set
    fun showDialog(block: Block) {
        if(block.subjects.size > 1) {
            isDialogShown = true
        }
    }
    fun dismissDialog() {
        isDialogShown = false
    }

    var clickedBlock by mutableStateOf<Block?>(null)
        private set
    fun chooseBlock(block: Block) {
        clickedBlock = block
    }

    var chosenSubject by mutableStateOf<Subject?>(null)
        private set

    fun chooseSubject(subject: Subject) {
        chosenSubject = subject
    }

    fun getSubjectByName(name: String, block: Block): Subject? {
        for (subject in block.subjects) {
            if (subject.name == name) return subject
        }
        return null
    }

}