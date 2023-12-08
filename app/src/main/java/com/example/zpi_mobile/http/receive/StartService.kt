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
        return getStringMutableStateListFromList(fields)
    }

    suspend fun getCycles(level: Int, field: String): SnapshotStateList<Int> {
        val cycles: List<Int> = httpClient
            .getHttpClient()
            .get("https://susel.pythonanywhere.com/list-cycle/$level/${removeWrongSigns(field)}/")
            .body()

        return getIntMutableStateListFromList(cycles)
    }

    suspend fun getSpecializations(level: Int, field: String, cycle: Int): SnapshotStateList<String> {
        val specializations: List<String> = httpClient
            .getHttpClient()
            .get("https://susel.pythonanywhere.com/list-specialization/$level/${removeWrongSigns(field)}/$cycle/")
            .body()

        return getStringMutableStateListFromList(specializations)
    }

    private fun removeWrongSigns(text: String): String {
        return text
            .split(" ")
            .joinToString(separator = "_")
            .split("/")
            .joinToString(separator = "_")
    }

    private fun getStringMutableStateListFromList(list: List<String>): SnapshotStateList<String> {
        val mutableStateList = mutableStateListOf<String>()
        for (element in list) {
            mutableStateList.add(element)
        }
        return mutableStateList
    }

    private fun getIntMutableStateListFromList(list: List<Int>): SnapshotStateList<Int> {
        val mutableStateList = mutableStateListOf<Int>()
        for (element in list) {
            mutableStateList.add(element)
        }
        return mutableStateList
    }

}
