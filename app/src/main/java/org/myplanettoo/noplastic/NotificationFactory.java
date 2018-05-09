package org.myplanettoo.noplastic;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import org.myplanettoo.noplastic.ui.activity.MainActivity_;

public class NotificationFactory {

    public static final String CHANNEL_ID = "no_plastic_channel";

    static Notification factory(NotificationItem notificationItem, Context context) {
        Intent intent = new Intent(context, MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.bottle)
                .setContentText(notificationItem.getDescription())
                .setContentTitle(notificationItem.getTitle())
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return mBuilder.build();
    }
}
