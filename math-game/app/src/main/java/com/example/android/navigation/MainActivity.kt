package com.example.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    //private lateinit var timer: Timer
    // private val timer: Timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        //Timber.i("onCreate called")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //timer = Timer()

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart() called")
        //timer.startTimer()
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop() called")
        //timer.stopTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart() called")
    }
}
