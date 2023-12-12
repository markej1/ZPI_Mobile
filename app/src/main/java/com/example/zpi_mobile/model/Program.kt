package com.example.zpi_mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//interface IProgram {
//    @SerialName("1")
//    val semester1: Semester
//    @SerialName("2")
//    val semester2: Semester
//    @SerialName("3")
//    val semester3: Semester
//    @SerialName("4")
//    val semester4: Semester
//    @SerialName("5")
//    val semester5: Semester
//    @SerialName("6")
//    val semester6: Semester
//    @SerialName("7")
//    val semester7: Semester
//}
@Serializable
data class Program (
    @SerialName("1") val semester1: List<Subject>,
    @SerialName("2") val semester2: List<Subject>,
    @SerialName("3") val semester3: List<Subject>,
    @SerialName("4") val semester4: List<Subject>,
    @SerialName("5") val semester5: List<Subject>,
    @SerialName("6") val semester6: List<Subject>,
    @SerialName("7") val semester7: List<Subject>
)
