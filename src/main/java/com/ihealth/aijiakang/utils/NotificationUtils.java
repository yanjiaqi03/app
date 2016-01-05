package com.ihealth.aijiakang.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import iHealth.AiJiaKang.MI.R;

public class NotificationUtils {
	private static NotificationUtils mInstance = null;
	public final static String IHEALTH_PUSH_RECEIVER = "com.xiaomi.mipush.RECEIVE_MESSAGE";
	
	public static NotificationUtils getInstance(){
		if(mInstance == null){
			mInstance = new NotificationUtils();
		}
		
		return mInstance;
	}
	
	public void createNotification(Context context, int id) {
		Intent intent = new Intent(IHEALTH_PUSH_RECEIVER);
		PendingIntent pendIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
		mBuilder.setContentTitle("Test Title")
		.setContentText("Test Content")
		.setWhen(System.currentTimeMillis())
		.setPriority(Notification.PRIORITY_DEFAULT)
		.setDefaults(Notification.DEFAULT_ALL)
		.setSmallIcon(R.drawable.ic_launcher)
		.setOngoing(false)
		.setTicker("Test Content")
		.setAutoCancel(true)
		.setContentIntent(pendIntent);
		
		
		mNotificationManager.notify(id, mBuilder.build());
	}
	
	public void cancelNotification(Context context, int id) {
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(id);
	}

	public void cancelAllNotification(Context context) {
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
	}
}
