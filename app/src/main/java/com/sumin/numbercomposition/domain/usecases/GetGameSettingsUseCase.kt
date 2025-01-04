package com.sumin.numbercomposition.domain.usecases

import com.sumin.numbercomposition.domain.entity.GameSettings
import com.sumin.numbercomposition.domain.entity.Level
import com.sumin.numbercomposition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings = repository.getGameSettings(level)
}