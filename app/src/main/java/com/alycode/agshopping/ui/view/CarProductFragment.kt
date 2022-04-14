package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alycode.agshopping.R
import com.alycode.agshopping.adapter.CarRecyclerViewAdapter
import com.alycode.agshopping.databinding.FragmentCarProductBinding
import com.alycode.agshopping.ui.viewModel.CarProductViewModel

class CarProductFragment : Fragment() {

    private var carAdapter: CarRecyclerViewAdapter = CarRecyclerViewAdapter()

    private val viewModel: CarProductViewModel by activityViewModels()
    private lateinit var carProductObject: FragmentCarProductBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_car_product, container, false)
        carProductObject = FragmentCarProductBinding.inflate(inflater)
        return carProductObject.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView(view)
        viewModel.getAllProductInCarUsingLiveData().observe(viewLifecycleOwner) {

            Log.d("listInRecyclerview", "onViewCreated: list in recyclerView ${it.size}")
            carAdapter.setProductsToCarList(it)
        }

//        carProductObject.gotoLoginBtn.setOnClickListener {
//            navController.navigate(R.id.action_carProductFragment_to_loginFragment)
//        }
    }

    private fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.carProductRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = carAdapter
    }

}
