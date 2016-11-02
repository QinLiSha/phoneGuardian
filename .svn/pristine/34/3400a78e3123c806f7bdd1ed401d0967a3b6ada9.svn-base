package com.example.test8_10_2.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.renderscript.Element.DataType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseBaseAdapter<DataType> extends BaseAdapter {

	protected Context context;
	private LayoutInflater layoutInflater;
	/**
	 * 当前适配器内的所用数据
	 */
	private List<DataType> dataList = new ArrayList<DataType>();

	/**
	 * 构造方法
	 * 
	 * 加载布局时要用到LayoutInflater,在构造方法中给定初始化
	 * 
	 * @param context
	 */
	public BaseBaseAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 获取适配器内所有数据列表(List集合)
	 * 
	 * @return
	 */
	public List<DataType> getDataList() {
		return dataList;
	}

	/**
	 * 向适配器内所用数据列表(List集合),添加一个数据
	 * 
	 * @param dataType
	 */
	public void addDataToAdapter(DataType data) {
		dataList.add(data);
	}

	/**
	 * 设置适配器内所用数据列表(List集合)内数据,为指定数据(将先清理原数据)
	 * 
	 * @param list
	 */

	public void setDataToAdapter(DataType data) {
		dataList.clear();
		dataList.add(data);
	}

	/**
	 * 设置适配器内所用数据列表(List集合)内数据,为指定数据(将先清理原数据)
	 * 
	 * @param datas
	 */
	public void setDataToAdapter(List<DataType> datas) {

		dataList.clear();
		dataList.addAll(datas);
	}

	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
	}

	@Override
	public DataType getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getItemView(position, convertView, parent);
	}

	/**
	 * 当列表项第一次显示出来，当列表项滑出滑进屏幕时，当列表项数据变化，调用notifyDataSetChanged()方法时 ，都将来调用
	 * 
	 * @param position
	 * @param converView
	 * @param parent
	 * @return
	 */
	public abstract View getItemView(int position, View convertView,
			ViewGroup parent);
}
