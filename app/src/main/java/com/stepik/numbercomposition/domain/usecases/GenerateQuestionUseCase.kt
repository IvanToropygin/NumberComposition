package com.stepik.numbercomposition.domain.usecases

import com.stepik.numbercomposition.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int) =
        repository.generateQuestion(maxSumValue, COUNT_OF_OPTION)

    private companion object {

        private const val COUNT_OF_OPTION = 6
    }
}