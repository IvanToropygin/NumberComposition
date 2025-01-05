package com.sumin.numbercomposition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswerCount")
fun bindRequiredAnswerCount(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count.toString()
    )
}

@BindingAdapter("rightAnswerCount")
fun bindRightAnswerCount(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count.toString()
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredRecent(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent.toString()
    )
}

@BindingAdapter("percentScore")
fun bindPercentScore(textView: TextView, gameResult: GameResult) {
    val result = if (gameResult.countOfQuestions == 0) 0
    else ((gameResult.countOfRightAnswers.toDouble() / gameResult.countOfQuestions) * 100).toInt()

    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        result.toString()
    )
}

@BindingAdapter("resultIcon")
fun bindResultIcon(imageView: ImageView, winner: Boolean) {
    val reId = if (winner) R.drawable.ic_smile else R.drawable.ic_sad
    imageView.setImageResource(reId)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorGreenOrRed(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    progressBar.progressTintList =
        ColorStateList.valueOf(getColorGreenOrRed(progressBar.context, enough))
}

private fun getColorGreenOrRed(context: Context, enough: Boolean): Int {
    val colorResId =
        if (enough) android.R.color.holo_green_light else android.R.color.holo_red_light
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberToString")
fun bindNumberToString(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClick")
fun bindOnOptionClick(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}
