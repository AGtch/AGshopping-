package com.alycode.agshopping.data.repository

import com.alycode.agshopping.data.pojo.ProductModel

data class Response(
    var products: List<ProductModel>? = null,
    var exception: Exception? = null
)
