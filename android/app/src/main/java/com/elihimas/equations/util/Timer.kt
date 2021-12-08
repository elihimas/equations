package com.elihimas.equations.util

interface TimerCallback {
    fun tick()
}

interface Timer {
    fun startWithCallback(timerCallback: TimerCallback)
}
