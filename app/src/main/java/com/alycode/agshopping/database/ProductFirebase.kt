package com.alycode.agshopping.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object ProductFirebase {
    var instance: ProductFirebase = ProductFirebase
    private val firebase: FirebaseDatabase = Firebase.database
    val rootReference: DatabaseReference = firebase.getReference("products")

}