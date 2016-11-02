
package com.example.test8_10_2.util;

import com.example.test8_10_2.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class NotificationUtil {

	public static Notification notification;// 通知
	public static NotificationManager manager;// 通知的管理者
	public static final int NOTIFI_APPICON_ID = 1;// 标志

	/**
	 * 启动一个Notification
	 */
	public static void startNotification(Context context) {
		if (notification == null) {
			notification = new Notification();
		}
		notification.flags = Notification.FLAG_AUTO_CANCEL;// 当用户点击完通知后,自动取消通知
		notification.icon = R.drawable.ic_launcher1;// 通知的
		notification.tickerText = "新通知";// 通知的标题
		notification.when = System.currentTimeMillis();// 时间

		RemoteViews rv = new RemoteViews(context.getPackageName(),
				R.layout.layout_notification_appicon);//RemoteView
		//在加载一种布局,inflate

		notification.contentView = rv;//将自定义个通知View给Notification
		
		Intent i = new Intent("com.example.test8_10_2.util");//意图
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, i,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.contentIntent = pendingIntent;

		if (manager == null) {
			manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		manager.notify(NOTIFI_APPICON_ID, notification);//发送一个通知

	}

	/**
	 * 取消一个通知
	 * @param context
	 */
	public static void cancleNotification(Context context) {
		if (manager == null) {
			manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		manager.cancel(NOTIFI_APPICON_ID);//取消一个通知

	}
}
