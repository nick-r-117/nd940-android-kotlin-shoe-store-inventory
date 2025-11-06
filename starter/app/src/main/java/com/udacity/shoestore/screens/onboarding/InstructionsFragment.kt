package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<InstructionsFragmentBinding>(
            inflater,
            R.layout.instructions_fragment,
            container,
            false
        )

        binding.nextButton.setOnClickListener { view: View ->
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragment3ToShoeInventoryFragment())
        }

        return binding.root
    }
}