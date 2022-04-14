package com.alycode.agshopping.data.repository

import com.google.firebase.database.DatabaseReference

interface GetFireBaseReferences {
    fun getColorsReference(subChild: String): DatabaseReference
}