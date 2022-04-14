package com.alycode.agshopping.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.database.ProductFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class ProductRepo(private var productFirebaseDatabase: ProductFirebase = ProductFirebase.instance) {


    val mutableListProduct: MutableList<ProductModel> = mutableListOf()
    val mutableLiveData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()


    fun getAllProductFromFirebaseUsingLiveData(): MutableLiveData<MutableList<ProductModel>> {
        mutableListProduct.clear()

        productFirebaseDatabase.rootReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val product = ProductModel(
                        it.child("productName").value.toString(),
                        it.child("productDescription").value.toString(),
                        it.child("productPrice").value.toString(),
                        it.child("productQuantity").value.toString(),
                        it.child("productImage").value.toString()
                    )
                    mutableListProduct.add(product)
                }
                mutableLiveData.postValue(mutableListProduct)
                Log.d("snapshotDATA", "onDataChange: ${mutableListProduct.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("errorInGetData", "  onCancelled:  ${error.message}")
            }
        })
        return mutableLiveData
    }
}



