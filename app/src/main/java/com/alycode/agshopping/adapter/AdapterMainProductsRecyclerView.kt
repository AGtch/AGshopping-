package com.alycode.agshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.AdapterMainProductsRecyclerView.ProductViewHolder
import com.alycode.agshopping.data.pojo.ProductModel
import com.squareup.picasso.Picasso

class AdapterMainProductsRecyclerView : RecyclerView.Adapter<ProductViewHolder>() {

    private var productsList: List<ProductModel>? = null
    private lateinit var itemClicked: ItemClicked


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProductViewHolder {
        //initialize viewHolder and its associated the view without fill data
        val viewLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(viewLayout)
    }


    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        var product = productsList?.get(position)
        Picasso.get().load(product!!.productImage).into(viewHolder.productImage)
        viewHolder.productName.text = product.productName
        viewHolder.productPrice.text = product.productPrice.toString()

        viewHolder.itemView.setOnClickListener {
            itemClicked.getItemCLiked(productsList?.get(position)!!)

        }
    }

    override fun getItemCount(): Int {
        if (productsList != null)
            return productsList!!.size
        return 0
    }


    fun setProductData(products: List<ProductModel>, itemClicked: ItemClicked) {
        this.productsList = products
        this.itemClicked = itemClicked
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val productName: TextView
        val productPrice: TextView
        val productImage: ImageView

        init {
            productName = view.findViewById(R.id.product_name)
            productPrice = view.findViewById(R.id.product_price)
            productImage = view.findViewById(R.id.product_image)
        }
    }

    interface ItemClicked {
        fun getItemCLiked(productModel: ProductModel)
    }
}