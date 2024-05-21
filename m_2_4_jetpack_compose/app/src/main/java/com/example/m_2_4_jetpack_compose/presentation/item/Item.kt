package com.example.m_2_4_jetpack_compose.presentation.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.m_2_4_jetpack_compose.R
import com.example.m_2_4_jetpack_compose.data.item.Episode
import com.example.m_2_4_jetpack_compose.presentation.MainViewModel
import com.example.m_2_4_jetpack_compose.presentation.theme.Green300

@Composable
fun Item(viewModel: MainViewModel) {
    val episodes: List<Episode> by viewModel.episodes.collectAsState(initial = emptyList())
    val result = viewModel.resultDto
    viewModel.loadEpisodes(result)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green300)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = result.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(5.dp),
        )
        Text(
            text = result.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp, 15.dp),
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight(400)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 1f),
                            Color.White.copy(alpha = 0.3f),
                            Color.White.copy(alpha = 0f),
                            Color.White.copy(alpha = 0f),
                        )
                    )
                )
        )
        Text(title = stringResource(id = R.string.status), answer = result.status)
        Text(title = stringResource(id = R.string.gender), answer = result.gender)
        Text(title = stringResource(id = R.string.last_location), answer = result.location.name)
        Text(title = stringResource(id = R.string.first_seen), answer = result.episode[0])
        Text(
            text = stringResource(id = R.string.episode),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 25.dp),
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight(400)
        )
        CharacterDetail { episodes }
    }
}

@Composable
fun Text(title: String, answer: String) {
    Text(text = title, modifier = Modifier.padding(25.dp, 10.dp, 0.dp, 5.dp), color = Color.Green)
    Text(text = answer, color = Color.White, modifier = Modifier.padding(25.dp, 0.dp))
}

@Composable
fun CharacterDetail(
    loadEpisodes: @Composable () -> List<Episode>
) {
    val episode = loadEpisodes()
    episode.forEach { Episodes(list = it) }
}


@Composable
fun Episodes(list: Episode) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(10.dp, 5.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .padding(top = 5.dp, start = 15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = list.name,
                    modifier = Modifier.padding(end = 15.dp),
                    maxLines = 1
                )
                Text(text = list.episode, modifier = Modifier.padding(end = 5.dp))
            }
            Text(text = list.airDate, modifier = Modifier.padding(top = 5.dp))
        }

    }
}




