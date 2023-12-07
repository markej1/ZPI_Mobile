package com.example.zpi_mobile.http.receive

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class StartService {

    fun getLevels(): List<String> {
        return listOf("I stopień", "II stopień")
    }

    fun getFields(): SnapshotStateList<String> {
        return mutableStateListOf(
            "Informatyka Stosowana",
            "Inżynieria Systemów",
            "Inżynieria Zarządzania",
            "Zarządzanie"
        )
    }

    fun getCycles(): SnapshotStateList<String> {
        return mutableStateListOf("2020/2021", "2021/2022", "2022/2023", "2023/2024")
    }

    fun getSpecializations(): SnapshotStateList<String> {
        return mutableStateListOf("ZSTI", "PSI", "IO")
    }

}