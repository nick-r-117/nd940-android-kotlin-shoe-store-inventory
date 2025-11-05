package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.models.UserLoginViewModel
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var userLoginViewModel: UserLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        userLoginViewModel = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        userLoginViewModel.loginSuccess.observe(this.viewLifecycleOwner, { loginSuccess ->
            if (loginSuccess) {
                Timber.d("Login successful")
            }
        })

        binding.userLoginViewModel = userLoginViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.loginButton.setOnClickListener { view: View ->
            // Read email & password fields
            val email = binding.emailEditText.text?.toString()
            val password = binding.passwordEditText.text?.toString()

            // TODO: Error handing -> Store user credentials -> Navigate to onboarding screen
            userLoginViewModel.login(email, password)
        }
        binding.createAccountButton.setOnClickListener { view: View ->
            // Read email & password fields
            val email = binding.emailEditText.text?.toString()
            val password = binding.passwordEditText.text?.toString()

            // TODO: Error handing -> Store user credentials -> Navigate to onboarding screen
            userLoginViewModel.createAccount(email, password)
        }

        return binding.root
    }
}