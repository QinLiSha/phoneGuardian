package com.example.test8_10_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseBaseAdapter;
import com.example.test8_10_2.bean.ClearBean;
import com.example.test8_10_2.util.CommonUtil;

public class ClearMgrAdapter extends BaseBaseAdapter<ClearBean> {

	LayoutInflater layoutInflater;

	public ClearMgrAdapter(Context context) {
		super(context);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {

		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.layout_clear_item,
					null);
			vh.cb_app_clear_mrg = (CheckBox) convertView
					.findViewById(R.id.cb_app_clear_mrg);
			vh.iv_app_icon_clear = (ImageView) convertView
					.findViewById(R.id.iv_app_icon_clear);
			vh.tv_app_lable_clear = (TextView) convertView
					.findViewById(R.id.tv_app_lable_clear);
			vh.tv_app_size = (TextView) convertView
					.findViewById(R.id.tv_app_size);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.tv_app_lable_clear.setText(getItem(position).getSoftChinesename());
		vh.iv_app_icon_clear.setImageDrawable(getItem(position).getIcon());
		vh.tv_app_size.setText(CommonUtil.getFileInfo(getItem(position).getSize()));
		return convertView;
	}

	class ViewHolder {
		TextView tv_app_size, tv_app_lable_clear;
		ImageView iv_app_icon_clear;
		CheckBox cb_app_clear_mrg;
	}
}
