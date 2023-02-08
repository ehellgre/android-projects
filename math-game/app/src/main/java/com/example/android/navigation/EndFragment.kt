package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.android.navigation.databinding.FragmentEndBinding

class EndFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndBinding>(
            inflater, R.layout.fragment_end, container, false
        )

        val score = arguments?.getInt("score", 0)
        binding.endScoreText.text = "$score/4"

        val timer = arguments?.getInt("timer", 0)
        binding.timerText.text = "Total time: $timer seconds"


        // Return to first page
        binding.endButton.setOnClickListener {
            findNavController().navigate(R.id.action_endFragment_to_titleFragment)
        }



        return binding.root
    }
}