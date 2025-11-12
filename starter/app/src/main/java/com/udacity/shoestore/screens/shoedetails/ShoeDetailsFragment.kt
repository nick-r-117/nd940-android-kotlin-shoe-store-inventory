package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.models.ShoeInventoryViewModel
import timber.log.Timber

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
        binding.lifecycleOwner = this
        binding.shoeViewModel = shoeViewModel

        shoeViewModel.onCancelSuccess.observe(this.viewLifecycleOwner, { onCancelSuccess ->
            if (onCancelSuccess) {
                binding.shoeNameFieldEditText.text?.clear()
                binding.shoeCompanyFieldEditText.text?.clear()
                binding.shoeSizeFieldEditText.text?.clear()
                binding.shoeDescriptionFieldEditText.text?.clear()
                shoeViewModel.onTextInputFieldsCleared()
                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeInventoryFragment())
            }
        })

        shoeViewModel.onSaveSuccess.observe(this.viewLifecycleOwner, { success ->
            if (success) {
                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeInventoryFragment())
            }
        })

        return binding.root
    }
}