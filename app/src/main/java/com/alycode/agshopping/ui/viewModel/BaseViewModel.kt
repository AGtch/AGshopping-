package com.alycode.agshopping.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.data.repository.ProductRepo

open class BaseViewModel(private val repository: ProductRepo = ProductRepo()) : ViewModel() {

    fun getResponseUsingLiveData(): LiveData<MutableList<ProductModel>> {
        return repository.getResponseFromRealtimeDatabaseUsingLiveData()
    }
}