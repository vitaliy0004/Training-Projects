package presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import data.Repository
import data.characters.ResultDto
import domain.GetRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    val pagingPerson: Flow<PagingData<ResultDto>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            PagingSource(_state)
        }
    ).flow.cachedIn(viewModelScope)
}