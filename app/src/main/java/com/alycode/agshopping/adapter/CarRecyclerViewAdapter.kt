package com.alycode.agshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.CarRecyclerViewAdapter.CarViewHolder
import com.alycode.agshopping.data.pojo.ProductModel
import com.squareup.picasso.Picasso

class CarRecyclerViewAdapter : RecyclerView.Adapter<CarViewHolder>() {
    lateinit var productsList: List<ProductModel>
    fun setProductsToCarList(productsListInCar: List<ProductModel>) {
        productsList = productsListInCar
    }

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageInCarView: ImageView
        val increaseNumberOfProductBtn: ImageButton
        val decreaseNumberOfProductBtn: ImageButton
        val productNameInCarViewTxtView: TextView
        val productPriceInCarViewTxtView: TextView
        val productCounterToBuyInCarViewTxtView: TextView

        val deleteProductFromCarAfterDecreaseBtn: ImageButton

        init {
            productImageInCarView = view.findViewById(R.id.productViewImage_icCarView)
            increaseNumberOfProductBtn = view.findViewById(R.id.increaseNumberOfProduct_inCarView)
            decreaseNumberOfProductBtn = view.findViewById(R.id.decrease_button_inCarView)
            productNameInCarViewTxtView = view.findViewById(R.id.product_name_inCarView)
            productPriceInCarViewTxtView = view.findViewById(R.id.product_price_inCarView)
            productCounterToBuyInCarViewTxtView = view.findViewById(R.id.counter_textview_inCarView)
            deleteProductFromCarAfterDecreaseBtn =
                view.findViewById(R.id.delete_after_decrease_button_inCarView)
        }
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

    }

    override fun getItemCount(): Int {
        return this.productsList.size
    }
}