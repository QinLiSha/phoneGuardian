package com.example.test8_10_2.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 用来承载ViewPager的每一个View
 * 
 * @author Administrator
 * 
 */
public class MyPagerAdapter extends PagerAdapter {//pager:呼叫机


	private Context context;
	private ArrayList<View> arrayList = new ArrayList<View>();// 存储的是一个View，也就是ViewPager每个页面显示的图片

	

	public MyPagerAdapter(Context context) {

		super();
		this.context = context;
	}

	/**
	 * 添加页面
	 * @param view
	 */
	public void addToAdapterView(View view){
		arrayList.add(view);
	}
	
	/**
	 * 返回ViewPager的页面数量
	 */
	@Override
	public int getCount() {
		return arrayList.size();
	}

	/**
	 * 判断是否前后两个页面是否是同一个页面
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	/**
	 * 如果超出ViewPager缓存页面,将页面销毁
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeViewInLayout(arrayList.get(position));
	}
	/**
	 * 将要缓存的View添加到集合中
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		container.addView(arrayList.get(position));
		return arrayList.get(position);
	}
	
}


