package com.example.navigation

import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.aconst.AppConstants
import com.example.core.navigation.NavigationRouter
import com.example.core.navigation.SomeViewModel
import javax.inject.Inject

class NavigationRouterImp @Inject constructor() : NavigationRouter {
    private lateinit var someViewModel: SomeViewModel

    override fun setViewModel(viewModel: SomeViewModel) {
        this.someViewModel = viewModel
    }

    override fun setActivityDetail(fragmentManager: FragmentManager, containerId: Int) {
        someViewModel.fragmentManager = fragmentManager
        someViewModel.containerId = containerId
    }

    override fun switchTab(tabTag: String, @NavigationRes graphRes: Int) {
        if (tabTag != someViewModel.tabChecked) {
            if (someViewModel.navControllers.containsKey(AppConstants.CORE_MODULE + tabTag))
                switchGraph(AppConstants.CORE_MODULE + tabTag, graphRes)
            else
                switchGraph(tabTag, graphRes)
        }
    }

    override fun openCoreFlow(tabTag: String, @NavigationRes graphRes: Int) {
        switchGraph(AppConstants.CORE_MODULE + tabTag, graphRes)
    }


    private fun switchGraph(tag: String, @NavigationRes graphRes: Int) {
        someViewModel.tabChecked = tag
        val existingFragment = someViewModel.fragmentManager.findFragmentByTag(tag)
        someViewModel.fragmentManager.beginTransaction().apply {
            someViewModel.fragmentManager.fragments.forEach { hide(it) } // Прячем все активные фрагменты
            if (existingFragment != null) {
                show(existingFragment)
                commitNow()
            } else {
                val newFragment = NavHostFragment.create(graphRes)
                add(someViewModel.containerId, newFragment, tag)
                commitNow()
                someViewModel.navControllers[tag] = newFragment.navController
            }
        }
    }


    private fun selectGraphByString(nameGraph: String): Int {
        when (nameGraph) {
            AppConstants.CORE_MODULE + AppConstants.SEARCH_TAB,
            AppConstants.CORE_MODULE + AppConstants.CITY_TAB ->
                return com.example.core.R.navigation.navigation_core

            AppConstants.SEARCH_TAB -> return com.example.feature_search.R.navigation.nav_graph_search
            AppConstants.CITY_TAB -> return com.example.feature_city.R.navigation.nav_graph_city
//            else -> {
//                throw (Exception("NavigationRouterImp: Name Graph"))
//                return  AppConstants.EXCEPTION_INT
//            }
        }
        return AppConstants.EXCEPTION_INT
    }

    override fun navigateBack() {
        val tabBackStack = someViewModel.navControllers[someViewModel.tabChecked]
        if (tabBackStack?.previousBackStackEntry != null) {//просто  идем назад по стеку
            tabBackStack.popBackStack()
            switchGraph(
                someViewModel.tabChecked,
                selectGraphByString(someViewModel.tabChecked)
            ) // Восстанавливаем core или вкладку
        } else {
            if (someViewModel.tabChecked.startsWith(AppConstants.CORE_MODULE)) {// возвращаемся на вкладку из модуля core
                someViewModel.cleanUpActiveTab()
                switchGraph(someViewModel.tabChecked, selectGraphByString(someViewModel.tabChecked))
            } else if (someViewModel.tabChecked != AppConstants.SEARCH_TAB) {
                someViewModel.navigateToSearchOnExit()
                switchGraph(someViewModel.tabChecked, selectGraphByString(someViewModel.tabChecked))
            } else someViewModel.readeExitApp()
        }
    }


    override fun getNavController(tag: String): NavController? {
        return someViewModel.navControllers[tag]
    }
}