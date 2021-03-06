package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoesListViewModel(): ViewModel() {

    private var _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList : LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        Timber.i("ShoeViewModel created!")

        //Just a test. If you uncomment the next line, be sure to comment the last one in this block.
        //addDummyItems()

        _shoesList.value = mutableListOf()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoesListViewmodel destroyed!")
    }

    fun addNewPairOfShoes(shoeName: String, shoeSize: String, companyName: String, description: String){
        val newShoe = Shoe(
                shoeName,
                shoeSize.toDouble(),
                companyName,
                description
        )
        _shoesList.value?.add(newShoe)
    }

    fun logOut(){
        _shoesList.value = mutableListOf()
    }

    private fun addDummyItems(){
        val dummyList : MutableList<Shoe> = mutableListOf()
        val shoe1 = Shoe(
            "primo",
            2.0,
            "Nike",
            "test"
        )

        val shoe2 = Shoe(
            "secondo",
            2.0,
            "Nike",
            "test"
        )

        val shoe3 = Shoe(
            "terzo",
            2.0,
            "Adidas",
            "test"
        )

        dummyList.add(shoe1)
        dummyList.add(shoe1)
        dummyList.add(shoe1)

        _shoesList.value = dummyList
    }
}