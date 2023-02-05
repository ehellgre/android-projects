/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.navigation.databinding.FragmentGameBinding
import com.example.android.navigation.databinding.FragmentTitleBinding

class GameFragment : Fragment() {
    private var score = 0
    private var currentProblem = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false
        )

        generateProblem(binding)

        binding.checkButton.setOnClickListener {
            if (binding.answerText.text.toString().toInt() == binding.number1TextView.text.toString().toInt() +
                binding.number2TextView.text.toString().toInt()
            ) {
                score++
            }

            if (currentProblem < 4) {
                currentProblem++
                generateProblem(binding)
            } else {
                //navigateToEndFragment()
            }
        }

        return binding.root
    }

    private fun generateProblem(binding: FragmentGameBinding) {
        binding.number1TextView.text = (1..10).random().toString()
        binding.number2TextView.text = (1..10).random().toString()
        binding.answerText.text.clear()
    }

}
