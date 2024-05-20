package presintation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Fail
import data.FailDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val STOP_TIMEOUT_MILLIS = 1000L
class MainViewModel(private val photoDao: FailDao) : ViewModel() {
        val allPhotosOfSights = this.photoDao.getFail()
                .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                        initialValue = emptyList()
                )
        fun savePhoto(photo: String, dataPhoto: String) {
                viewModelScope.launch {
                        photoDao.addFail(
                                Fail(
                                        fail = photo,
                                        data = dataPhoto
                                )
                        )
                }

        }

        fun delete() {
               viewModelScope.launch {
                       allPhotosOfSights.value.let{photoDao.deleteFail(it)}
               }
        }

}