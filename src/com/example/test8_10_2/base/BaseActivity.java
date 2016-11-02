package com.example.test8_10_2.base;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.test8_10_2.R;
import com.example.test8_10_2.view.ActionBarView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * BaseActivity������Ϊ����Activity�Ļ�����
 * 
 * @author Administrator
 * 
 */
public class BaseActivity extends Activity {

	/**
	 * ��ǰ�ļ�������װ���е�Activity
	 */
	private static ArrayList<BaseActivity> onLineActivityList = new ArrayList<BaseActivity>();

	public void finishAllActivity() {
		Iterator<BaseActivity> iterator = onLineActivityList.iterator();
		while (iterator.hasNext()) {
			iterator.next().finish();

		}
	}

	/**
	 * ��װһ����ʼ��ActionBarView
	 * 
	 * @param title
	 * @param leftId
	 * @param rightId
	 * @param onClickListener
	 */
	protected void initActionBar(String title, int leftId, int rightId,
			OnClickListener onClickListener) {
		ActionBarView actionBarView = (ActionBarView) findViewById(R.id.actionBarView);
		actionBarView.initActionBar(title, leftId, rightId, onClickListener);
	}

	/**
	 * ��װһ����ת��Activity
	 * 
	 * @param targetClass
	 * 			Ŀ��ҳ�� 	 Class<?>
	 * 			Activity Context
	 * 			Class  	 .class
	 */
	protected void startActivity(Class<?> targetClass) {
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
	}

	/**
	 * @param targetClass
	 * 	��һ��Ŀ����һ��Target class
	 * @param bundle
	 *   ͨ��Bundle���ݲ���
	 */
	protected void startActivity(Class<?> targetClass, Bundle bundle) {
		Intent intent = new Intent(this, targetClass);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	protected void showToast(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onLineActivityList.add(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		onLineActivityList.remove(this);
	}
	
	protected Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg){
			myHandlerMessage(msg);
		};
	};	
	
	protected void myHandlerMessage(Message msg){
		
	}

}
