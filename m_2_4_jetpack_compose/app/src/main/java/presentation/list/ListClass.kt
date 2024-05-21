package presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.jetpackcompose.R
import data.characters.ResultDto
import presentation.MainViewModel
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import presentation.theme.Green300

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ListClass(
    viewModel: MainViewModel,
    onClick: () -> Unit

) {
    val pagingData by lazy { PagingSource.page(viewModel) }
    val characters: LazyPagingItems<ResultDto> = pagingData.collectAsLazyPagingItems()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Green300),
        verticalArrangement = Arrangement.Center
    ) {
        items(characters) {
            it?.let {
                Person(result = it, onClick = onClick, viewModel = viewModel)
            }
        }
        characters.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Load()
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .height(150.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Load()
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { retry() }) {
                                Text(text = stringResource(id = R.string.refresh))
                            }
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { retry() }) {
                                Text(text = stringResource(id = R.string.refresh))
                            }
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun Load() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.load))
    LottieAnimation(composition = composition)
}


@Composable
fun Person(result: ResultDto, onClick: () -> Unit, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(160.dp)
            .clickable {
                onClick()
                viewModel.resultDto = result
            },
        shape = RoundedCornerShape(15.dp)

    )
    {
        Row(modifier = Modifier.background(Color.Green)) {
            AsyncImage(
                model = result.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .width(150.dp),

                )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 5.dp)
            ) {
                Text(text = result.name)
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.lens),
                        contentDescription = null
                    )
                    Text(modifier = Modifier.padding(0.dp, 5.dp), text = result.status)
                }
                Text(text = stringResource(id = R.string.location) )
                Text(text = result.location.name)
            }
        }
    }
}


