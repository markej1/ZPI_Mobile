package com.example.zpi_mobile.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.zpi_mobile.model.*

class SubjectService: ViewModel() {

    fun getBlocks(): List<Block> {
        val blocks: List<Block> = listOf(
            Block(
                name = "Organizacja Systemów Komputerowych",
                hours = "21000",
                ects = "5",
                exam = "",
                block_type = "przedmiot kierunkowy",
                subjects = listOf(
                    Subject(
                        id = 1,
                        name = "Organizacja Systemów Komputerowych",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(
                            "Złożoności (1/4), iteratory.",
                            "Złożoności (2/4), listy wiązane.",
                            "Złożoności (3/4), stosy i kolejki zwykłe.",
                            "Złożoności (4/4), techniki rozwiązywania problemów",
                            "Komparatory, sortowania proste.",
                            "Sortowania efektywne. Kopiec. ",
                            "Wyszukiwania liniowe i binarne, kolejki priorytetowe, tablice mieszające",
                            "Drzewa przedziałowe, kopce dwumianowe, las zbiorów rozłącznych.",
                            "Przekazywanie akcji i danych – intencje, współdziałanie aktywności, użycie aktywności systemowych. Obsługa zmiany konfiguracji. "
                        ),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    ),
                    Subject(
                        id = 1,
                        name = "Sieci komputerowe",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    )
                )
            ),
            Block(
                name = "Analiza matematyczna I",
                hours = "21000",
                ects = "5",
                exam = "E",
                block_type = "przedmiot kierunkowy",
                subjects = listOf(
                    Subject(
                        id = 1,
                        name = "Organizacja Systemów Komputerowych",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    ),
                    Subject(
                        id = 1,
                        name = "Sieci komputerowe",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    ),
                    Subject(
                        id = 1,
                        name = "Bazy danych",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    )
                )
            ),
            Block(
                name = "M",
                hours = "21000",
                ects = "5",
                exam = "",
                block_type = "przedmiot kierunkowy",
                subjects = listOf(
                    Subject(
                        id = 1,
                        name = "M",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "TESTTESTTEST",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    )
                )
            ),
            Block(
                name = "Wpowadzenie do zarządzania projektami informatycznymi",
                hours = "21000",
                ects = "5",
                exam = "",
                block_type = "przedmiot kierunkowy",
                subjects = listOf(
                    Subject(
                        id = 1,
                        name = "Wpowadzenie do zarządzania projektami informatycznymi",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    )
                )
            ),
            Block(
                name = "Blok",
                hours = "21000",
                ects = "5",
                exam = "",
                block_type = "przedmiot kierunkowy",
                subjects = listOf(
                    Subject(
                        id = 1,
                        name = "Organizacja Systemów Komputerowych",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    )
                )
            ),
            Block(
                name = "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm",
                hours = "21000",
                ects = "5",
                exam = "",
                block_type = "przedmiot kierunkowy",
                subjects = listOf(
                    Subject(
                        id = 1,
                        name = "Organizacja Systemów Komputerowych",
                        lecture = Lecture(
                            ECTS = "3",
                            ZZU = "30",
                            CNPS = "90",
                            crediting = ""
                        ),
                        classes = Classes(
                            ECTS = "2",
                            ZZU = "15",
                            CNPS = "45",
                            crediting = ""
                        ),
                        group_of_courses = "NIE",
                        programme_content = listOf(),
                        kind_of_subject = "Obowiązkowy",
                        link = "",
                        project = Project(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        seminar = Seminar(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        ),
                        laboratory = Laboratory(
                            ECTS = "",
                            ZZU = "",
                            CNPS = "",
                            crediting = ""
                        )
                    )
                )
            )
        )
        return blocks
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