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

//    fun getBlocks(): List<Block> {
//        val blocks: List<Block> = listOf(
//            Block(
//                name = "Organizacja Systemów Komputerowych",
//                hours = "21000",
//                ects = "5",
//                exam = "",
//                block_type = "przedmiot kierunkowy",
//                subjects = listOf(
//                    Subject(
//                        id = 1,
//                        name = "Organizacja Systemów Komputerowych",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(
//                            "Złożoności (1/4), iteratory.",
//                            "Złożoności (2/4), listy wiązane.",
//                            "Złożoności (3/4), stosy i kolejki zwykłe.",
//                            "Złożoności (4/4), techniki rozwiązywania problemów",
//                            "Komparatory, sortowania proste.",
//                            "Sortowania efektywne. Kopiec. ",
//                            "Wyszukiwania liniowe i binarne, kolejki priorytetowe, tablice mieszające",
//                            "Drzewa przedziałowe, kopce dwumianowe, las zbiorów rozłącznych.",
//                            "Przekazywanie akcji i danych – intencje, współdziałanie aktywności, użycie aktywności systemowych. Obsługa zmiany konfiguracji. "
//                        ),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    ),
//                    Subject(
//                        id = 1,
//                        name = "Sieci komputerowe",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    )
//                )
//            ),
//            Block(
//                name = "Analiza matematyczna I",
//                hours = "21000",
//                ects = "5",
//                exam = "E",
//                block_type = "przedmiot kształcenia ogólnego",
//                subjects = listOf(
//                    Subject(
//                        id = 1,
//                        name = "Organizacja Systemów Komputerowych",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    ),
//                    Subject(
//                        id = 1,
//                        name = "Sieci komputerowe",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    ),
//                    Subject(
//                        id = 1,
//                        name = "Bazy danych",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    )
//                )
//            ),
//            Block(
//                name = "M",
//                hours = "21000",
//                ects = "5",
//                exam = "",
//                block_type = "przedmiot specjalnościowy",
//                subjects = listOf(
//                    Subject(
//                        id = 1,
//                        name = "M",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "TESTTESTTEST",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    )
//                )
//            ),
//            Block(
//                name = "Wpowadzenie do zarządzania projektami informatycznymi",
//                hours = "21000",
//                ects = "5",
//                exam = "",
//                block_type = "przedmiot nauk podstawowych",
//                subjects = listOf(
//                    Subject(
//                        id = 1,
//                        name = "Wpowadzenie do zarządzania projektami informatycznymi",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    )
//                )
//            ),
//            Block(
//                name = "Blok",
//                hours = "21000",
//                ects = "5",
//                exam = "",
//                block_type = "blok kierunkowy",
//                subjects = listOf(
//                    Subject(
//                        id = 1,
//                        name = "Organizacja Systemów Komputerowych",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    )
//                )
//            ),
//            Block(
//                name = "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm",
//                hours = "21000",
//                ects = "5",
//                exam = "",
//                block_type = "blok kształcenia ogólnego",
//                subjects = listOf(
//                    Subject(
//                        id = 1,
//                        name = "Organizacja Systemów Komputerowych",
//                        lecture = Lecture(
//                            ECTS = "3",
//                            ZZU = "30",
//                            CNPS = "90",
//                            crediting = ""
//                        ),
//                        classes = Classes(
//                            ECTS = "2",
//                            ZZU = "15",
//                            CNPS = "45",
//                            crediting = ""
//                        ),
//                        group_of_courses = "NIE",
//                        programme_content = listOf(),
//                        kind_of_subject = "Obowiązkowy",
//                        link = "",
//                        project = Project(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        seminar = Seminar(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        ),
//                        laboratory = Laboratory(
//                            ECTS = "",
//                            ZZU = "",
//                            CNPS = "",
//                            crediting = ""
//                        )
//                    )
//                )
//            )
//        )
//        return blocks
//    }

    private val _url = "https://susel.pythonanywhere.com/"
    var allSubjects by mutableStateOf<List<Subject>>(listOf())
    var allBlocks by mutableStateOf<List<Block>>(listOf())
    var program by mutableStateOf<Program?>(null)

    fun getBlocks(): List<Block> {return listOf()}
    suspend fun getAllSubjects(level: Int, field: String, cycle: String, specialization: Int) {
        val blocks: MutableList<Block> = mutableListOf()
        val subjects: MutableList<Subject> = mutableListOf()
        val semesters: MutableList<List<Subject>> = mutableListOf()

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
                    blocks.add(Block(name = subject.module ?: subject.name, block_type = subject.category, ects = "5", exam = "E", hours = "21000", subjects = mutableListOf(subject), semester = semesterId))
                } else {
                    tempBlock.subjects.add(subject)
                }
            }
            semesterId += 1
        }
        allSubjects = subjects
        allBlocks = blocks
    }
    fun getSubjectsBySemester(semester: Int): List<Block> {
        val resultBlock = mutableListOf<Block>()
        for (block in allBlocks) {
            if (block.semester == semester) resultBlock.add(block)
        }
        Log.d("galo", resultBlock.toString())
        return resultBlock
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