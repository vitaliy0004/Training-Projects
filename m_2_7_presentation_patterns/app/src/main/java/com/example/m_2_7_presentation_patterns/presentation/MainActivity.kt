package com.example.m_2_7_presentation_patterns.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.aconst.AppConstants
import com.example.core.navigation.NavigationRouter
import com.example.core.navigation.SomeViewModel
import com.example.m_2_7_presentation_patterns.R
import com.example.m_2_7_presentation_patterns.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navControllers: NavController
    private val navigationViewModel: SomeViewModel by viewModels()

    @Inject
    lateinit var navRouter: NavigationRouter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentController) as NavHostFragment
        navControllers = navHostFragment.navController
        navRouter.setViewModel(navigationViewModel)
        navRouter.setActivityDetail(supportFragmentManager, R.id.fragmentController)
        val bottomNav = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    navRouter.switchTab(
                        AppConstants.SEARCH_TAB,
                        com.example.search.R.navigation.nav_graph_search
                    )
                    true
                }

                R.id.citys -> {
                    navRouter.switchTab(
                        AppConstants.CITY_TAB,
                        com.example.city.R.navigation.nav_graph_city
                    )
                    true
                }

                else -> {
                    navRouter.switchTab(
                        AppConstants.SEARCH_TAB,
                        com.example.search.R.navigation.nav_graph_search
                    )
                    false
                }
            }
        }

        navigationViewModel.exitApp.observe(this, Observer { shouldExit ->
            if (shouldExit) {
                finish()  // Закрытие приложения
            }
        })
    }

    override fun onBackPressed() {
        navRouter.navigateBack()
        if (navigationViewModel.checkedTabBackStace) {
            navigationViewModel.checkedTabBackStace = false
            binding.bottomNavigation.selectedItemId = R.id.search
        }
    }
}