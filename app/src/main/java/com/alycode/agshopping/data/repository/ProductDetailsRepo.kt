package com.alycode.agshopping.data.repository

import androidx.lifecycle.MutableLiveData
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.database.ProductFirebase

class ProductDetailsRepo(private var productFirebaseDatabase: ProductFirebase = ProductFirebase.instance) {
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

    fun getProductColorsImage(productModel: ProductModel) {
        var string: String = productModel.productName!!.replace(" ", "")
            .replaceFirstChar { productModel.productName!![0].lowercase() }

        var childColors = ProductFirebase.rootReference.child(
            string
        ).child("colors")


    }
}