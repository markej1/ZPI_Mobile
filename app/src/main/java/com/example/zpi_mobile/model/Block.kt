package com.example.zpi_mobile.model

import kotlinx.serialization.Serializable

interface IBlock {
    val name: String
    val hours: String
    val ects: String
    val exam: String
    val block_type: String
    val subjects: MutableList<Subject>
    var semester: Int?
}
@Serializable
data class Block(
    override val name: String,
    override val hours: String,
    override val ects: String,
    override val exam: String,
    override val block_type: String,
    override val subjects: MutableList<Subject>,
    override var semester: Int? = null
): IBlock
