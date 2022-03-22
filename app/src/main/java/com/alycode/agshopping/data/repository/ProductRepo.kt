package com.alycode.agshopping.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alycode.agshopping.data.pojo.ProductModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProductRepo(
    private val firebaseDatabase: FirebaseDatabase? = Firebase.database,
    private val reference: DatabaseReference? = firebaseDatabase?.getReference("products")
) {
    fun getResponseFromRealtimeDatabaseUsingLiveData(): MutableLiveData<MutableList<ProductModel>> {
        val mutableLiveData = MutableLiveData<MutableList<ProductModel>>()
        val mutableListProduct: MutableList<ProductModel> = mutableListOf()
        reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val product = ProductModel(
                        it.child("name").value.toString(),
                        it.child("descrption").value.toString(),
                        it.child("price").value.toString(),
                        it.child("Quantity").value.toString(),
                        it.child("image").value.toString()
                    )
                    mutableListProduct.add(product)
                }
                mutableLiveData.postValue(mutableListProduct)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("errofirev", "  onCancelled:  ${error.message}")
            }
        })
        return mutableLiveData
    }
}



