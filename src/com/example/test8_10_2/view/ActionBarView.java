package com.example.test8_10_2.view;

import com.example.test8_10_2.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarView extends LinearLayout {

	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_title;

	public ActionBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.layout_actionbar, this);
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		tv_title = (TextView) findViewById(R.id.tv_title);

	}

	public void initActionBar(String title, int leftId,int rightId,
			OnClickListener onClickListener) {

		tv_title.setText(title);
		
		if(leftId != -1){
			iv_left.setBackgroundResource(leftId);
			iv_left.setOnClickListener(onClickListener);
			
		}else {
			iv_left.setVisibility(View.INVISIBLE);
		}
		if(rightId != -1){
			iv_right.setBackgroundResource(rightId);
			iv_right.setOnClickListener(onClickListener);
			
		}else {
			iv_right.setVisibility(View.INVISIBLE);
		}
	}

}
