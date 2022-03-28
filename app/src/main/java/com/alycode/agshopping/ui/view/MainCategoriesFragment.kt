package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.AdapterMainProductsRecyclerView
import com.alycode.agshopping.adapter.AdapterMainProductsRecyclerView.ItemClicked
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.ui.viewModel.BaseViewModel
import com.alycode.agshopping.ui.viewModel.SharedViewModel

class MainCategoriesFragment : Fragment(), ItemClicked {

    private val adapter: AdapterMainProductsRecyclerView =
        AdapterMainProductsRecyclerView()
    private var action: Int = R.id.action_mainCategoriesFragment_to_productDetails
    private val viewModel: BaseViewModel by viewModels()

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        viewModel.getAllProductFromFirebaseUsingLiveData()
            .observe(viewLifecycleOwner) { response: MutableList<ProductModel>? ->
                if (response != null) {
                    setProductData(response, this)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_categories, container, false)
    }



    fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.main_product_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL, false
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun setProductData(products: List<ProductModel>, itemClicked: ItemClicked) {
        adapter.setProductData(products, itemClicked)
    }

    override fun getItemCLiked(productModel: ProductModel) {
        sharedViewModel.setProductDetails(productModel)
        navController.navigate(action)
    }
}


