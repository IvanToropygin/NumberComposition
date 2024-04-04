package com.stepik.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stepik.numbercomposition.R
import com.stepik.numbercomposition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindView()
    }

    private fun bindView() {
        with(binding) {
            emojiResult.setImageResource(getSmileId())

            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                args.result.gameSettings.minCountOfRightAnswers
            )

            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                args.result.gameSettings.minPercentOfRightAnswers
            )

            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                args.result.countOfRightAnswers
            )

            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getPercentOfRightAnswers() = with(args.result) {
        if (countOfQuestions == 0) 0 else countOfRightAnswers * 100 / countOfQuestions
    }

    private fun getSmileId() =
        if (args.result.winner) R.drawable.ic_smile else R.drawable.ic_sad

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}