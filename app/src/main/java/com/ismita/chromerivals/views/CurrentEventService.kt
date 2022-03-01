package com.ismita.chromerivals.views

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ismita.chromerivals.R

class CurrentEventService: Service() {

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val channelID = "12345"
        val notificationChannel: NotificationChannel
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = NotificationChannel(channelID, "channel", NotificationManager.IMPORTANCE_NONE)
            notificationManager.createNotificationChannel(notificationChannel)

            val handler = Handler(Looper.getMainLooper())
            var count = 0
            handler.post(object : Runnable {
                override fun run() {
                    notificateEvent(count)
                    count++
                    handler.postDelayed(this, 10000)
                }
            })
        }

        return START_NOT_STICKY
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    @RequiresApi(Build.VERSION_CODES.N)
    fun notificateEvent(count: Int) {
        val notification = NotificationCompat.Builder(this, "12345")
            .setContentTitle("Notification Title $count")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(PendingIntent.getActivity(this, 0, Intent(this, MainNavigationActivity::class.java), FLAG_UPDATE_CURRENT))
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(123, notification)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}