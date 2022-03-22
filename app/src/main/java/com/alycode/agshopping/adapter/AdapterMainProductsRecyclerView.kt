package com.alycode.agshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.ui.view.ProductOnClickListener
import com.squareup.picasso.Picasso

class AdapterMainProductsRecyclerView(private var productsList: MutableList<ProductModel>) :
    RecyclerView.Adapter<AdapterMainProductsRecyclerView.ProductViewHolder>() {


    lateinit var productOncClickListener: ProductOnClickListener

    fun setProductOnClickListener(listener: ProductOnClickListener) {
        productOncClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProductViewHolder {
        //initialize viewHolder and its associated the view without fill data
        val viewLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(viewLayout, productOncClickListener)
    }


    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        Picasso.get().load(productsList[position].productImage).into(viewHolder.productImage)
        viewHolder.productName.text = productsList[position].productName
        viewHolder.productPrice.text = productsList[position].productPrice.toString()
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductViewHolder(view: View, listener: ProductOnClickListener) :
        RecyclerView.ViewHolder(view) {
        val productName: TextView
        val productPrice: TextView
        val productImage: ImageView

        init {
            productName = view.findViewById(R.id.product_name)
            productPrice = view.findViewById(R.id.product_price)
            productImage = view.findViewById(R.id.product_image)
            view.setOnClickListener { listener.productOnClickListener(adapterPosition) }
        }
    }

}