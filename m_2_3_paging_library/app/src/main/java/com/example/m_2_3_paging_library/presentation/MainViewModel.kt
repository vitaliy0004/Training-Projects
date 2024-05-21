package com.example.m_2_3_paging_library.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.m_2_3_paging_library.data.characters.ResultDto
import com.example.m_2_3_paging_library.data.characters.RickAndMortyDto
import com.example.m_2_3_paging_library.domain.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRepository: GetRepository) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    val pagingPerson: Flow<PagingData<ResultDto>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            PagingSource(_state,this)
        }
    ).flow.cachedIn(viewModelScope)
    suspend fun getPerson(page:Int):RickAndMortyDto{
        return getRepository.getRepository(page)
    }
}