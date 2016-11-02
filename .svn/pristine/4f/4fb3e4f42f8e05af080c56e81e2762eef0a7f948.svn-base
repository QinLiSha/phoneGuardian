package com.example.test8_10_2.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.test8_10_2.R;
import com.example.test8_10_2.bean.ClearBean;
import com.example.test8_10_2.bean.TableClass;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class ClearManager {

	private static final String FILE_NAME = "clearpath.db";
	private static final String FILE_PACKAG = "com.example.test8_10_2";
	private static final String FILE_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ FILE_PACKAG + "/" + FILE_NAME;

	public static ArrayList<ClearBean> softFileList;// 存放所用应用的list集合

	public static List<ClearBean> getPhoneSoftDetail(Context context) {
		if (softFileList == null) {
			softFileList = readClassListTable();
		}
		ArrayList<ClearBean> currentPhoneDetailList = new ArrayList<ClearBean>();// 存储手机上存在的所有应用
		for (ClearBean cb : softFileList) {
			File file = new File(cb.getFilepath());
			Drawable icon;
			if (file.exists()) {
				try {
					icon = context.getPackageManager().getApplicationIcon(
							cb.getApkname());
				} catch (NameNotFoundException e) {

					icon = context.getResources().getDrawable(
							R.drawable.ic_launcher1);
				}
				cb.setIcon(icon);
				currentPhoneDetailList.add(cb);
			}

		}
		return currentPhoneDetailList;
	}

	public static void readClearDB(InputStream path) throws Exception {
		File toFile = new File(FILE_PATH);
		if (toFile.exists()) {
			return;
		}
		BufferedInputStream bis = new BufferedInputStream(path);
		FileOutputStream fis = new FileOutputStream(toFile);
		BufferedOutputStream bos = new BufferedOutputStream(fis);
		int length = 0;
		byte[] b = new byte[5 * 1024];
		while ((length = bis.read(b)) != -1) {
			bos.write(b, 0, length);
		}
		bos.flush();
		bos.close();
		bis.close();
	}

	/**
	 * 查询的是classlist表中的数据
	 * 
	 * @return
	 */
	public static ArrayList<ClearBean> readClassListTable() {
		ArrayList<ClearBean> classInfoList = new ArrayList<ClearBean>();
		SQLiteDatabase dataBase = SQLiteDatabase.openOrCreateDatabase(
				FILE_PATH, null);
		String sql = "select*from softdetail";
		Cursor cursor = dataBase.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {

				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String softChinesename = cursor.getString(cursor
						.getColumnIndex("softChinesename"));
				String softEnglishname = cursor.getString(cursor
						.getColumnIndex("softEnglishname"));

				String apkname = cursor.getString(cursor
						.getColumnIndex("apkname"));

				String filepath = cursor.getString(cursor
						.getColumnIndex("filepath"));
				filepath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + filepath;// 获取存储卡中的文件路径

				ClearBean clear = new ClearBean(id, softChinesename,
						softEnglishname, apkname, filepath);
				classInfoList.add(clear);
			} while (cursor.moveToNext());
			cursor.close();
			dataBase.close();
		}

		return classInfoList;

	}

	/**
	 * 根据传递过来的表名来查表的数据
	 * 
	 * @param tableName
	 * @return
	 */
	public static List<TableClass> readTableClass(String tableName) {
		List<TableClass> list = new ArrayList<TableClass>();
		SQLiteDatabase dataBase = SQLiteDatabase.openOrCreateDatabase(
				FILE_PATH, null);
		String sql = "select * from " + tableName;
		Cursor cursor = dataBase.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				long number = cursor.getLong(cursor.getColumnIndex("number"));
				TableClass classInfo = new TableClass(name, number);
				list.add(classInfo);
			} while (cursor.moveToNext());
			cursor.close();
			dataBase.close();
		}
		return list;
	}

}
