package com.example.pushnotify

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseService : FirebaseMessagingService() {


    /**
     * All Information For Firebase Push Notification
     * You Must Need To paste The Code On AndroidManifested: (In application)
     *
     * */

    /** <service android:name=".MyFirebaseService"
    android:exported="false"
    >
    <intent-filter>
    <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
    </service>
     * */

    /**
     * must change some data on build.gradle
     *
     */










    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TOKEN", token)
    }


    override fun onMessageReceived(remoteMsg: RemoteMessage) {
        super.onMessageReceived(remoteMsg)
        if (remoteMsg.notification !=null){
            remoteMsg.notification!!.body?.let {
                remoteMsg.notification!!.title?.let { it1 ->
                    showNotification(
                        it1,
                        it
                    )
                }
            }
        }

    }

   private fun showNotification(title :String, body: String) {
       val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
       val notification = NotificationCompat.Builder(this)
           .setSmallIcon(R.drawable.ic_launcher_background)
           .setContentTitle(title)
           .setContentText(body)
           .setSound(sound)
           .setAutoCancel(true)

       val notifyManger = getSystemService(Context.NOTIFICATION_SERVICE)
       as NotificationManager
       notifyManger.notify(0,notification.build())



    }


}