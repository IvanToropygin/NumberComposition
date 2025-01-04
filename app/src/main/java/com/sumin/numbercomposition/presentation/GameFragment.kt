package com.sumin.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.databinding.FragmentGameBinding
import com.sumin.numbercomposition.domain.entity.GameResult
import com.sumin.numbercomposition.domain.entity.GameSettings
import com.sumin.numbercomposition.domain.entity.Level

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding!!

    private lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener { lunchGameFinishedFragment(
            GameResult(
                winner = true,
                countOfRightAnswers = 0,
                countOfQuestions = 0,
                gameSettings = GameSettings(
                    maxSumValue = 0,
                    minCountOfRightAnswers = 0,
                    minPercentOfRightAnswers = 0,
                    gameTimeInSeconds = 0
                )
            )
        ) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(LEVEL_KEY)?.let{
            level = it
        }
    }

    private fun lunchGameFinishedFragment(gameResult: GameResult) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(NAME_FOR_NAVIGATION)
            .commit()
    }

    companion object {

        private const val LEVEL_KEY = "level_key"

        const val NAME_FOR_NAVIGATION = "game_fragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LEVEL_KEY, level)
                }
            }
        }
    }
}
