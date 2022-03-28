package com.alycode.agshopping.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alycode.agshopping.data.pojo.ProductModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProductRepo(
    private val firebaseDatabase: FirebaseDatabase? = Firebase.database,
    private val reference: DatabaseReference? = firebaseDatabase?.reference?.child("products")
) {
    lateinit var mutableLiveData: MutableLiveData<MutableList<ProductModel>>
    lateinit var product: ProductModel
    var mutableListProduct: MutableList<ProductModel> = mutableListOf()


    fun getAllProductFromFirebaseUsingLiveData(): MutableLiveData<MutableList<ProductModel>> {

        mutableLiveData = MutableLiveData<MutableList<ProductModel>>()
        mutableListProduct.clear()
        reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    product = ProductModel(
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



