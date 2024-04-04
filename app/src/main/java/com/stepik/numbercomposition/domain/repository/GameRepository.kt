package com.stepik.numbercomposition.domain.repository

import com.stepik.numbercomposition.domain.entity.GameSettings
import com.stepik.numbercomposition.domain.entity.Level
import com.stepik.numbercomposition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}