package com.example.zpi_mobile.model

import kotlinx.serialization.Serializable


interface ISubject {
//    val kind_of_subject: String
//    val link: String
    val name: String
    val code: String
    val curriculumContent: Any
    val category: String
    val subjectId: String
    val moduleId: String
    val module: String?
    var lecture: CourseDetails?
    var classes: CourseDetails?
    var seminar: CourseDetails?
    var laboratory: CourseDetails?
    var project: CourseDetails?
}
@Serializable
data class Subject(
//    override val kind_of_subject: String,
//    override val link: String
    override val name: String,
    override val code: String,
    override val curriculumContent: List<String>,
    override val category: String,
    override val subjectId: String,
    override val moduleId: String,
    override val module: String? = null,
    override var lecture: CourseDetails? = null,
    override var classes: CourseDetails? = null,
    override var seminar: CourseDetails? = null,
    override var laboratory: CourseDetails? = null,
    override var project: CourseDetails? = null
): ISubject

