package presentation.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.characters.ResultDto
import presentation.MainViewModel


class PagingSource(
    private val viewModel: MainViewModel
) : PagingSource<Int, ResultDto>() {
    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? = null
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> =
        kotlin.runCatching {
            viewModel.load(params.key ?: 1).results
        }.fold(
            onSuccess = { list ->
                LoadResult.Page(
                    data = list,
                    prevKey = params.key?.let { it - 1 },
                    nextKey = (params.key ?: 0) + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    companion object {
        fun page(viewModel: MainViewModel) =
            Pager(PagingConfig(pageSize = 10)) { PagingSource(viewModel) }.flow
    }
}

