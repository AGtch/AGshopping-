package com.alycode.agshopping.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.CarRecyclerViewAdapter.CarViewHolder
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.ui.view.ProductDetailSharedData
import com.squareup.picasso.Picasso

class CarRecyclerViewAdapter : RecyclerView.Adapter<CarViewHolder>(), ProductDetailSharedData {
    private var productsList: MutableList<ProductModel> = mutableListOf()

    private lateinit var handleClickEventOnShoppingCar: HandleClickEventOnShoppingCar

    @SuppressLint("NotifyDataSetChanged")
    fun setProductsToCarList(
        productsListInCar: MutableList<ProductModel>,
        handleClickEventOnShoppingCar: HandleClickEventOnShoppingCar
    ) {
        productsList = productsListInCar
        this.handleClickEventOnShoppingCar = handleClickEventOnShoppingCar
        notifyDataSetChanged()
    }

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageInCarView: ImageView = view.findViewById(R.id.productViewImage_icCarView)
        val increaseNumberOfProductBtn: ImageButton =
            view.findViewById(R.id.increaseNumberOfProduct_inCarView)
        val decreaseNumberOfProductBtn: ImageButton =
            view.findViewById(R.id.decrease_button_inCarView)
        val productNameInCarViewTxtView: TextView = view.findViewById(R.id.product_name_inCarView)
        val productPriceInCarViewTxtView: TextView = view.findViewById(R.id.product_price_inCarView)
        val productCounterToBuyInCarViewTxtView: TextView =
            view.findViewById(R.id.counter_textview_inCarView)
        val deleteProductFromCarBtn: Button =
            view.findViewById(R.id.deleteProductFromCar_Btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val viewLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_incar, parent, false)
        return CarViewHolder(viewLayout)
    }

    override fun onBindViewHolder(viewHolder: CarViewHolder, position: Int) {
        val product = productsList[position]

        Picasso.get().load(product.productImage).into(viewHolder.productImageInCarView)
        viewHolder.productNameInCarViewTxtView.text = product.productName
        viewHolder.productPriceInCarViewTxtView.text = product.productPrice
        viewHolder.productCounterToBuyInCarViewTxtView.text = product.productQuantityToBuy

        viewHolder.increaseNumberOfProductBtn.setOnClickListener {
            handleClickEventOnShoppingCar.clickOnIncreaseProductBtn(productsList[position].productPrice!!)

            viewHolder.productCounterToBuyInCarViewTxtView.text = increaseNumberOfProductsInCar(
                viewHolder.increaseNumberOfProductBtn,
                product.productQuantity!!.toInt(),
                viewHolder.productCounterToBuyInCarViewTxtView.text.toString()
            ).toString()
        }


        viewHolder.decreaseNumberOfProductBtn.setOnClickListener {
            handleClickEventOnShoppingCar.clickOnDecreaseProductBtn(productsList[position].productPrice!!)

            viewHolder.productCounterToBuyInCarViewTxtView.text = decreaseNumberOfProductsInCar(
                viewHolder.increaseNumberOfProductBtn,
                viewHolder.productCounterToBuyInCarViewTxtView.text.toString()
            ).toString()
        }


        viewHolder.deleteProductFromCarBtn.setOnClickListener {
            deleteProductFromShoppingCar(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteProductFromShoppingCar(indexOfProduct: Int) {
        productsList.removeAt(indexOfProduct)
        notifyItemRemoved(indexOfProduct)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    interface HandleClickEventOnShoppingCar {
        fun clickOnIncreaseProductBtn(productPrice: String)
        fun clickOnDecreaseProductBtn(productPrice: String)
    }
}