package com.example.m_2_3_paging_library.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.m_2_3_paging_library.data.characters.ResultDto
import com.example.m_2_3_paging_library.domain.GetRepository
import kotlinx.coroutines.flow.MutableStateFlow
class PagingSource(
    private val state: MutableStateFlow<State>,
    private val viewModel: MainViewModel
) : PagingSource<Int, ResultDto>() {
    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? = FIRST_PAGE
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> {
        val page = params.key ?: FIRST_PAGE
        kotlin.runCatching {
            if (page == 1) state.value = State.Loading
            viewModel.getPerson(page)
        }.fold(
            onSuccess = {
                state.value = State.Success
                return LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = if (it.results.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                state.value = State.Error
                return LoadResult.Error(it)
            }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}