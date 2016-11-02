package com.example.test8_10_2.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String getStrTime(long filetime){
		if(filetime ==0){
			return "未知";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd hh:mm:ss");
		String ftime = format.format(new Date(filetime));
		return ftime;
	}
	
	private static DecimalFormat df = new DecimalFormat("#.00");
	
	//DecimalFormat起的作用是对数据的一种格式操作,他可以将数据格式化成括号内的格式
//	public static String getFileInfo(long file){
//		StringBuffer strBuff = new StringBuffer();
//		if(file<1024){
//			strBuff.append(file);
//			strBuff.append("B");
//		}else if (file<(1024*1024)) {
//			strBuff.append(df.format((double)file/1024));
//			strBuff.append("K");
//		}else if (file<(1024*1024*1024)) {
//			strBuff.append(df.format((double)file/(1024*1024)));
//			strBuff.append("M");
//		}else if (file<(1024*1024*1024*1024)) {
//			strBuff.append(df.format((double)file/(1024*1024*1024)));
//			strBuff.append("G");
//		}
//		
//		return strBuff.toString();
//	}
	
	public static String getFileInfo(long filesize) {
		StringBuffer mstrbuf = new StringBuffer();
		if (filesize < 1024) {
			mstrbuf.append(filesize);
			mstrbuf.append(" B");
		} else if (filesize < 1048576) {
			mstrbuf.append(df.format((double) filesize / 1024));
			mstrbuf.append(" K");
		} else if (filesize < 1073741824) {
			mstrbuf.append(df.format((double) filesize / 1048576));
			mstrbuf.append(" M");
		} else {
			mstrbuf.append(df.format((double) filesize / 1073741824));
			mstrbuf.append(" G");
		}
		return mstrbuf.toString();
	}
	
}
