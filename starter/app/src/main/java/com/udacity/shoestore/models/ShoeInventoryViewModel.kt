package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeInventoryViewModel : ViewModel() {

    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()

    private val _shouldClearTextInputFields = MutableLiveData<Boolean>()
    val shouldClearTextInputFields: LiveData<Boolean>
        get() = _shouldClearTextInputFields

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
        val shoe = Shoe(
            shoeName.value.toString(),
            shoeSize.value.toString().toDouble(),
            shoeCompany.value.toString(),
            shoeDescription.value.toString())

        _shoes.value = _shoes.value?.plus(shoe)
        _onSaveSuccess.value = true
    }

    fun cancelShoe() {
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""

        _shouldClearTextInputFields.value = true
    }

    fun onTextInputFieldsCleared() {
        _shouldClearTextInputFields.value = false
    }
}