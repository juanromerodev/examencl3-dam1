package com.example.examencl3dam1

import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.example.examencl3dam1.R

class VideoActivity : AppCompatActivity() {
    lateinit var videoView: VideoView
    var urlVideo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        // Obtener el extra del intent
        urlVideo = intent.getStringExtra("VIDEO_URL")

        // Verificar si el URL del video no es nulo o vacío antes de proceder
        if (!urlVideo.isNullOrBlank()) {
            videoView = findViewById(R.id.vdAPI)
            val uri: Uri = Uri.parse(urlVideo)
            videoView.setVideoURI(uri)

            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            mediaController.setMediaPlayer(videoView)
            videoView.setMediaController(mediaController)

            videoView.start()
        } else {
            // Manejar el caso en el que el URL del video es nulo o vacío
            // Por ejemplo, mostrar un mensaje de error o volver a la actividad anterior
        }
    }
}