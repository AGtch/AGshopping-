package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.AdapterMainProductsRecyclerView
import com.alycode.agshopping.adapter.AdapterMainProductsRecyclerView.ItemClicked
import com.alycode.agshopping.data.pojo.ProductColors
import com.alycode.agshopping.data.pojo.ProductModel
import com.alycode.agshopping.database.ProductFirebase
import com.alycode.agshopping.databinding.FragmentMainCategoriesBinding
import com.alycode.agshopping.ui.viewModel.BaseViewModel
import com.alycode.agshopping.ui.viewModel.SharedViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainCategoriesFragment : Fragment(), ItemClicked {

    private val adapter: AdapterMainProductsRecyclerView =
        AdapterMainProductsRecyclerView()
    private val viewModel: BaseViewModel by viewModels()
    private lateinit var mainCategoriesFragment: FragmentMainCategoriesBinding
    private lateinit var navController: NavController


    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

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
    ): View {
        TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        // Inflate the layout for this fragment
        mainCategoriesFragment = FragmentMainCategoriesBinding.inflate(inflater)

        return mainCategoriesFragment.root
    }


    private fun initRecyclerView(view: View) {
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

        val map: MutableMap<String, String> = mutableMapOf()
        var nameOfParentInFirebase: String = productModel.productName!!.replace(" ", "")
        nameOfParentInFirebase =
            nameOfParentInFirebase.replaceFirstChar { productModel.productName!![0].lowercase() }

        val child = ProductFirebase.rootReference.child(nameOfParentInFirebase).child("colors")

        child.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                map["main"] = productModel.productImage.toString()
                map.putAll(snapshot.value as MutableMap<String, String>)
                productModel.productColors = ProductColors(map)
                sharedViewModel.setProductDetails(productModel)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        navController.navigate(
            R.id.action_mainCategoriesFragment_to_productDetails
        )
    }


    private fun setExitToFullScreenTransition() {
        exitTransition =
            TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.product_list_exit_transition)
    }

    private fun setReturnFromFullScreenTransition() {
        reenterTransition =
            TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.transition_list_return_transition)
    }
}


