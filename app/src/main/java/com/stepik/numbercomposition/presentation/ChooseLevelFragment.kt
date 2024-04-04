package com.stepik.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stepik.numbercomposition.R
import com.stepik.numbercomposition.databinding.FragmentChooseLevelBinding
import com.stepik.numbercomposition.domain.entity.Level
import com.stepik.numbercomposition.domain.entity.Level.EASY
import com.stepik.numbercomposition.domain.entity.Level.HARD
import com.stepik.numbercomposition.domain.entity.Level.NORMAL
import com.stepik.numbercomposition.domain.entity.Level.TEST


class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseLevel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun chooseLevel() {
        with(binding) {
            btnTestLevel.setOnClickListener {
                launchGameFragment(TEST)
            }
            btnEasyLevel.setOnClickListener {
                launchGameFragment(EASY)
            }
            btnNormalLevel.setOnClickListener {
                launchGameFragment(NORMAL)
            }
            btnHardLevel.setOnClickListener {
                launchGameFragment(HARD)
            }
        }
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    companion object {

        const val NAME = "ChooseLevelFragment"

        fun newInstance() = ChooseLevelFragment()
    }
}