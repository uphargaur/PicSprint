package com.uphar.picsprint

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.annotation.ExperimentalCoilApi
import com.uphar.picsprint.ui.theme.PicSprintTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.uphar.bussiness.domain.data.Coverage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            PicSprintTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: MainViewModel = hiltViewModel()
                    LaunchedEffect(Unit) {
                        viewModel.getToken()
                    }
                    val loadingState by viewModel.loading.collectAsState()
                    val dataState by viewModel.allMessagesDataState.collectAsState()
                    val sendMessageDataState by viewModel.allMessagesDataState.collectAsState()
                    ShowLoader(loadingState)
                    when (dataState) {
                        is DataState.Loading -> {
                            Log.i("ChatScreen", "getAllMessages: Loading")
                        }

                        is DataState.Error -> {
                            Log.i(
                                "ChatScreen",
                                "getAllMessages: Error-> ${(dataState as DataState.Error).exception.message}"
                            )
                            ShowError((dataState as DataState.Error).exception.message ?: "Something went wrong")
                        }

                        is DataState.Success -> {
                            Log.i(
                                "ChatScreen",
                                "getAllMessages: Success-> ${(dataState as DataState.Success).data.toString()}"
                            )
                           ImageGrid(covers = (dataState as DataState.Success).data.response)
                        }
                    }

                }
            }
        }
    }




    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun ImageGrid(covers: List<Coverage>) {
        LazyColumn {
            items(covers.chunked(3)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (cover in rowItems) {
                        val quality = cover.thumbnail.qualities.maxOrNull() ?: 0
                        val imageUrl =
                            "${cover.thumbnail.domain}/${cover.thumbnail.basePath}/$quality/${cover.thumbnail.key}"
                        GridItem(imageUrl = imageUrl)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun GridItem(imageUrl: String) {
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(100.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            color = Color.LightGray
        ) {
            val painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)
                    placeholder( R.drawable.baseline_image_24) // Placeholder image resource
                }
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
@Composable
fun ShowLoader(loading: Boolean) {
    if (loading)
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(60.dp))
        }
}

@Composable
private fun ShowError(msg: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = msg,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}
