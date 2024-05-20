package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nasaAPI.Repository


class Factory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(Repository()) as T
        }
        throw IllegalStateException("Unknown class name")
    }
}