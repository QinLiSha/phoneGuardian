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
 * @version2016-8-22����9:38:16
 */
public class PieChartView extends View {

	private Paint paint; // ����

	private RectF oval; // ��Բ��

	private int baseColor; // �����ı�����ɫ

	private int phoneColor; // �ֻ��ڴ�ı�����ɫ

	private int sdCardColr; // �ڴ濨�ı�����ɫ

	private float pieChartPhoneAngle = 0; // �ֻ���ռ�ĽǶ�

	private float pieChartSDCardAngle = 0; // SD��ռ�ĽǶ�

	private float proprotionPhone = 0; // �ֻ��ڴ���ռ����

	private float proprotionsdCard = 0; // sdCard��ռ�ڴ����

	/** ���췽�� */
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
				
				postInvalidate();//ˢ�½Ƕ�
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
	 * �����ؼ��Ĵ�С���
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
