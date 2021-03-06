package com.example.test8_10_2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseBaseAdapter;
import com.example.test8_10_2.bean.AppInfo;

public class AppAdapter extends BaseBaseAdapter<AppInfo> {

	private LayoutInflater layoutInflater;
	private Context context;

	public AppAdapter(Context context) {
		super(context);
		this.context = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.layout_softshow_item,
					null);
			vh.tv_app_lable_softshow = (TextView) convertView
					.findViewById(R.id.tv_app_lable_softshow);
			vh.tv_app_packageName_softshow = (TextView) convertView
					.findViewById(R.id.tv_app_packageName_softshow);
			vh.tv_app_version_softshow = (TextView) convertView
					.findViewById(R.id.tv_app_version_softshow);
			vh.cb_app_softshow = (CheckBox) convertView
					.findViewById(R.id.cb_app_softshow);

			vh.iv_app_icon_softshow = (ImageView) convertView
					.findViewById(R.id.iv_app_icon_softshow);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		String title = getItem(position).getPackageInfo().applicationInfo
				.loadLabel(context.getPackageManager()).toString();
		String packageName = getItem(position).getPackageInfo().packageName;
		String version = getItem(position).getPackageInfo().versionName;
		//之前下面的2行没有写就不能进行卸载
		vh.cb_app_softshow.setTag(position);
		vh.cb_app_softshow.setOnCheckedChangeListener(onCheckedChangeListener);
		
		boolean isDel = getItem(position).isDel();
		Bitmap bitmap = ((BitmapDrawable) getItem(position).getPackageInfo().applicationInfo
				.loadIcon(context.getPackageManager())).getBitmap();
		vh.tv_app_lable_softshow.setText(title);
		vh.tv_app_packageName_softshow.setText(packageName);
		vh.tv_app_version_softshow.setText(version);
		vh.iv_app_icon_softshow.setImageBitmap(bitmap);
		vh.cb_app_softshow.setChecked(isDel);
		return convertView;
	}

	class ViewHolder {
		TextView tv_app_lable_softshow, tv_app_packageName_softshow,
				tv_app_version_softshow;
		ImageView iv_app_icon_softshow;
		CheckBox cb_app_softshow;
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			getDataList().get(position).setDel(isChecked);

		}
	};

}
