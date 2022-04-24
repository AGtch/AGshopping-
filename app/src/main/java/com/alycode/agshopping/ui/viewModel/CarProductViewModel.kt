package com.alycode.agshopping.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alycode.agshopping.data.pojo.ProductModel

class CarProductViewModel :
    ViewModel() {

    private val productList: MutableList<ProductModel> = mutableListOf()
    private val productMutableLiveData: MutableLiveData<MutableList<ProductModel>> =
        MutableLiveData()

    private val totalPriceMutableLiveData: MutableLiveData<String> =
        MutableLiveData()

    fun getAllProductInCarUsingLiveData(): LiveData<MutableList<ProductModel>> {
        Log.d("sizeinlist", "LiveData-addProductToCar: ${productMutableLiveData.value?.size}")
        Log.d("sizeinlist", "List-addProductToCar: ${productList.size}")

        return productMutableLiveData
    }

    fun addProductToCar(productModel: ProductModel) {
        productList.add(productModel)
        totalPriceMutableLiveData.postValue(productModel.productPrice!!)
        productMutableLiveData.value = productList
    }

    fun addPriceToTotalAmount(price: String) {

        val result = totalPriceMutableLiveData.value!!.toInt() + price.toInt()
        totalPriceMutableLiveData.postValue(
            result.toString()
        )
    }

    fun removePriceFromTotalAmount(price: String) {
        Log.d("removeIt", "removePriceFromTotalAmount: ${totalPriceMutableLiveData.value}")
        val result = totalPriceMutableLiveData.value!!.toInt().minus(price.toInt())
        if (result >0)
            totalPriceMutableLiveData.postValue(
                result.toString()
            )
    }

    fun getNewTotalPrice(): LiveData<String> {
        return totalPriceMutableLiveData
    }
}