package com.alycode.agshopping.ui.view

import android.util.Log
import android.view.View
import android.widget.Toast

interface ProductDetailSharedData {

    fun increaseNumberOfProductsInCar(
        view: View?,
        MaxQuantity: Int, numberInCar: String = "1"
    ): Int {
        var resultOfNumber: Int = numberInCar.toInt()
        if (resultOfNumber < MaxQuantity) {
            resultOfNumber += 1
        } else {
            if (view != null) {
                Toast.makeText(
                    view.context,
                    "sorry!!,You can't buy more than",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        return resultOfNumber

    }

    fun decreaseNumberOfProductsInCar(
        view: View?,
        numberInCar: String = "1",
    ): Int {
        var resultOfNumber: Int = numberInCar.toInt()
        if (view != null) {

            if (resultOfNumber > 1) {
                resultOfNumber -= 1
                Log.d("decrease", "decreaseNumberOfProductsInCar: $resultOfNumber")
            } else {
                Toast.makeText(
                    view.context,
                    "sorry!!,You can't buy less than 1",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return resultOfNumber
    }


    fun showTheShoppingCarIsEmpty(emptyCarHintView: View, productInCarFragmentRecyclerView: View) {

        emptyCarHintView.visibility = View.VISIBLE
        productInCarFragmentRecyclerView.visibility = View.INVISIBLE
    }

    fun showTheProductsInCar(emptyCarHintView: View, productInCarFragmentRecyclerView: View) {
        emptyCarHintView.visibility = View.INVISIBLE
        productInCarFragmentRecyclerView.visibility = View.VISIBLE
    }
}