package com.diegoalvis.android.sicor

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by diegoalvis on 13/11/2017.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Check if message contains a notification payload.
        if (remoteMessage?.notification != null) {
            sendNotification(remoteMessage.notification.body!!)
        }

    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.logo_corec)
                .setContentTitle("Nueva Citacion")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val min = calendar.get(Calendar.MINUTE)
        val sec = calendar.get(Calendar.SECOND)
        var am_pm = calendar.get(Calendar.AM_PM)
        var ampm: String

        ampm = if (am_pm == Calendar.AM) {
            "AM"
        } else {
            "PM"
        }

        val prefs = this.getSharedPreferences(NOTIFCATIONS, 0)
        val editor = prefs.edit()
        editor.putString(CONTENT, prefs.getString(CONTENT, "") + "#" + messageBody)
        editor.putString(DATE, prefs.getString(DATE, "") + "#" + hour + ':' + min + ':' + sec + ' ' + ampm)
        editor.apply()

    }

}