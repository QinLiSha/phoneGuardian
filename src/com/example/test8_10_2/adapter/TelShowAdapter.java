package com.example.test8_10_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseBaseAdapter;
import com.example.test8_10_2.bean.TableClass;

public class TelShowAdapter extends BaseBaseAdapter<TableClass> {
	private LayoutInflater layoutInflater;

	public TelShowAdapter(Context context) {
		super(context);

		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {

		ViewHolder vh;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.layout_tel_listitem,
					null);
			vh = new ViewHolder();
			vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			vh.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
			convertView.setTag(vh);
			
		}else {
			vh = (ViewHolder) convertView.getTag();
			
		}
		vh.tv_name.setText(getItem(position).getName());
		vh.tv_num.setText(getItem(position).getNumber()+"");
		return convertView;
	}

	class ViewHolder{
		TextView tv_name,tv_num;
	}
}
