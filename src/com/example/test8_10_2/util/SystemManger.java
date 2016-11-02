package com.example.test8_10_2.util;

import android.os.Build;

public class SystemManger {
	/**
	 * ���һ���豸��Ʒ��
	 * 
	 * @return
	 */
	public static String getPhoneName() {
		return Build.BRAND;
	}

	/**
	 * ����豸�ͺ�
	 * 
	 * @return
	 */
	public static String getSystemPhoneModel() {
		return Build.VERSION.RELEASE;
	}
	
	/**
	 * ����������豸�ͺ�
	 * @return
	 */
	public static String getPhoneModel(){
		return Build.MODEL+"Android"+getSystemPhoneModel();
	}
	
}

























