package com.example.zpi_mobile.http.receive

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.zpi_mobile.http.KtorHttpClient
import com.example.zpi_mobile.model.Level
import com.example.zpi_mobile.model.MockStringList
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class StartService {

    private val httpClient = KtorHttpClient()

    fun getLevels(): List<Level> {
        return listOf(
            Level(1, "I stopień"),
            Level(2, "II stopień")
        )
    }

    suspend fun getFields(level: Int): SnapshotStateList<String> {
        Log.d("ococho6", level.toString())
        val fields: MockStringList = httpClient
            .getHttpClient()
            .get("https://susel.pythonanywhere.com/list-field/1/") {
                contentType(ContentType.Application.Json)
            }
            .body()
        Log.d("ococho6", fields.names[0])
        val fieldsMutableStateList = mutableStateListOf<String>()
        for (field in fields.names) {
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