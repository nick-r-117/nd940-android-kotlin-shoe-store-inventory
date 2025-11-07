package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.models.ShoeInventoryViewModel

class ShoeDetailsFragment : Fragment() {

    private val shoeViewModel: ShoeInventoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ShoeDetailsFragmentBinding>(
            inflater,
            R.layout.shoe_details_fragment,
            container,
            false
        )

        shoeViewModel.shouldClearTextInputFields.observe(this.viewLifecycleOwner, { shouldClear ->
            if (shouldClear) {
                binding.shoeNameFieldEditText.text?.clear()
                binding.shoeCompanyFieldEditText.text?.clear()
                binding.shoeSizeFieldEditText.text?.clear()
                binding.shoeDescriptionFieldEditText.text?.clear()
                shoeViewModel.onTextInputFieldsCleared()
            }
        })

        return binding.root
    }
}