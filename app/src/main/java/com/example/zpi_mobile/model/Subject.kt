package com.example.zpi_mobile.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
    val lecture: Lecture?
    val classes: Classes?
    val seminar: Seminar?
    val laboratory: Laboratory?
    val project: Project?
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
    override val lecture: Lecture? = null,
    override val classes: Classes? = null,
    override val seminar: Seminar? = null,
    override val laboratory: Laboratory? = null,
    override val project: Project? = null
): ISubject

