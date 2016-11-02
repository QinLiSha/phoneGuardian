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
	 * ��ǰ�������ڵ���������
	 */
	private List<DataType> dataList = new ArrayList<DataType>();

	/**
	 * ���췽��
	 * 
	 * ���ز���ʱҪ�õ�LayoutInflater,�ڹ��췽���и�����ʼ��
	 * 
	 * @param context
	 */
	public BaseBaseAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * ��ȡ�����������������б�(List����)
	 * 
	 * @return
	 */
	public List<DataType> getDataList() {
		return dataList;
	}

	/**
	 * �������������������б�(List����),���һ������
	 * 
	 * @param dataType
	 */
	public void addDataToAdapter(DataType data) {
		dataList.add(data);
	}

	/**
	 * ���������������������б�(List����)������,Ϊָ������(��������ԭ����)
	 * 
	 * @param list
	 */

	public void setDataToAdapter(DataType data) {
		dataList.clear();
		dataList.add(data);
	}

	/**
	 * ���������������������б�(List����)������,Ϊָ������(��������ԭ����)
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
	 * ���б����һ����ʾ���������б����������Ļʱ�����б������ݱ仯������notifyDataSetChanged()����ʱ ������������
	 * 
	 * @param position
	 * @param converView
	 * @param parent
	 * @return
	 */
	public abstract View getItemView(int position, View convertView,
			ViewGroup parent);
}
