package org.myplanettoo.noplastic.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.SystemService;
import org.myplanettoo.noplastic.R;
import org.myplanettoo.noplastic.database.NoPlasticDatabase;
import org.myplanettoo.noplastic.database.dao.ItemDao;
import org.myplanettoo.noplastic.ui.activity.InfoActivity;
import org.myplanettoo.noplastic.ui.activity.InfoActivity_;
import org.myplanettoo.noplastic.ui.activity.MainActivity_;

@EService
public class NoPlasticService extends Service {
    public static final String TAG = "NoPlasticService";
    public static final String CHANNEL_ID = "no_plastic_channel";

    @SystemService
    NotificationManager notificationManager;

    ItemDao itemDao;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        itemDao = NoPlasticDatabase.getInstance(getApplicationContext()).itemDao();

        initChannels();
        processThread();

        return Service.START_NOT_STICKY;
    }

    @Background
    void processThread() {
        itemDao.getCountInfo().observeForever(new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long count) {
                Log.d(TAG, "Change count=" + count);
                //after change value in db
                showNotification(createIntentForMainActivity(), "Count plastic", "Terrible " + count + " plastic things");
            }
        });

        //permanently in time intervals
        while (true) {
            Log.d(TAG, "Cycle of checking notifications");

            Intent intent = createIntentForInfo("The Earth in plastic madness", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin accumsan interdum mauris ac lobortis. Aliquam quis neque leo. Nam quis tempor augue, sed tempor enim. Proin a urna eros. Sed eget nunc urna. Ut nisl leo, dictum non interdum et, vestibulum vitae diam. Phasellus libero tortor, porttitor hendrerit vestibulum pretium, rhoncus dictum magna. Suspendisse porta, nulla sit amet dictum maximus, purus tortor pharetra neque, non mollis ligula leo non ante. Vivamus ut dolor sed metus sollicitudin convallis nec vitae dolor. Etiam faucibus egestas auctor. Vivamus ut est sem. Proin dignissim volutpat augue, eu fermentum turpis luctus ac. Donec ut justo quis felis dignissim scelerisque a sed ex.\n" +
                    "\n" +
                    "Suspendisse at lacus hendrerit, ultrices tortor quis, elementum turpis. Vivamus quis tellus ac enim dignissim dapibus. Donec aliquet non quam et consectetur. In et vulputate libero. Nunc feugiat, metus sed bibendum laoreet, erat mi vehicula urna, id porttitor lorem nisi vitae mi. Sed tincidunt enim eget quam feugiat, at venenatis odio imperdiet. Nullam luctus lorem a purus venenatis lacinia. Vestibulum faucibus, nibh non tincidunt rhoncus, sapien nisi tincidunt nisi, et sagittis erat nisi id enim. Duis pulvinar dolor in molestie aliquam. Suspendisse in tempor massa. Aenean tempus justo at dui varius, ut tincidunt nibh imperdiet. Nulla accumsan, augue vitae varius dignissim, nisi risus laoreet velit, nec ullamcorper velit neque at lorem. Aenean vitae lacus id mauris fringilla ornare. Praesent egestas ex erat, eget pulvinar lacus pretium non. Proin tincidunt volutpat mauris, quis euismod nulla venenatis vitae.\n" +
                    "\n");

            showNotification(intent, "Timer", "Interesting no plastic info !");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Intent createIntentForMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return intent;
    }

    private Intent createIntentForInfo(String title, String infoText) {
        Intent intent = new Intent(getApplicationContext(), InfoActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(InfoActivity.EXTRA_TITLE, title);
        intent.putExtra(InfoActivity.EXTRA_INFO_TEXT, infoText);

        return intent;
    }

    private void showNotification(@Nullable Intent intent, String title, String content) {
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.bottle)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(2, mBuilder.build());
    }
}