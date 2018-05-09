package org.myplanettoo.noplastic;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.SystemService;
import org.myplanettoo.noplastic.ui.activity.MainActivity_;

@EApplication
public class NoPlasticApplication extends Application {
    public static final String CHANNEL_ID = "no_plastic_channel";


    @SystemService
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();


        NotificationItem notificationItem = NotificationKnowledge.getRandomByType(NotificationItem.MORNING_TYPE);

        //Vytvorim si INTENT na reciever
        Notification notification = NotificationFactory.factory(notificationItem, getApplicationContext());

        Intent notificationIntent = new Intent(this, AlarmReciever.class);
        notificationIntent.putExtra(AlarmReciever.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReciever.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Popytam si Alarm
        long futureInMillis = SystemClock.elapsedRealtime() + 30000;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

//        NoPlasticService_.intent(this).start();
    }

    public void initChannels() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "No Plastic channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("No Plastic  description");
        notificationManager.createNotificationChannel(channel);
        initChannels();
    }

    private Notification getNotification(String content) {
        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.bottle)
                .setContentText(content)
                .setContentTitle("test")
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return mBuilder.build();
    }
}
