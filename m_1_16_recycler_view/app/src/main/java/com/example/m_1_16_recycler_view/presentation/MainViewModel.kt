package com.example.m_1_16_recycler_view.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.m_1_16_recycler_view.data.PhotoSputnikDto
import com.example.m_1_16_recycler_view.data.PhotosDto
import com.example.m_1_16_recycler_view.nasaAPI.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val pagingPhotos: Flow<PagingData<PhotosDto>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MarsPagingSource(this) }
    ).flow.cachedIn(viewModelScope)

    suspend fun photoSputnik(page: Int): PhotoSputnikDto {
        return repository.getPhotoSputnik(page)
    }
}