package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.ViewPager2Adapter
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.databinding.FragmentProductDetailsBinding
import com.alycode.agshopping.ui.viewModel.CarProductViewModel
import com.alycode.agshopping.ui.viewModel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.properties.Delegates


class ProductDetails : Fragment(), View.OnClickListener {
    private lateinit var productDetailsBinding: FragmentProductDetailsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var colorsValues: List<String> = mutableListOf()
    private var colorsNames: List<String> = mutableListOf()
    private var mapColors: Map<String, String> = mutableMapOf()
    private var quantity by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        productDetailsBinding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return productDetailsBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        productDetailsBinding.addOneProductImageViewAsBtn.setOnClickListener(this)
        productDetailsBinding.removeOneProductImageViewAsBtn.setOnClickListener(this)

        sharedViewModel.getProductDetails().observe(viewLifecycleOwner) {
            setProductDetailsValuesToViews(it)
            initializeViewPager2WithTabLayout(colorsValues)
            addProductToCar(it)
        }
    }


    private fun setProductDetailsValuesToViews(
        productDetailsModel: ProductModel
    ) {
        productDetailsBinding.txtViewProductName.text = productDetailsModel.productName
        productDetailsBinding.productDescriptionTxtView.text =
            productDetailsModel.productDescription
        productDetailsBinding.productPriceTxtView.text = productDetailsModel.productPrice

        mapColors = productDetailsModel.productColors?.productColors!!
        quantity = productDetailsModel.productQuantity!!.toInt()
        colorsValues = mapColors.values.toList()
        colorsNames = mapColors.keys.toList()
    }

    private fun initializeViewPager2WithTabLayout(colorsValues: List<String>) {
        val viewPager = productDetailsBinding.productColorSliderViewPager2
        val tabLayout = productDetailsBinding.tabLayout
        val viewPager2Adapter = ViewPager2Adapter(colorsValues)
        productDetailsBinding.productColorSliderViewPager2.adapter = viewPager2Adapter
        productDetailsBinding.productColorSliderViewPager2.orientation =
            ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(
            tabLayout, viewPager, true, true
        ) { tab, position ->
            tab.text = colorsNames[position]
        }.attach()
    }

    private fun numberOfProductInCard(view: View?) {
        var numberOfUnits: Int =
            productDetailsBinding.productNumberToBuyTxtView.text.toString().toInt()

        when (view?.id) {
            R.id.add_oneProduct_ImageView_as_btn -> {
                if (numberOfUnits < quantity) {
                    numberOfUnits++
                } else {
                    Toast.makeText(
                        view.context,
                        "sorry!!,You can't buy more than",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            R.id.remove_oneProduct_ImageView_as_btn -> {
                if (numberOfUnits != 1) {
                    numberOfUnits--
                } else {
                    Toast.makeText(view.context, "the minimum value is 1", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        productDetailsBinding.productNumberToBuyTxtView.text = numberOfUnits.toString()
    }


    override fun onClick(view: View?) {
        numberOfProductInCard(view)
    }

    private val carProductViewModel: CarProductViewModel by activityViewModels()

    private fun addProductToCar(product: ProductModel) {
        productDetailsBinding.btnAddToCar.setOnClickListener {
            carProductViewModel.addProductToCar(product)
        }
    }


    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_element_transition)
    }
}

