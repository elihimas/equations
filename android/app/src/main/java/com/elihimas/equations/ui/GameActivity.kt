package com.elihimas.equations.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.elihimas.equations.R
import com.elihimas.equations.databinding.ActivityMainBinding
import com.elihimas.equations.viewmodels.GameState
import com.elihimas.equations.viewmodels.GameViewModel
import org.koin.android.ext.android.inject

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GameViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.state.observe(this, this::updateState)
    }

    private fun updateState(gameState: GameState) {
        val nextFragment = stateToFragment(gameState)
        updateFragment(nextFragment)
    }

    private fun stateToFragment(gameState: GameState) = when (gameState) {
        GameState.Menu -> MenuFragment(viewModel)
        GameState.Game, GameState.TimeAttack -> GameFragment(viewModel)
    }

    private fun updateFragment(nextFragment: Fragment) {
        supportFragmentManager
            .beginTransaction().replace(R.id.fragmentsContainer, nextFragment)
            .commit()
    }

}
