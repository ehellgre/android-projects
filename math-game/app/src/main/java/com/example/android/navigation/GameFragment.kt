package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding
import com.example.android.navigation.databinding.FragmentTitleBinding
import timber.log.Timber

class GameFragment : Fragment() {

    private lateinit var timer: Timer

    var timerCount = 0
    var score = 0
    private var currentProblem = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false
        )

        timer = Timer()

        if(savedInstanceState != null) {
            timerCount = savedInstanceState.getInt("timer")
            score = savedInstanceState.getInt("score")
            currentProblem = savedInstanceState.getInt("currentProblem")
        }

        // generate problem for the user to solve
        generateProblem(binding)

        binding.checkButton.setOnClickListener {
            if (binding.answerText.text.toString().toInt() == binding.number1TextView.text.toString().toInt() +
                binding.number2TextView.text.toString().toInt()
            ) {
                score++
                binding.scoreText.text ="$score/$currentProblem"
            } else {
                binding.scoreText.text = "$score/$currentProblem"
            }

            if (currentProblem < 4) {
                currentProblem++
                generateProblem(binding)
            } else {
                val bundle = Bundle()
                bundle.putInt("score", score)
                bundle.putInt("timer", timer.secondsCount)
                findNavController().navigate(R.id.action_gameFragment_to_endFragment, bundle)
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


    private fun generateProblem(binding: FragmentGameBinding) {
        binding.number1TextView.text = (1..10).random().toString()
        binding.number2TextView.text = (1..10).random().toString()
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