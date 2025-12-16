package com.example.streamingpoc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun StreamingScreen() {
    val context = LocalContext.current
    var isStreaming by remember { mutableStateOf(false) }

    val rtmpUrl = "rtmp://rtmp-ls2-k1.video.media.ntruss.com:8080/relay/keykey"
    val hlsUrl = "https://playback-live-ls2.video.media.ntruss.com/relay/keykey/index.m3u8"

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("📡 Live Streaming PoC", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(8.dp))

        // 📷 카메라 + RTMP 송출
        CameraStreamingView(
            rtmpUrl = rtmpUrl,
            isStreaming = isStreaming
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { isStreaming = !isStreaming },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isStreaming) "송출 중지" else "송출 시작")
        }

        Spacer(Modifier.height(24.dp))
        HorizontalDivider()
        Spacer(Modifier.height(24.dp))

        // ▶️ ExoPlayer 시청
        LivePlayerView(hlsUrl = hlsUrl)
    }

}