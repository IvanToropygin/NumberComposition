package com.sumin.numbercomposition.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.data.GameRepositoryImpl
import com.sumin.numbercomposition.domain.entity.GameResult
import com.sumin.numbercomposition.domain.entity.GameSettings
import com.sumin.numbercomposition.domain.entity.Level
import com.sumin.numbercomposition.domain.entity.Question
import com.sumin.numbercomposition.domain.usecases.GenerateQuestionUseCase
import com.sumin.numbercomposition.domain.usecases.GetGameSettingsUseCase

class GameViewModel(private val application: Application, private val level: Level) : ViewModel() {

    lateinit var gameSettings: GameSettings

    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private var timer: CountDownTimer? = null

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private var _percentRightAnswers = MutableLiveData<Int>()
    val percentRightAnswers: LiveData<Int>
        get() = _percentRightAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _isEnoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val isEnoughCountOfRightAnswers: LiveData<Boolean>
        get() = _isEnoughCountOfRightAnswers

    private val _isEnoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val isEnoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _isEnoughPercentOfRightAnswers

    private var _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private var _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private var countOfRightAnswers = 0
    private var questionCount = 0

    init {
        startGame()
    }

    private fun startGame() {
        getGameSettings()
        updateProgress()
        startTimer()
        generateQuestion()
    }

    fun chooseAnswer(option: Int) {
        checkAnswer(option)
        generateQuestion()
        updateProgress()
    }

    private fun updateProgress() {
        _percentRightAnswers.value = calculateSuccessPercent()

        _progressAnswers.value = String.format(
            application.resources.getString(R.string.progress_answers),
            countOfRightAnswers.toString(),
            gameSettings.minCountOfRightAnswers.toString()
        )
        _isEnoughCountOfRightAnswers.value =
            countOfRightAnswers >= gameSettings.minCountOfRightAnswers

        _isEnoughPercentOfRightAnswers.value =
            (_percentRightAnswers.value ?: 0) >= gameSettings.minPercentOfRightAnswers
    }

    private fun calculateSuccessPercent(): Int {
        return if (questionCount == 0) 0
        else (countOfRightAnswers.toDouble() / questionCount * 100).toInt()
    }

    private fun checkAnswer(option: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (option == rightAnswer) countOfRightAnswers++
        questionCount++
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }

    private fun getGameSettings() {
        this.gameSettings = getGameSettingsUseCase(level)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun startTimer() {
        timer = object :
            CountDownTimer(gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS, MILLIS_IN_SECONDS) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            winner = isEnoughCountOfRightAnswers.value == true
                    && isEnoughPercentOfRightAnswers.value == true,
            countOfRightAnswers = countOfRightAnswers,
            countOfQuestions = questionCount,
            gameSettings = gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        const val MILLIS_IN_SECONDS = 1000L
        const val SECONDS_IN_MINUTES = 60
    }
}