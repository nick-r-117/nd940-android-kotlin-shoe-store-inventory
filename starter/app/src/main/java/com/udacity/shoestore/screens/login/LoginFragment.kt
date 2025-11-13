package com.udacity.shoestore.screens.login

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.models.NuxConstants
import com.udacity.shoestore.models.UserLoginViewModel
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var userLoginViewModel: UserLoginViewModel
    private lateinit var _binding: LoginFragmentBinding
    private val binding
        get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        userLoginViewModel = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        binding.userLoginViewModel = userLoginViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userLoginViewModel.loginSuccess.observe(this.viewLifecycleOwner, { loginSuccess ->
            if (loginSuccess) {
                onLoginSuccess()
            }
        })
        userLoginViewModel.accountCreatedSuccess.observe(this.viewLifecycleOwner, { accountCreatedSuccess ->
            if (accountCreatedSuccess) {
                val prefs = requireActivity().getSharedPreferences(NuxConstants.SHOE_STORE_NUX_PREFS, MODE_PRIVATE)
                prefs.edit { putBoolean(NuxConstants.HAS_USER_ONBOARDED, false) }
                onLoginSuccess()
            }
        })
    }

    fun onLoginSuccess() {
        val prefs = requireActivity().getSharedPreferences(NuxConstants.SHOE_STORE_NUX_PREFS, MODE_PRIVATE)
        val hasUserOnboarded = prefs.getBoolean(NuxConstants.HAS_USER_ONBOARDED, false)
        if (hasUserOnboarded) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShoeInventoryFragment())
        } else {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        userLoginViewModel.onLoggedInComplete()
        Timber.d("Login successful")
    }
}