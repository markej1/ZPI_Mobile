package com.example.zpi_mobile.model

import kotlinx.serialization.Serializable

interface Course {
    val ECTS: String
    val ZZU: String
    val CNPS: String
    val crediting: String
}

@Serializable
data class Lecture(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

@Serializable
data class Seminar(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

@Serializable
data class Laboratory(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

@Serializable
data class Classes(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course

@Serializable
data class Project(
    override val ECTS: String,
    override val ZZU: String,
    override  val CNPS: String,
    override val crediting: String
): Course
