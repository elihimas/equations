package com.elihimas.equations.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.elihimas.equations.databinding.FragmentGameBinding
import com.elihimas.equations.model.Equation
import com.elihimas.equations.model.Game
import com.elihimas.equations.model.RemainingTime
import com.elihimas.equations.util.disable
import com.elihimas.equations.util.setOnClickListener
import com.elihimas.equations.util.updateTextAndTag
import com.elihimas.equations.viewmodels.GameState
import com.elihimas.equations.viewmodels.GameViewModel

class GameFragment(private val viewModel: GameViewModel) : Fragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.game.observe(requireActivity(), this::updateGame)
        viewModel.remainingTime.observe(requireActivity(), this::updateRemainingTime)
    }

    private fun updateGame(game: Game) {
        val formattedEquation = formatEquation(game.equation)
        val options = game.shuffledOptions

        with(binding) {
            tvEquation.text = formattedEquation

            btOption1.updateTextAndTag(options[0])
            btOption2.updateTextAndTag(options[1])
            btOption3.updateTextAndTag(options[2])
        }

        if (!game.isRunning) {
            disableButtons()
        }
    }

    private fun disableButtons() = allButtons().forEach(View::disable)

    private fun allButtons() = listOf(binding.btOption1, binding.btOption2, binding.btOption3)

    private fun formatEquation(equation: Equation) =
        with(equation) { "$operand1 $operation $operand2" }

    private fun updateRemainingTime(remainingTime: RemainingTime) {
        binding.timeIndicator.scaleX = remainingTime.proportionalRemainingTime
    }

    private fun initViews() {
        val state = viewModel.state.value
        if (state == GameState.TimeAttack) {
            configureForTimeAttack()
        }

        with(binding) {
            btOption1.setOnClickListener(this@GameFragment::onOptionSelected)
            btOption2.setOnClickListener(this@GameFragment::onOptionSelected)
            btOption3.setOnClickListener(this@GameFragment::onOptionSelected)
            btClose.setOnClickListener(viewModel::onCloseClicked)
        }
    }

    private fun configureForTimeAttack() {
        binding.timeIndicator.visibility = View.VISIBLE
    }

    private fun onOptionSelected(source: View) {
        val answer = source.tag as Int?
        answer?.let {
            viewModel.handleAnswer(it)
        }
    }
}