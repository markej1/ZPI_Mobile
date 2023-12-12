package com.example.zpi_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class CourseDetails(
    val ects: Double,
    val zzu: Double,
    val cnps: Double,
    val hasExam: Boolean,
    val inGroupCourse: Boolean,
    val hasCurriculum: Boolean,
    val curriculumContent: List<String>? = null,
    val crediting: String
)
@Serializable
sealed class ICourse {
    abstract val type: String
    @SerialName("detalis") abstract val details: CourseDetails
}
@Serializable
@SerialName("Lecture")
data class Lecture(
    @SerialName("detalis")  override val details: CourseDetails, override val type: String
): ICourse()

@Serializable
@SerialName("Seminar")
data class Seminar(
    @SerialName("detalis")  override val details: CourseDetails, override val type: String
): ICourse()

@Serializable
@SerialName("Laboratory")
data class Laboratory(
    @SerialName("detalis")  override val details: CourseDetails, override val type: String
): ICourse()

@Serializable
@SerialName("Classes")
data class Classes(
    @SerialName("detalis")  override val details: CourseDetails, override val type: String
): ICourse()

@Serializable
@SerialName("Project")
data class Project(
    @SerialName("detalis")  override val details: CourseDetails, override val type: String
): ICourse()
