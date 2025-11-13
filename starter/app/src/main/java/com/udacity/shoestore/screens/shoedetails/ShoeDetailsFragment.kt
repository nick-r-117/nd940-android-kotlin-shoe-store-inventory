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

class ShoeDetailsFragment : Fragment() {

    private val shoeViewModel: ShoeInventoryViewModel by activityViewModels()
    private lateinit var _binding: ShoeDetailsFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_details_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.shoeViewModel = shoeViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoeViewModel.onCloseDetails.observe(this.viewLifecycleOwner, { onFinished ->
            if (onFinished) {
                binding.shoeNameFieldEditText.text?.clear()
                binding.shoeCompanyFieldEditText.text?.clear()
                binding.shoeSizeFieldEditText.text?.clear()
                binding.shoeDescriptionFieldEditText.text?.clear()
                findNavController().popBackStack()
                shoeViewModel.onCloseDetails()
            }
        })
    }
}