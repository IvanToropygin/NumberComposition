package com.sumin.numbercomposition.domain.usecases

import com.sumin.numbercomposition.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int) =
        repository.generateQuestion(
            maxSumValue = maxSumValue,
            countOfOptions = COUNT_OF_OPTIONS
        )


    private companion object {

        private const val COUNT_OF_OPTIONS = 6
    }
}