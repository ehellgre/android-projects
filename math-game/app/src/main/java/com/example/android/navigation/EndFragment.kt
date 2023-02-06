package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.navigation.databinding.FragmentEndBinding

class EndFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndBinding>(
            inflater, R.layout.fragment_end, container, false
        )


        // THIS DOESNT WORK
        val score = arguments?.getInt("score", 0)
        binding.endScoreText.text = "$score HALOOOOO"

        // END

        return binding.root
    }
}