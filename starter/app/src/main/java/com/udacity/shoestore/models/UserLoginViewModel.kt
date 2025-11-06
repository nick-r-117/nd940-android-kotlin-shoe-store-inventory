package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class UserLoginViewModel : ViewModel() {

    private var _email: String? = null
    private var _password: String? = null

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn


    init {
        _loginSuccess.value = false
    }

    fun createAccount(email: String?, password: String?) {
        if (validate(email, password)) {
            _email = email
            _password = password

            _loginSuccess.value = true
            _isLoggedIn.value = true

            // TODO: Write to DB
        }
    }

    fun login(email: String?, password: String?) {
        if (verifyCredentials(email, password)) {
            _email = email
            _password = password
            _loginSuccess.value = true
            _isLoggedIn.value = true
        }
    }

    private fun validate(email: String?, password: String?): Boolean {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            return false
        }

        // TODO: Error handling
        return true
    }

    private fun verifyCredentials(email: String?, password: String?): Boolean {
        return validate(email, password)
        // TODO: Read from DB
    }

    fun onLoggedInComplete() {
        _loginSuccess.value = false
    }
}