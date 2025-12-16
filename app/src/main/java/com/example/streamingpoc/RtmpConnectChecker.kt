package com.example.streamingpoc

import android.util.Log
import com.pedro.common.ConnectChecker

class RtmpConnectChecker : ConnectChecker {

    override fun onConnectionStarted(url: String) {
        Log.d("RTMP", "Connection started: $url")
    }

    override fun onConnectionSuccess() {
        Log.d("RTMP", "Connection success")
    }

    override fun onConnectionFailed(reason: String) {
        Log.e("RTMP", "Connection failed: $reason")
    }

    override fun onDisconnect() {
        Log.d("RTMP", "Disconnected")
    }

    override fun onAuthError() {
        Log.e("RTMP", "Auth error")
    }

    override fun onAuthSuccess() {
        Log.d("RTMP", "Auth success")
    }

    override fun onNewBitrate(bitrate: Long) {
        Log.d("RTMP", "New bitrate: $bitrate")
    }
}
