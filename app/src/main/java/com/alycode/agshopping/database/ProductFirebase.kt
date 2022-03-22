package com.alycode.agshopping.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ProductFirebase {
        var firebaseDatabase: FirebaseDatabase? = Firebase.database
    var refrence: DatabaseReference? = firebaseDatabase?.getReference("products")
    companion object FirebaseInstance {
        private val firebaseDatabase: FirebaseDatabase? by lazy {
            Firebase.database
        }
        val reference: DatabaseReference? by lazy {
            firebaseDatabase?.getReference("products")
        }
    }
}