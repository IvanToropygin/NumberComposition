package com.stepik.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stepik.numbercomposition.R
import com.stepik.numbercomposition.databinding.FragmentGameBinding
import com.stepik.numbercomposition.domain.entity.GameResult
import com.stepik.numbercomposition.domain.entity.GameSettings
import com.stepik.numbercomposition.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener {
            launchFinishedFragment(
                GameResult(
                winner = false,
                countOfRightAnswers = 10,
                countOfQuestions = 5,
                gameSettings = GameSettings(0,0,0, 0)
            )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable(KEY_LEVEL, Level::class.java)?.let {
            level = it
        }
    }

    private fun launchFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"

        private const val KEY_LEVEL = "level"
        fun newInstance(level: Level) = GameFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
    }
}