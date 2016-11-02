package com.example.test8_10_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseBaseAdapter;
import com.example.test8_10_2.bean.PhoneInfo;

public class PhoneMgrAdapter extends BaseBaseAdapter<PhoneInfo> {

	LayoutInflater layoutInflater;

	public PhoneMgrAdapter(Context context) {
		super(context);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = layoutInflater
					.inflate(R.layout.layout_phonemgr, null);
			vh.im_mgr_item = (ImageView) convertView
					.findViewById(R.id.im_mgr_item);
			vh.tv_mgr_title_item = (TextView) convertView
					.findViewById(R.id.tv_mgr_title_item);
			vh.tv_mgr_content_item = (TextView) convertView
					.findViewById(R.id.tv_mgr_content_item);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		switch (position % 3) {
		case 0:
			vh.im_mgr_item
					.setBackgroundResource(R.drawable.notification_information_progress_red);
			break;
		case 1:
			vh.im_mgr_item
					.setBackgroundResource(R.drawable.notification_information_progress_green);
			break;
		case 2:
		default:
			vh.im_mgr_item
					.setBackgroundResource(R.drawable.notification_information_progress_yellow);
			break;
		}
		vh.im_mgr_item.setImageDrawable(getItem(position).getIcon());
		vh.tv_mgr_content_item.setText(getItem(position).getContent());
		vh.tv_mgr_title_item.setText(getItem(position).getTitle());
		return convertView;
	}

	class ViewHolder {
		ImageView im_mgr_item;
		TextView tv_mgr_title_item, tv_mgr_content_item;
	}
}
