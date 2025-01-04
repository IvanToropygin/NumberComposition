package com.sumin.numbercomposition.domain.repository

import com.sumin.numbercomposition.domain.entity.GameSettings
import com.sumin.numbercomposition.domain.entity.Level
import com.sumin.numbercomposition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}