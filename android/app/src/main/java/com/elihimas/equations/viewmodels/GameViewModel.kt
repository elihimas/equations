package com.elihimas.equations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elihimas.equations.business.GameCreator
import com.elihimas.equations.model.Game
import com.elihimas.equations.model.RemainingTime
import com.elihimas.equations.util.GameTimer
import com.elihimas.equations.util.TimerCallback

class GameViewModel(private val gameTimer: GameTimer) : ViewModel() {

    private val mutableGameState = MutableLiveData<GameState>()
    private val mutableGame = MutableLiveData<Game>()
    private val mutableRemainingTime = MutableLiveData<RemainingTime>()

    private var currentGame: Game = GameCreator.createGame()

    val state: LiveData<GameState> = mutableGameState
    val game: LiveData<Game> = mutableGame
    val remainingTime: LiveData<RemainingTime> = mutableRemainingTime

    init {
        mutableGameState.value = GameState.Menu
        mutableGame.value = currentGame
    }

    fun handleAnswer(answer: Int) {
        if (currentGame.correctAnswer == answer) {
            resetGame()
        }
    }

    fun onCloseClicked() = mutableGameState.postValue(GameState.Menu)

    private fun resetGame() {
        currentGame = GameCreator.createGame()
        mutableGame.postValue(currentGame)
    }

    fun onNormalModeClicked() {
        resetGame()
        mutableGameState.postValue(GameState.Game)
    }

    fun onTimeAttackModeClicked() {
        resetGame()
        mutableGameState.postValue(GameState.TimeAttack)
        gameTimer.startWithCallback(totalTime, object : TimerCallback {
            override fun tick(remainingTime: Int) {
                updateTime(remainingTime)
            }
        })
    }

    private fun updateTime(totalRemainingTime: Int) {
        val proportionalTime = totalRemainingTime.toFloat() / totalTime
        val remainingTime = RemainingTime(totalRemainingTime, proportionalTime)
        mutableRemainingTime.postValue(remainingTime)

        if (totalRemainingTime <= 0) {
            gameTimer.stop()
            currentGame.isRunning = false
            mutableGame.postValue(currentGame)
        }
    }

    private companion object {
        const val totalTime = 10
    }

}