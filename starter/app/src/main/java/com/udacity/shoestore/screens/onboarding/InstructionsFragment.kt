package com.udacity.shoestore.screens.onboarding

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import com.udacity.shoestore.models.NuxConstants
import androidx.core.content.edit

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
            val prefs = requireActivity().getSharedPreferences(NuxConstants.SHOE_STORE_NUX_PREFS, MODE_PRIVATE)
            prefs.edit { putBoolean(NuxConstants.HAS_USER_ONBOARDED, true) }
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragment3ToShoeInventoryFragment())
        }

        return binding.root
    }
}