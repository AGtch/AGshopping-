package com.alycode.agshopping.data.repository

import androidx.lifecycle.MutableLiveData
import com.alycode.agshopping.data.pojo.ProductModel

class ProductDetailsRepo {
    private var productDetailsMutableLiveData: MutableLiveData<ProductModel>? = null

    init {
        productDetailsMutableLiveData = MutableLiveData()
    }

    fun setProductDetails(productModel: ProductModel) {
        if (productDetailsMutableLiveData == null) {
            productDetailsMutableLiveData!!.value = productModel
        } else {
            productDetailsMutableLiveData!!.value = productModel
        }
    }

    fun getProductClickedInRecyclerView(): MutableLiveData<ProductModel> {
        return productDetailsMutableLiveData!!
    }


}