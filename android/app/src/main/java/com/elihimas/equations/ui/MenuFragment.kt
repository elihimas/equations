package com.elihimas.equations.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elihimas.equations.databinding.FragmentMenuBinding
import com.elihimas.equations.viewmodels.GameViewModel
import com.elihimas.equations.util.setOnClickListener

class MenuFragment(private val viewModel: GameViewModel) : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding) {
            btNormalMode.setOnClickListener(viewModel::onNormalModeClicked)
            btTimeAttack.setOnClickListener(viewModel::onTimeAttackModeClicked)
        }
    }
}
