package com.example.mrglisse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        /*Issue not fixed yet
        https://issuetracker.google.com/142847973
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)*/

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if(navHostFragment != null){
            val navController = navHostFragment.findNavController()
            bottomNavigationView.setupWithNavController(navController)
        }

        supportActionBar?.hide()
    }
}