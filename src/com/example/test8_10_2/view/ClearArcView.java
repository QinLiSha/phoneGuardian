package com.example.test8_10_2.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ClearArcView extends View {

	private Paint paint = new Paint();// ����

	private RectF oval;// ��Բ��

	private int arcColor = 0xFFFF8C00;// ���ʵ���ɫ

	private int start_angle = -90;// ��ʼ�ĽǶ�

	private int sweep_angle = -90;// �����ĽǶ�

	private int state = 0;// 0������� , 1����ǰ��

	private int[] back = new int[] { -6, -6, -10, -10, -12, -12 };

	private int backIndex;

	private int goon[] = new int[] { 12, 12, 12, 10, 10, 9, 9, 9, 8, 6,5};

	private int goonIndex;

	private boolean isRunning = false;

	public ClearArcView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAngle(360);
	}

	public void setAngle(int angle) {
		sweep_angle = angle;
		postInvalidate();// ˢ��ҳ��

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
		setMeasuredDimension(width, height);// ��ȸ߶����ø�������
	}

	/**
	 * ��ʼ�ڻ����ϻ�ͼ
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		paint.setColor(arcColor);
		paint.setAntiAlias(true);// �����ȥ��
		canvas.drawArc(oval, start_angle, sweep_angle, true, paint);

	}

	public void setAngleWidthAnim(final int angle) {
		if (isRunning) {
			return;
		}
		isRunning = true;
		state = 0;
		final Timer timer = new Timer();// ��ʱ��
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				switch (state) {
				case 0:
					sweep_angle += back[backIndex++];//360+=-6   354+=-6  348+=-6;
					if (backIndex >= back.length) {
						backIndex = back.length - 1;
					}
					postInvalidate();
					if (sweep_angle <= 0) {
						sweep_angle = 0;
						state = 1;
						backIndex = 0;
					}
					break;
				case 1:
					sweep_angle += goon[goonIndex++];
					if (goonIndex >= goon.length) {
						goonIndex = goon.length - 1;
					}
					postInvalidate();
					if (sweep_angle >= angle) {
						sweep_angle = angle;
						goonIndex = 0;
						isRunning = false;
						timer.cancel();
					}
					break;
				}
			}
		};
		timer.schedule(task, 50, 50);
	}
}
