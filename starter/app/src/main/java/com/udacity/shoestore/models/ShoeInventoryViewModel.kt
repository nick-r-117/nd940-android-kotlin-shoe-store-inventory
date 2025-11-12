package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeInventoryViewModel : ViewModel() {

    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()

    private val _onCloseDetails = MutableLiveData<Boolean>()
    val onCloseDetails: LiveData<Boolean>
        get() = _onCloseDetails

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf()
    }

    fun saveShoe() {
        if (validateInput()) {
            val shoe = Shoe(
                shoeName.value.toString(),
                shoeSize.value.toString().toDouble(),
                shoeCompany.value.toString(),
                shoeDescription.value.toString()
            )

            _shoes.value = _shoes.value?.plus(shoe)
            _onCloseDetails.value = true
        } else {
            // TODO: Handle invalid input i.e. display error message (out of scope of project)
        }
    }

    private fun validateInput(): Boolean {
        return shoeName.value?.isNotEmpty() == true &&
                shoeCompany.value?.isNotEmpty() == true &&
                shoeSize.value?.isNotEmpty() == true &&
                shoeDescription.value?.isNotEmpty() == true
    }

    fun cancelShoe() {
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
        _onCloseDetails.value = true
    }

    fun onCloseDetails() {
        _onCloseDetails.value = false
    }

    fun logout() {
        clearAllData()
    }

    fun clearAllData() {
        _shoes.value = mutableListOf()
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
        _onCloseDetails.value = false
    }
}