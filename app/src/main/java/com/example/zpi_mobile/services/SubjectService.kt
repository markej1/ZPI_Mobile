package com.example.zpi_mobile.services

import com.example.zpi_mobile.model.*

class SubjectService {

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

}