package com.prathameshkumbhar.aniyey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.prathameshkumbhar.aniyey.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupNavigation()
    }

    private fun setupUi() {
        window.statusBarColor = getColor(R.color.red)
    }

    private fun setupNavigation() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.aniyeyFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
    }
}