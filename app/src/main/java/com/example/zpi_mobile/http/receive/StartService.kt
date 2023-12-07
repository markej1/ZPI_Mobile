package com.example.zpi_mobile.http.receive

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.zpi_mobile.http.KtorHttpClient
import com.example.zpi_mobile.model.Level
import io.ktor.client.call.*
import io.ktor.client.request.*

class StartService {

    private val httpClient = KtorHttpClient()

    fun getLevels(): List<Level> {
        return listOf(
            Level(1, "I stopień"),
            Level(2, "II stopień")
        )
    }

    suspend fun getFields(level: Int): SnapshotStateList<String> {
        val fields: List<String> = httpClient
            .getHttpClient()
            .get("https://susel.pythonanywhere.com/list-field/$level/")
            .body()
        val fieldsMutableStateList = mutableStateListOf<String>()
        for (field in fields) {
            fieldsMutableStateList.add(field)
        }
        return fieldsMutableStateList
    }

    fun getCycles(): SnapshotStateList<String> {
        return mutableStateListOf("2020/2021", "2021/2022", "2022/2023", "2023/2024")
    }

    fun getSpecializations(): SnapshotStateList<String> {
        return mutableStateListOf("ZSTI", "PSI", "IO")
    }

}