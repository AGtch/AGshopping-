package com.alycode.agshopping.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alycode.agshopping.data.pojo.ProductModel

class CarProductViewModel :
    ViewModel() {

    private val mutableListProduct: MutableList<ProductModel> = mutableListOf()
    private val mutableLiveData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()


    fun getAllProductInCarUsingLiveData(): LiveData<MutableList<ProductModel>> {
        Log.d("sizeinlist", "LiveData-addProductToCar: ${mutableLiveData.value?.size}")
        Log.d("sizeinlist", "List-addProductToCar: ${mutableListProduct.size}")

        return mutableLiveData
    }

    fun addProductToCar(productModel: ProductModel) {
        mutableListProduct.add(productModel)
        mutableLiveData.value = mutableListProduct
    }
}