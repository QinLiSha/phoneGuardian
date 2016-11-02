package com.example.test8_10_2.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseBaseAdapter;
import com.example.test8_10_2.bean.FileInfo;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.FileTypeUtil;

public class FileAdapter extends BaseBaseAdapter<FileInfo> {

	private LayoutInflater layoutInflater;

	public FileAdapter(Context context) {
		super(context);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.layout_file_mgr_item,
					null);
			vh.cb_file_show = (CheckBox) convertView
					.findViewById(R.id.cb_file_show);
			vh.iv_file_show = (ImageView) convertView
					.findViewById(R.id.iv_file_show);
			vh.tv_filename_show = (TextView) convertView
					.findViewById(R.id.tv_filename_show);
			vh.tv_filetime_show = (TextView) convertView
					.findViewById(R.id.tv_filetime_show);
			vh.tv_filesize_show = (TextView) convertView
					.findViewById(R.id.tv_filesize_show);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		FileInfo fileInfo = getItem(position);
		String name = fileInfo.getFile().getName();// 文件名称
		String size = CommonUtil.getFileInfo(fileInfo.getFile().length());// 文件大小
		String lastTime = CommonUtil.getStrTime(fileInfo.getFile()
				.lastModified());// 文件最后访问时间
		boolean isCheck = fileInfo.isSelected();
		vh.cb_file_show.setChecked(isCheck);
		vh.cb_file_show.setTag(position);
		vh.cb_file_show.setOnCheckedChangeListener(onCheckedChangeListener);
		
		vh.tv_filename_show.setText(name);
		vh.tv_filesize_show.setText(lastTime);
		vh.tv_filetime_show.setText(lastTime);
		Bitmap bitmap = getBitMap(fileInfo);
		vh.iv_file_show.setImageBitmap(bitmap);
		return convertView;
	}

	public Bitmap getBitMap(FileInfo fileInfo) {
		Bitmap bitmap = null;
		if (fileInfo.getFiletype().equals(FileTypeUtil.TYPE_IMAGE)) {
			bitmap = BitmapFactory.decodeFile(fileInfo.getFile().getPath());
			// BitmapFactory.decodeFile可以将一个图片路径转化成BitMap对象
			return bitmap;
		}
		Resources resources = context.getResources();
		int icon = resources.getIdentifier(fileInfo.getIcon(), "drawable",
				context.getPackageName());
		if (icon <= 0) {
			icon = R.drawable.icon_file;
		}
		bitmap = BitmapFactory.decodeResource(context.getResources(), icon);
		return bitmap;
	}

	class ViewHolder {
		CheckBox cb_file_show;
		ImageView iv_file_show;
		TextView tv_filename_show, tv_filetime_show, tv_filesize_show;
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			getItem(position).setIsSelected(isChecked);
		}
	};
}
