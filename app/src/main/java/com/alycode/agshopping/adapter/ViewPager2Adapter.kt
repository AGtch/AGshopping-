package com.alycode.agshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.squareup.picasso.Picasso

class ViewPager2Adapter(private val productColorImageList: List<String>) :
    RecyclerView.Adapter<ViewPager2Adapter.ImageViewHolder>() {

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productColorImage: ImageView = view.findViewById(R.id.product_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load(productColorImageList[position])
            .into(holder.productColorImage)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}