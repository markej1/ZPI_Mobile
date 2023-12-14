package com.example.zpi_mobile.repo

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.zpi_mobile.SharedPreferencesManager
import com.example.zpi_mobile.model.Level

class StartInformation {

//    fun makeLevelInt(level: String): Int {
//        return if (level == "I stopień") {
//            1
//        } else {
//            2
//        }
//    }

    fun getLevelInt(sharedPreferencesManager: SharedPreferencesManager, levels: List<Level>): Int {
        val levelName = sharedPreferencesManager.getData("level", "")
        for (level in levels) {
            if (levelName == level.levelName) {
                return level.number
            }
        }
        return -1
    }

    fun getDisplayedCycles(cyclesInt: SnapshotStateList<Int>): SnapshotStateList<String> {
        val displayCyclesList = mutableStateListOf<String>()
        for (cycle in cyclesInt) {
            var cycleText = cycle.toString()
            cycleText += "/${cycle + 1}"
            displayCyclesList.add(cycleText)
        }
        return displayCyclesList
    }

    fun makeCycleInt(cycle: String): Int {
        return Integer.parseInt(cycle.split("/")[0])
    }

    fun improveText(text: String): String {
        var newText = ""
        val words = text.split(" ")
        for (word in words) {
            newText += word
            newText += "_"
        }
        newText = newText.substring(0, newText.length-1)
        return newText
    }

}