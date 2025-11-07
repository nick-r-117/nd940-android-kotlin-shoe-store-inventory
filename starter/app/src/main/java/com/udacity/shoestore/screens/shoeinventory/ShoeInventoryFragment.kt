package com.udacity.shoestore.screens.shoeinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeInventoryFragmentBinding

class ShoeInventoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ShoeInventoryFragmentBinding>(
            inflater,
            R.layout.shoe_inventory_fragment,
            container,
            false
        )

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeInventoryFragmentDirections.actionShoeInventoryFragmentToShoeDetailsFragment())
        }

        return binding.root
    }
}