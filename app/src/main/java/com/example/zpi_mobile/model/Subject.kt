package com.example.zpi_mobile.model

interface ISubject {
    val id: Int
    val name: String
    val lecture: Lecture
    val classes: Classes
    val seminar: Seminar
    val laboratory: Laboratory
    val project: Project
    val group_of_courses: String
    val kind_of_subject: String
    val programme_content: List<String>
    val link: String
}

data class Subject(
    override val id: Int,
    override val name: String,
    override val lecture: Lecture,
    override val classes: Classes,
    override val seminar: Seminar,
    override val laboratory: Laboratory,
    override val project: Project,
    override val group_of_courses: String,
    override val kind_of_subject: String,
    override val programme_content: List<String>,
    override val link: String
): ISubject
