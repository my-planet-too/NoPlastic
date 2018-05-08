package org.myplanettoo.noplastic.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.lifecycle.Observer;
import android.content.Intent;
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
import org.myplanettoo.noplastic.ui.activity.MainActivity_;

@EService
public class NoPlasticService extends Service {
    public static final String TAG = "NoPlasticService";

    @SystemService
    NotificationManager notificationManager;

    ItemDao itemDao;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        itemDao = NoPlasticDatabase.getInstance(getApplicationContext()).itemDao();
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
                showNotification("Count plastic", "Terrible " + count + " plastic things");
            }
        });

        //permanently in time intervals
        while (true) {
            Log.d(TAG, "Cycle of checking notifications");

            showNotification("Timer", "Write it !");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showNotification(@Nullable String title, String content) {
        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.bottle)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(2, mBuilder.build());
    }
}