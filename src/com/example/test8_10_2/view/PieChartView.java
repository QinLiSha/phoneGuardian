package com.example.test8_10_2.view;


import java.util.Timer;
import java.util.TimerTask;

import com.example.test8_10_2.R;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @authorAdministrator
 * @version2016-8-22上午9:38:16
 */
public class PieChartView extends View {

	private Paint paint; // 画笔

	private RectF oval; // 椭圆形

	private int baseColor; // 基本的背景颜色

	private int phoneColor; // 手机内存的背景颜色

	private int sdCardColr; // 内存卡的背景颜色

	private float pieChartPhoneAngle = 0; // 手机所占的角度

	private float pieChartSDCardAngle = 0; // SD所占的角度

	private float proprotionPhone = 0; // 手机内存所占比例

	private float proprotionsdCard = 0; // sdCard所占内存比例

	/** 构造方法 */
	public PieChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		baseColor = context.getResources().getColor(R.color.piechar_base);
		phoneColor = context.getResources().getColor(R.color.piechar_phone);
		sdCardColr = context.getResources().getColor(R.color.piechar_sdcard);
	}

	public void setProprotionWidthAnim(float f1, float f2) {
		proprotionPhone = f1;
		proprotionsdCard = f2;
		final float phoneTargetAngle = proprotionPhone * 360;
		final float sdCardTargetAngle = proprotionsdCard * 360;
		final Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				pieChartPhoneAngle += 4;
				pieChartSDCardAngle += 4;
				
				postInvalidate();//刷新角度
				if (pieChartPhoneAngle >= phoneTargetAngle) {
					pieChartPhoneAngle = phoneTargetAngle;
				}
				
				if (pieChartSDCardAngle >= sdCardTargetAngle) {
					pieChartSDCardAngle = sdCardTargetAngle;
				}
				
				if (pieChartPhoneAngle >= phoneTargetAngle
						&& pieChartSDCardAngle >= sdCardTargetAngle) {
					timer.cancel();
				}
			}
		};
		timer.schedule(task, 26, 26);
	}

	/**
	 * 测量控件的大小宽度
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		oval = new RectF(0, 0, width, height);
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setAntiAlias(true);

		paint.setColor(baseColor);
		canvas.drawArc(oval, -90, 360, true, paint);

		paint.setColor(phoneColor);
		canvas.drawArc(oval, -90, pieChartPhoneAngle, true, paint);

		paint.setColor(sdCardColr);
		canvas.drawArc(oval, -90, pieChartSDCardAngle, true, paint);

	}
}
