package com.sumin.numbercomposition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.domain.entity.GameResult

@BindingAdapter("requiredAnswerCount")
fun bindRequiredAnswerCount(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
                count.toString())
}

@BindingAdapter("rightAnswerCount")
fun bindRightAnswerCount(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count.toString())
}

@BindingAdapter("requiredPercent")
fun bindRequiredRecent(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent.toString())
}

@BindingAdapter("percentScore")
fun bindPercentScore(textView: TextView, gameResult: GameResult) {
    val result = if (gameResult.countOfQuestions == 0) 0
    else ((gameResult.countOfRightAnswers.toDouble() / gameResult.countOfQuestions) * 100).toInt()

    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        result.toString())
}

@BindingAdapter("getResultIcon")
fun getResultIcon(imageView: ImageView, winner: Boolean) {
    val reId = if (winner) R.drawable.ic_smile else R.drawable.ic_sad
    imageView.setImageResource(reId)
}
