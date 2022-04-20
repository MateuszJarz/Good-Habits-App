package com.example.goodhabitsapp.view_models

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

@HiltViewModel
class Timer : ViewModel() {
    private var timer: CountDownTimer? = null
    private var decreaseSecond: Int = 30

    private val _second = MutableStateFlow(0)
    val second: StateFlow<Int> = _second

    fun decreaseCountDown(totalSec: Long) {
        val totalTime = TimeUnit.MINUTES.toMinutes(totalSec) % 60
        timer = object : CountDownTimer(totalSec, 1000) {
            override fun onTick(milliSec: Long) {
                _second.value = decreaseSecond
                decreaseSecond--
            }

            override fun onFinish() {
                Log.d("TAG", "Decrease Time finished")
            }
        }

        timer?.start()
    }
}