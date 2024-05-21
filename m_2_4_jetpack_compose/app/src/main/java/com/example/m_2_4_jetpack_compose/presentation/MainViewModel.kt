package com.example.m_2_4_jetpack_compose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_2_4_jetpack_compose.data.characters.ResultDto
import com.example.m_2_4_jetpack_compose.data.characters.RickAndMortyDto
import com.example.m_2_4_jetpack_compose.data.item.Episode
import com.example.m_2_4_jetpack_compose.data.item.RepositoryItem
import com.example.m_2_4_jetpack_compose.domain.GetRepositoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRepositoryList: GetRepositoryList) : ViewModel() {
    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes = _episodes.asStateFlow()
    lateinit var resultDto: ResultDto
    suspend fun load(params: Int): RickAndMortyDto {
        return getRepositoryList.getRepository(params)
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