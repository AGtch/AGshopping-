package com.alycode.agshopping.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alycode.agshopping.data.pojo.ProductModel


class SharedViewModel() :
    ViewModel() {

    private val _clickedProductDetails = MutableLiveData<ProductModel>()
    val clickedProductDetails: LiveData<ProductModel> by lazy { _clickedProductDetails }

    fun setProductDetails(productModel: ProductModel) {
        _clickedProductDetails.value = productModel
    }
}