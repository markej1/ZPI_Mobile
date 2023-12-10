package com.example.zpi_mobile.model

import kotlinx.serialization.Serializable

interface Course {
    val ects: Int
    val zzu: Int
    val cnps: Int
    val hasExam: Boolean
    val inGroupCourse: Boolean
}
@Serializable
data class Lecture(
    override val ects: Int,
    override val zzu: Int,
    override val cnps: Int,
    override val hasExam: Boolean,
    override val inGroupCourse: Boolean
): Course
@Serializable
data class Seminar(
    override val ects: Int,
    override val zzu: Int,
    override val cnps: Int,
    override val hasExam: Boolean,
    override val inGroupCourse: Boolean
): Course
@Serializable
data class Laboratory(
    override val ects: Int,
    override val zzu: Int,
    override val cnps: Int,
    override val hasExam: Boolean,
    override val inGroupCourse: Boolean
): Course
@Serializable
data class Classes(
    override val ects: Int,
    override val zzu: Int,
    override val cnps: Int,
    override val hasExam: Boolean,
    override val inGroupCourse: Boolean
): Course
@Serializable
data class Project(
    override val ects: Int,
    override val zzu: Int,
    override val cnps: Int,
    override val hasExam: Boolean,
    override val inGroupCourse: Boolean
): Course
