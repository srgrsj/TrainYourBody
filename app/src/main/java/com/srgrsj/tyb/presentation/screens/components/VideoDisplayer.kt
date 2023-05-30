package com.srgrsj.tyb.presentation.screens.components

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@SuppressLint("RememberReturnType")
@Composable
fun VideoDisplayer(context: Context, videoUrl: String){
    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            val dataSourceFactory : DataSource.Factory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.packageName))

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl))

            this.prepare(source)
        }
    }

    AndroidView(factory = { context ->
        PlayerView(context).apply {
            player = exoPlayer
        }
    })

}