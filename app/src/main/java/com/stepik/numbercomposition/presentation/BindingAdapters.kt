package com.stepik.numbercomposition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.stepik.numbercomposition.R
import com.stepik.numbercomposition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score), count)
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercent(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage), percentage)
}

@BindingAdapter("score")
fun bindScore(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers), score)
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage), percentOfRightAnswers(gameResult))
}

fun percentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) 0 else {
        countOfRightAnswers * 100 / countOfQuestions
    }
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileId(winner))
}

fun getSmileId(winner: Boolean) = if (winner) R.drawable.ic_smile else R.drawable.ic_sad
