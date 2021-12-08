package com.elihimas.equations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elihimas.equations.business.GameCreator
import com.elihimas.equations.model.Game
import com.elihimas.equations.util.Timer

class GameViewModel(val timer: Timer) : ViewModel() {

    private val mutableGameState = MutableLiveData<GameState>()
    private val mutableGame = MutableLiveData<Game>()
    private var currentGame: Game = GameCreator.createGame()

    val state: LiveData<GameState> = mutableGameState
    val game: LiveData<Game> = mutableGame

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

    fun onNormalModeClicked() = mutableGameState.postValue(GameState.Game)

    fun onTimeAttackModeClicked() = mutableGameState.postValue(GameState.TimeAttack)

}