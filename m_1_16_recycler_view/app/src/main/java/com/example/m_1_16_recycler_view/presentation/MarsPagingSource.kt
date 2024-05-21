package com.example.m_1_16_recycler_view.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.m_1_16_recycler_view.data.PhotosDto
import com.example.m_1_16_recycler_view.nasaAPI.Repository

class MarsPagingSource(private val viewModel: MainViewModel) : PagingSource<Int, PhotosDto>() {
    override fun getRefreshKey(state: PagingState<Int, PhotosDto>): Int? = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosDto> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            viewModel.photoSputnik(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.photos,
                    prevKey = null,
                    nextKey = if (it.photos.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }
}