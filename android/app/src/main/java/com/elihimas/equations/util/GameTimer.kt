package com.elihimas.equations.util

import java.util.*

interface TimerCallback {
    fun tick(remainingTime: Int)
}

interface GameTimer {
    fun startWithCallback(totalTime: Int, timerCallback: TimerCallback)
    fun stop()
}

class GameTimerImpl : GameTimer {

    private lateinit var timer: Timer

    override fun startWithCallback(totalTime: Int, timerCallback: TimerCallback) {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            var remainingTime = totalTime
            override fun run() {
                timerCallback.tick(remainingTime)
                remainingTime--
            }

        }, 0, 1000)
    }

    override fun stop() {
        timer.cancel()
    }

}
