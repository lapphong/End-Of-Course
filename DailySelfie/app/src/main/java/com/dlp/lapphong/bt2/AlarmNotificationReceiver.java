package com.dlp.lapphong.bt2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;

import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.img_camera);

        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.layout_custom_notification);
        notificationLayout.setTextViewText(R.id.textviewTitle,"Daily Selfie");
        notificationLayout.setTextViewText(R.id.textviewContent,"Time for anther selfie");
        notificationLayout.setTextViewText(R.id.textviewTime,new SimpleDateFormat("hh:mm aa").format(new Date()));

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(getNotificationId(), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notificationBuilder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationBuilder = new Notification.Builder(context,"channelID")
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.ic_camera).setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setCustomContentView(notificationLayout);
        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(getNotificationId(), notificationBuilder.build());
    }

    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}
