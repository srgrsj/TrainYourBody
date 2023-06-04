package com.srgrsj.tyb.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


//@SuppressLint("RememberReturnType")
//@Composable
//fun VideoDisplayer(
//    videoUrl: String,
//    key: Any
//) {
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val exoPlayer = remember(key) {
//        SimpleExoPlayer.Builder(context).build().apply {
//            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
//                context,
//                Util.getUserAgent(context, context.packageName)
//            )
//
//            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(videoUrl))
//
//            this.prepare(source)
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(factory = { context ->
//        PlayerView(context).apply {
//            player = exoPlayer
//        }
//    })
//}

@Composable
fun VideoPlayer(url: String){
    val context = LocalContext.current
    val player = SimpleExoPlayer.Builder(context).build()
    val playerView = PlayerView(context)
    val mediaItem = MediaItem.fromUri(url)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady

    }
    AndroidView(factory = {
        playerView
    })
}