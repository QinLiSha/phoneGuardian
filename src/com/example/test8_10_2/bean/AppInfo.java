package com.example.test8_10_2.bean;

import android.content.pm.PackageInfo;

public class AppInfo {

	private PackageInfo packageInfo;
	
	private boolean isDel;

	public AppInfo(PackageInfo packageInfo, boolean isDel) {
		super();
		this.packageInfo = packageInfo;
		this.isDel = isDel;
	}
	

	public AppInfo(PackageInfo packageInfo) {
		super();
		this.packageInfo = packageInfo;
	}


	public PackageInfo getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	public boolean isDel() {
		return isDel;
	}

	@Override
	public String toString() {
		return "AppInfo [packageInfo=" + packageInfo + ", isDel=" + isDel + "]";
	}


	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	
}
