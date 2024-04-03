package com.stepik.numbercomposition.domain.usecases

import com.stepik.numbercomposition.domain.entity.Level
import com.stepik.numbercomposition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level) =
        repository.getGameSettings(level)
}