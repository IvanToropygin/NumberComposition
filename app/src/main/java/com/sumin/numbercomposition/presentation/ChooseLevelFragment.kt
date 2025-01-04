package com.sumin.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.databinding.FragmentChooseLevelBinding
import com.sumin.numbercomposition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            buttonLevelTest.setOnClickListener { launchGameFragment(Level.TEST) }
            buttonLevelEasy.setOnClickListener { launchGameFragment(Level.EASY) }
            buttonLevelNormal.setOnClickListener { launchGameFragment(Level.NORMAL) }
            buttonLevelHard.setOnClickListener { launchGameFragment(Level.HARD) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFragment(level: Level) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = ChooseLevelFragment()
    }
}
