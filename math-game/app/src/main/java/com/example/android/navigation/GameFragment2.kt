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
import androidx.navigation.fragment.findNavController
import com.example.android.navigation.databinding.FragmentGame2Binding
import com.example.android.navigation.databinding.FragmentTitleBinding
import timber.log.Timber

class GameFragment2 : Fragment() {

    private lateinit var timer: Timer

    var timerCount = 0
    var score = 0
    private var currentProblem = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGame2Binding>(
            inflater, R.layout.fragment_game2, container, false
        )

        timer = Timer()

        if(savedInstanceState != null) {
            timerCount = savedInstanceState.getInt("timer")
            score = savedInstanceState.getInt("score")
            currentProblem = savedInstanceState.getInt("currentProblem")
        }

        generateProblem(binding)

        binding.checkButton.setOnClickListener {
            if (binding.answerText.text.toString().toInt() == binding.numberMulti1TextView.text.toString().toInt() *
                binding.numberMulti2TextView.text.toString().toInt()
            ) {
                score++
                binding.score2Text.text ="$score/$currentProblem"
            } else {
                binding.score2Text.text = "$score/$currentProblem"
            }

            if (currentProblem < 4) {
                currentProblem++
                generateProblem(binding)
            } else {
                val bundle = Bundle()
                bundle.putInt("score", score)
                bundle.putInt("timer", timer.secondsCount)
                findNavController().navigate(R.id.action_gameFragment2_to_endFragment, bundle)
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("timer", timer.secondsCount)
        outState.putInt("score", score)
        outState.putInt("currentProblem", currentProblem)
    }

    private fun generateProblem(binding: FragmentGame2Binding) {
        binding.numberMulti1TextView.text = (1..10).random().toString()
        binding.numberMulti2TextView.text = (1..10).random().toString()
        binding.answerText.text.clear()
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart() called")
        timer.startTimer()
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop() called")
        timer.stopTimer()
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause() called")
        timer.stopTimer()
    }

}