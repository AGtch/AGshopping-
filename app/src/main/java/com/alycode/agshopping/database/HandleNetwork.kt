package com.alycode.agshopping.database

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

class HandleNetwork : Application() {

    fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetwork
        Log.d("hasNetworkAvailable", "hasNetworkAvailable: ${(network != null)}")
        return (network != null)
    }
}