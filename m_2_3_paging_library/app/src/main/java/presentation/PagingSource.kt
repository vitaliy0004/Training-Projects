package presentation
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.characters.ResultDto
import data.characters.RickAndMortyDto
import domain.GetRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PagingSource(
  private val state: MutableStateFlow<State>,
        ): PagingSource<Int, ResultDto>(){
    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? = FIRST_PAGE
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,ResultDto> {
        val page = params.key ?: FIRST_PAGE
        kotlin.runCatching {
            if (page == 1) state.value = State.Loading
            GetRepository().getRepository(page)
        }.fold(
            onSuccess = {
                state.value = State.Success
                return LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = if(it.results.isEmpty()) null else page+1
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