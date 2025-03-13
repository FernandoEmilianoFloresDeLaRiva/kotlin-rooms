package com.example.room_clase.core.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.speech.tts.TextToSpeech
import androidx.core.app.NotificationCompat
import com.example.room_clase.R
import java.util.Locale

class TextToSpeechService : Service(), TextToSpeech.OnInitListener {

    private lateinit var textToSpeech: TextToSpeech
    private var bookText: String? = null
    private var isSpeaking = false
    private var isTtsInitialized = false

    override fun onCreate() {
        super.onCreate()
        textToSpeech = TextToSpeech(this, this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "STOP") {
            stopSelf()
            return START_NOT_STICKY
        }

        bookText = intent?.getStringExtra("BOOK_TEXT")
        startForeground(1, createNotification("Iniciando..."))

        if (isTtsInitialized) {
            bookText?.let { speakText(it) }
        }

        return START_NOT_STICKY
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.language = Locale("es", "MX")

            val result = textToSpeech.setLanguage(Locale("es", "MX"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                stopSelf()
                return
            }

            isTtsInitialized = true

            bookText?.let { speakText(it) }
        } else {
            stopSelf()
        }
    }

    private fun speakText(text: String) {
        if (!isTtsInitialized) {
            return
        }

        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, "TTS_ID")
        isSpeaking = true
        updateNotification("Leyendo en voz alta...")
    }

    private fun updateNotification(text: String) {
        val notification = createNotification(text)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    private fun createNotification(content: String): Notification {
        val stopIntent = Intent(this, TextToSpeechService::class.java).apply {
            action = "STOP"
        }
        val stopPendingIntent = PendingIntent.getService(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, "TTS_CHANNEL")
            .setContentTitle("Lector de Libros")
            .setContentText(content)
            .setSmallIcon(R.drawable.baseline_menu_book_24)
            .addAction(R.drawable.stop, "Detener", stopPendingIntent)
            .setOngoing(true)
            .build()
    }

    override fun onDestroy() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        isSpeaking = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}