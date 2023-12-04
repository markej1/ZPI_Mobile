package com.example.zpi_mobile.model

interface Course {
    val ECTS: String
    val ZZU: String
    val CNPS: String
    val crediting: String
}

data class Lecture(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

data class Seminar(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

data class Laboratory(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

data class Classes(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

data class Project(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course
