package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.characters.ResultDto
import data.characters.RickAndMortyDto
import data.item.Episode
import data.item.RepositoryItem
import domain.GetRepositoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes = _episodes.asStateFlow()
    lateinit var resultDto: ResultDto
    suspend fun load(params: Int): RickAndMortyDto {
        return GetRepositoryList().getRepository(params)
    }
    fun loadEpisodes(character: ResultDto) {
        val episodesId = StringBuilder()
        if (character.episode.size > 1) {
            character.episode.forEach {
                val lastIndex = it.lastIndexOf('/')
                val temp = it.removeRange(0, lastIndex + 1)
                if (episodesId.isBlank()) episodesId.append(temp) else episodesId.append(",$temp")
            }
        } else {
            val fullEpisodeName = character.episode.first()

            val lastIndex = fullEpisodeName.lastIndexOf('/')
            val temp = fullEpisodeName.removeRange(0, lastIndex + 1)
            episodesId.append(temp).append(",0")
        }
        viewModelScope.launch(Dispatchers.IO) {
            _episodes.value = RepositoryItem().item(episodesId.toString())
        }
    }
}