package com.example.goodhabitsapp.view_models

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.goodhabitsapp.util.Utility
import com.example.goodhabitsapp.util.Utility.formatTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//@HiltViewModel
class TimerViewModel : ViewModel() {

    //region Properties
    private var countDownTimer: CountDownTimer? = null
    //endregion

    //region States
    private val _time = MutableStateFlow(Utility.TIME_COUNTDOWN.formatTime())
    val time: StateFlow<String> = _time

    private val _progress = MutableStateFlow(1.00F)
    val progress: StateFlow<Float> = _progress

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying


    var progressValue = 0F

    //region Public methods
    fun handleCountDownTimer() {
        if (isPlaying.value == true) {
            pauseTimer()

        } else {
            startTimer()
        }
    }
    //endregion

    //region Private methods
    private fun pauseTimer() {
        countDownTimer?.cancel()

        //handleTimerValues(false, _time.value, progress = progress.value)
        handleTimerValues(false, Utility.TIME_COUNTDOWN.formatTime(), progress = 1.0f)


    }

    private fun startTimer() {

        _isPlaying.value = true
        countDownTimer = object : CountDownTimer(Utility.TIME_COUNTDOWN, 1000) {

            override fun onTick(millisRemaining: Long) {

                progressValue = millisRemaining.toFloat() / Utility.TIME_COUNTDOWN

                handleTimerValues(true, millisRemaining.formatTime(), progressValue)

            }

            override fun onFinish() {
                pauseTimer()

            }
        }.start()
    }

    private fun handleTimerValues(
        isPlaying: Boolean,
        time: String,
        progress: Float,
    ) {
        _isPlaying.value = isPlaying
        _time.value = time
        _progress.value = progress
    }
}
//endregion