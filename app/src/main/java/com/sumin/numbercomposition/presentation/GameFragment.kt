package com.sumin.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.databinding.FragmentGameBinding
import com.sumin.numbercomposition.domain.entity.GameResult
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
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        level = requireArguments().getSerializable(LEVEL_KEY) as Level
    }

    private fun lunchGameFinishedFragment(gameResult: GameResult) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        private const val LEVEL_KEY = "level_key"

        fun newInstance(level : Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                   putSerializable(LEVEL_KEY, level)
                }
            }
        }
    }
}
