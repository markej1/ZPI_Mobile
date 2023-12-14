package com.example.zpi_mobile.http.receive

import androidx.compose.runtime.mutableStateOf
import com.example.zpi_mobile.http.KtorHttpClient
import com.example.zpi_mobile.model.ChosenProgram
import io.ktor.client.call.*
import io.ktor.client.request.*

class MenuService {

    private val httpClient = KtorHttpClient()

    val loading = mutableStateOf(false)

    suspend fun getChosenProgram(level: Int, field: String, cycle: Int): ChosenProgram {
        loading.value = true
        val chosenProgram: ChosenProgram = httpClient
            .getHttpClient()
            .get("https://susel.pythonanywhere.com/chosen-program/$level/$field/$cycle/")
            .body()
        loading.value = false
        return chosenProgram
    }

    suspend fun getChosenProgramSpecialization(
        level: Int, field: String, cycle: Int, specialization: String
    ): ChosenProgram {
        loading.value = true
        val chosenProgram: ChosenProgram = httpClient
            .getHttpClient()
            .get("https://susel.pythonanywhere.com/chosen-program/$level/$field/$cycle/$specialization/")
            .body()
        loading.value = false
        return chosenProgram
    }

}