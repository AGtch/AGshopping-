package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.alycode.agshopping.databinding.FragmentProductDetailsBinding
import com.alycode.agshopping.ui.viewModel.SharedViewModel


class ProductDetails : Fragment() {

    private lateinit var productDetailsBinding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        productDetailsBinding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return productDetailsBinding.root
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.clickedProductDetails.observe(viewLifecycleOwner, Observer {
            Log.d("sharedViewModel", "onViewCreated: sharedViewModel ${it.productName}")
            productDetailsBinding.txtViewProductName.text = it.productName
            productDetailsBinding.productDescriptionTxtView.text = it.productDescription
            productDetailsBinding.productPriceTxtView.text = it.productPrice
        })
    }
}
//  val pos = arguments?.getInt("positionClicked")
//   Log.d("clickedProduct", "onViewCreated: $pos")
//   viewModel.getProductDetailsFirebaseUsingLiveData(pos!!)
//    .observe(viewLifecycleOwner, Observer {
//            Log.d("clickedProduct", "onViewCreated: ${it.productName}")

//       })
//   productDetailsBinding.txtViewProductName.text = product.toString()


