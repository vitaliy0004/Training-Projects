package presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.PhotosDto
import nasaAPI.Repository

class MarsPagingSource: PagingSource<Int, PhotosDto>() {
    override fun getRefreshKey(state: PagingState<Int, PhotosDto>): Int? = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosDto> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            Repository().getPhotoSputnik(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.photos,
                    prevKey = null,
                    nextKey = if (it.photos.isEmpty()) null else page+1
                )
            },
            onFailure = { LoadResult.Error(it)}
        )
    }
}