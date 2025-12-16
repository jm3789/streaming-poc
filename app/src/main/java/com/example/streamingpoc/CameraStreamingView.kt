package com.example.streamingpoc

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pedro.library.view.OpenGlView
import com.pedro.library.rtmp.RtmpCamera2

@Composable
fun CameraStreamingView(
    rtmpUrl: String,
    isStreaming: Boolean
) {
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        factory = { ctx ->
            val openGlView = OpenGlView(ctx)

            val rtmpCamera = RtmpCamera2(
                openGlView,
                RtmpConnectChecker()
            )

            openGlView.tag = rtmpCamera
            openGlView
        },
        update = { view ->
            val rtmpCamera = view.tag as RtmpCamera2

            if (isStreaming && !rtmpCamera.isStreaming) {
                rtmpCamera.prepareVideo(
                    1280,
                    720,
                    30,
                    2_000_000,
                    1 // 🔥 KeyFrame Interval = 1초 (LL-HLS 대응)
                )
                rtmpCamera.prepareAudio()
                rtmpCamera.startStream(rtmpUrl)
            }

            if (!isStreaming && rtmpCamera.isStreaming) {
                rtmpCamera.stopStream()
            }
        }
    )
}
