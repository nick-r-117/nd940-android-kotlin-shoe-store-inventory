package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeInventoryViewModel : ViewModel() {

    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()

    private val _onCancelSuccess = MutableLiveData<Boolean>()
    val onCancelSuccess: LiveData<Boolean>
        get() = _onCancelSuccess

    private val _onSaveSuccess = MutableLiveData<Boolean>()
    val onSaveSuccess: LiveData<Boolean>
        get() = _onSaveSuccess

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
            _onSaveSuccess.value = true
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
        _onCancelSuccess.value = true
    }

    fun onTextInputFieldsCleared() {
        _onCancelSuccess.value = false
    }
}