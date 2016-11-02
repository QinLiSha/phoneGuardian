package com.example.test8_10_2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.test8_10_2.bean.AppInfo;
import com.example.test8_10_2.bean.RunningAppInfo;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;


public class AppInfoManager {
	private Context context;
	private PackageManager packManager;
	private ActivityManager activityManager;

	private List<AppInfo> allPackageInfos = new ArrayList<AppInfo>();
	private List<AppInfo> systemPackageInfos = new ArrayList<AppInfo>();
	private List<AppInfo> usePackageInfos = new ArrayList<AppInfo>();

	public AppInfoManager(Context context) {
		this.context = context;
		packManager = context.getPackageManager();
		activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

	}

	private static AppInfoManager manager = null;

	public static AppInfoManager getAppInfoManager(Context context) {
		if (manager == null) {
			manager = new AppInfoManager(context);
		}
		return manager;
	}

	/**
	 * 清理空闲时的后台，不包括系统级后台
	 */

	public void KillAllProcess() {
		List<RunningAppProcessInfo> allProcess = activityManager
				.getRunningAppProcesses();
		for (RunningAppProcessInfo runAppInfo : allProcess) {
			if (runAppInfo.importance > RunningAppProcessInfo.IMPORTANCE_SERVICE) {
				String processName = runAppInfo.processName;
				try {
					ApplicationInfo appInfo = packManager.getApplicationInfo(
							processName, PackageManager.GET_META_DATA
									| PackageManager.GET_SHARED_LIBRARY_FILES
									| PackageManager.GET_SHARED_LIBRARY_FILES
									| PackageManager.GET_UNINSTALLED_PACKAGES);
					if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {

					} else {
						activityManager.killBackgroundProcesses(processName);
					}
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 删除指定的正在运行的程序
	 * 
	 * @param packageName
	 */
	public void killProcesses(String packageName) {
		activityManager.killBackgroundProcesses(packageName);
	}

	public final static int SYSTEM_TYPE_FLAG = 1;
	public final static int USER_TYPE_FLAG = 0;

	/**
	 * 获取系统正在运行的所有程序
	 * 
	 * @return
	 */

	public Map<Integer, List<RunningAppInfo>> getRunningAppInfos() {
		// 用来存放所有正在运行的程序信息
		Map<Integer, List<RunningAppInfo>> appinfos = new HashMap<Integer, List<RunningAppInfo>>();
		// 存放系统程序的信息
		List<RunningAppInfo> systemInfos = new ArrayList<RunningAppInfo>();
		// 存放非系统应用的信息
		List<RunningAppInfo> userInfos = new ArrayList<RunningAppInfo>();
		// 存放所有的应用程序信息
		List<RunningAppProcessInfo> allInfos = activityManager
				.getRunningAppProcesses();

		for (RunningAppProcessInfo run : allInfos) {
			int pid = run.pid;
			String packageName = run.processName;
			int importance = run.importance;
			if (importance > RunningAppProcessInfo.IMPORTANCE_SERVICE) {
				Drawable icon;
				String lableName;
				long size;
				MemoryInfo[] memoryInfo = activityManager
						.getProcessMemoryInfo(new int[] { pid });
				size = memoryInfo[0].getTotalPrivateDirty() * 1024;
				try {
					ApplicationInfo appInfos = packManager.getApplicationInfo(
							packageName, PackageManager.GET_META_DATA
									| PackageManager.GET_SHARED_LIBRARY_FILES
									| PackageManager.GET_UNINSTALLED_PACKAGES);
					icon = packManager.getApplicationIcon(appInfos);
					lableName = packManager.getApplicationLabel(appInfos)
							.toString();
					RunningAppInfo appInfo = new RunningAppInfo(packageName,
							icon, lableName, size);
					if ((appInfos.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
						appInfo.setSystem(true);
						appInfo.setClear(false);
						systemInfos.add(appInfo);// 系统信息的集合
					} else {
						appInfo.setSystem(false);
						appInfo.setClear(true);
						userInfos.add(appInfo);// 用户应用的集合
					}

				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
			}

		}
		appinfos.put(SYSTEM_TYPE_FLAG, systemInfos);
		appinfos.put(USER_TYPE_FLAG, userInfos);
		return appinfos;
	}

	/**
	 * 返回全部数据
	 * 
	 * @param isReset
	 * @return
	 */
	public List<AppInfo> getAllPackageInfos(boolean isReset) {
		if (isReset) {
			loadAllDataPackageInfo();
		}
		return allPackageInfos;
	}

	public List<AppInfo> getSystemPackageInfos(boolean isReset) {
		if (isReset) {
			loadAllDataPackageInfo();
			systemPackageInfos.clear();
			for (AppInfo pi : allPackageInfos) {
				if ((pi.getPackageInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
					systemPackageInfos.add(pi);
				}
			}
		}
		return systemPackageInfos;
	}

	public List<AppInfo> getUserPackageInfos(boolean isReset) {
		if (isReset) {
			loadAllDataPackageInfo();
			usePackageInfos.clear();
			for (AppInfo pi : allPackageInfos) {
				if ((pi.getPackageInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				} else {
					usePackageInfos.add(pi);
				}
			}
		}
		return usePackageInfos;
	}

	/**
	 * 通过该方法找到所有安装的软件
	 */
	public void loadAllDataPackageInfo() {
		List<PackageInfo> allInfos = packManager
				.getInstalledPackages(PackageManager.GET_ACTIVITIES
						| PackageManager.GET_UNINSTALLED_PACKAGES);
		allPackageInfos.clear();
		for (PackageInfo pi : allInfos) {
			allPackageInfos.add(new AppInfo(pi));
		}
	}

}
