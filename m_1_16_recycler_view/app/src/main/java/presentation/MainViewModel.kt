package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import data.PhotosDto
import nasaAPI.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val repo: Repository) : ViewModel() {
    private val _photos = MutableStateFlow<List<PhotosDto>>(emptyList())
    val photos = _photos.asStateFlow()
    val pagingPhotos: Flow<PagingData<PhotosDto>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MarsPagingSource() }
    ).flow.cachedIn(viewModelScope)
}