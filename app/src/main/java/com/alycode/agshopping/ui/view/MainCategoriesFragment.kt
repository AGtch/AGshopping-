package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.AdapterMainProductsRecyclerView
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.ui.viewModel.BaseViewModel


class MainCategoriesFragment : Fragment() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter: AdapterMainProductsRecyclerView
        val viewModel: BaseViewModel by viewModels()
        val recyclerView: RecyclerView = view.findViewById(R.id.main_product_recyclerview)
        viewModel.getResponseUsingLiveData()
            .observe(viewLifecycleOwner) { response: MutableList<ProductModel>? ->
                adapter = AdapterMainProductsRecyclerView(response!!)
                recyclerView.adapter = adapter
                adapter.setProductOnClickListener(object : ProductOnClickListener {
                    override fun productOnClickListener(productPosition: Int?) {
                        navController.navigate(R.id.action_mainCategoriesFragment_to_productDetails)
                    }
                })
            }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL, false
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_categories, container, false)
    }

}
