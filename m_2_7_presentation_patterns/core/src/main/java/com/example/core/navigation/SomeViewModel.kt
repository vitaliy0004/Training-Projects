package com.example.core.navigation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.aconst.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SomeViewModel @Inject constructor() : ViewModel() {
    val navControllers = mutableMapOf<String, NavController>()// NavController для каждой вкладки
    var tabChecked = AppConstants.SEARCH_TAB
    var checkedTabBackStace = false
    lateinit var fragmentManager: FragmentManager
    var containerId: Int = 0
    private val _exitApp = MutableLiveData<Boolean>(false)
    val exitApp: LiveData<Boolean> get() = _exitApp

    fun readeExitApp() {
        _exitApp.value = true
    }

    fun cleanUpActiveTab() {
        navControllers.remove(tabChecked)
        val existingFragment = fragmentManager.findFragmentByTag(tabChecked)
        fragmentManager.beginTransaction().remove(existingFragment!!).commit()
        tabChecked = tabChecked.removePrefix(AppConstants.CORE_MODULE)
    }

    fun navigateToSearchOnExit() {
        checkedTabBackStace = true
        tabChecked =
            if (navControllers.containsKey(AppConstants.CORE_MODULE + AppConstants.SEARCH_TAB))
                AppConstants.CORE_MODULE + AppConstants.SEARCH_TAB
            else AppConstants.SEARCH_TAB
    }
}