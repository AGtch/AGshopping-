package com.alycode.agshopping.ui.main

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.NavHostFragment
import com.alycode.agshopping.R
import com.alycode.agshopping.database.HandleNetwork

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToCar: ImageButton = findViewById(R.id.navigate_to_carBtn)
        val backToPreviousFragment: ImageButton = findViewById(R.id.back_toPreviousFragmentBtn)
        val networking = HandleNetwork()
        if (!networking.hasNetworkAvailable(applicationContext)) {
            Toast.makeText(this, "No Connection.....", Toast.LENGTH_SHORT).show()
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController

        goToCar.setOnClickListener {
            navController.navigate(R.id.carProductFragment)
        }
        backToPreviousFragment.setOnClickListener {
            navController.popBackStack()
        }


    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }

}