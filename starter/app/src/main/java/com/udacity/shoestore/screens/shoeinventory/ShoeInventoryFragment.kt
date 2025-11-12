package com.udacity.shoestore.screens.shoeinventory

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeInventoryFragmentBinding
import com.udacity.shoestore.models.ShoeInventoryViewModel
import com.udacity.shoestore.models.Shoe

class ShoeInventoryFragment : Fragment() {

    private val shoeViewModel: ShoeInventoryViewModel by activityViewModels()

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
        binding.lifecycleOwner = this

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeInventoryFragmentDirections.actionShoeInventoryFragmentToShoeDetailsFragment())
        }

        shoeViewModel.shoes.observe(this.viewLifecycleOwner, { shoes ->
            val layoutParams = binding.shoeList.layoutParams as FrameLayout.LayoutParams
            if (shoes.isNullOrEmpty()) {
                layoutParams.gravity = Gravity.CENTER
                binding.nullStateText.visibility = View.VISIBLE
            } else {
                val layoutParams = binding.shoeList.layoutParams as FrameLayout.LayoutParams
                layoutParams.gravity = Gravity.START
                binding.nullStateText.visibility = View.GONE
                updateShoeInventory(binding.shoeList, shoes)
            }
            binding.shoeList.layoutParams = layoutParams
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost = requireActivity()
        menuHost.addMenuProvider( object : MenuProvider {
            override fun onCreateMenu(
                menu: Menu,
                menuInflater: MenuInflater
            ) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout_menu_option -> {
                        findNavController().navigate(ShoeInventoryFragmentDirections.actionShoeInventoryFragmentToLoginFragment())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun updateShoeInventory(shoeContainer: ViewGroup, shoes: List<Shoe>) {
        val resources = shoeContainer.resources

        shoeContainer.removeAllViews()
        for (shoe in shoes) {
            val shoeInfoText =
                resources.getString(
                    R.string.shoe_inventory_item,
                    shoe.name,
                    shoe.size.toString(),
                    shoe.company,
                    shoe.description
                )

            val view = TextView(shoeContainer.context)
            val margin =
                resources.getDimension(R.dimen.shoe_inventory_screen_vertical_margin)
                    .toInt()
            val layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(margin, margin, margin, margin)
                }
            view.setTextSize(
                android.util.TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.shoe_inventory_body_text_size))
            view.text = shoeInfoText
            view.layoutParams = layoutParams

            shoeContainer.addView(view)
        }
    }
}
