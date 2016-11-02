package com.example.test8_10_2.activity;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.MemoryManager;
import com.example.test8_10_2.view.PieChartView;

public class SoftMgrActivity extends BaseActivity {

	private PieChartView pieChart;
	private ProgressBar pb_soft_phone;
	private ProgressBar pb_soft_sdCard;
	private TextView tv_soft_phoneSize;
	private TextView tv_soft_sdCardSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_mgr);
		initActionBar("��������", R.drawable.btn_homeasup_default, -1,
				onClickListener);
		initView();
		initLoadData();
	}

	private void initLoadData() {
		// ϵͳ�������ڴ��С
		long phoneSelfTotal = MemoryManager.getPhoneSelfSize();
		long phoneSelfUnuse = MemoryManager.getPhoneSelfFreeSize();
		long phoneSelfUse = phoneSelfTotal - phoneSelfUnuse;// �ֻ�ʹ���˵��ڴ�
		// �ֻ������ڴ��С
		long phoneSelfSDCardTotal = MemoryManager.getPhoneSelfSDCardSize();
		long phoneSelfSDCardUnuse = MemoryManager.getPhoneSelfSDCardFreeSize();
		long phoneSelfSDCardUse = phoneSelfSDCardTotal - phoneSelfSDCardUnuse;
		// �ֻ������ڴ��С
		long phoneOutofSDCardTotal = MemoryManager
				.getPhoneOutSDCardSize(getApplicationContext());
		long phoneOutofSDCardUnuse = MemoryManager
				.getPhoneOutSDCardFreeSize(getApplicationContext());
		long phoneOutofSDCardUse = phoneOutofSDCardTotal
				- phoneOutofSDCardUnuse;
		// �ֻ��ڴ���ܴ�С
		long phoneTotalMemory = phoneSelfTotal + phoneSelfSDCardTotal
				+ phoneOutofSDCardTotal;

		// �����ֻ��ڴ���ռ�ı���
		float phoneSpaceF = (float) (phoneSelfTotal + phoneSelfSDCardTotal)
				/ phoneTotalMemory;
		// SD��ռ�ı���
		float sdCardSpaceF = (float) phoneOutofSDCardTotal / phoneTotalMemory;
		DecimalFormat df = new DecimalFormat("#.00");
		phoneSpaceF = Float.parseFloat(df.format(phoneSpaceF));
		sdCardSpaceF = Float.parseFloat(df.format(sdCardSpaceF));

		pieChart.setProprotionWidthAnim(phoneSpaceF, sdCardSpaceF);
		// �ֻ����������ڴ�
		long phoneTotalSpace = phoneSelfTotal + phoneSelfSDCardTotal;
		// �ֻ����ÿ���
		long phoneUnuseSpace = phoneSelfUnuse + phoneSelfSDCardUnuse;
		// �ֻ������Ѿ���
		long phoneUseSpace = phoneTotalSpace - phoneUnuseSpace;

		tv_soft_phoneSize.setText("����:"
				+ CommonUtil.getFileInfo(phoneUnuseSpace) + "/"
				+ CommonUtil.getFileInfo(phoneTotalSpace));
		tv_soft_sdCardSize.setText("����:"
				+ CommonUtil.getFileInfo(phoneOutofSDCardUnuse) + "/"
				+ CommonUtil.getFileInfo(phoneOutofSDCardTotal));

		// pb_soft_phone.setMax((int) phoneTotalSpace / 1024);
		// pb_soft_phone.setProgress((int) phoneUseSpace / 1024);
		// pb_soft_sdCard.setMax((int) phoneOutofSDCardTotal / 1024);
		// pb_soft_sdCard.setProgress((int) (phoneOutofSDCardUse / 1024));

		pb_soft_phone.setMax(1000);
		pb_soft_phone
				.setProgress((int) (phoneUseSpace * 1000 / phoneTotalSpace));
	
		if (phoneOutofSDCardTotal == 0) {
			pb_soft_sdCard.setProgress(0);
		} else {
			pb_soft_sdCard.setMax(1000);
			pb_soft_sdCard
					.setProgress((int) (phoneOutofSDCardUse * 1000 / phoneOutofSDCardTotal));

		}
	}

	/**
	 * �ؼ��ĳ�ʼ��
	 */
	private void initView() {
		pieChart = (PieChartView) findViewById(R.id.pieChart);
		pb_soft_phone = (ProgressBar) findViewById(R.id.pb_soft_phone);
		pb_soft_sdCard = (ProgressBar) findViewById(R.id.pb_soft_sdCard);
		tv_soft_phoneSize = (TextView) findViewById(R.id.tv_soft_phoneSize);
		tv_soft_sdCardSize = (TextView) findViewById(R.id.tv_soft_sdCardSize);

	}

	/**
	 * ��Ŀ�ĵ������
	 */
	public void hitListitem(View view) {
		int viewId = view.getId();
		switch (viewId) {
		case R.id.soft_classlist_all:
		case R.id.soft_classlist_system:
		case R.id.soft_classlist_user:
			Bundle bundle = new Bundle();
			bundle.putInt("viewId", viewId);
			startActivity(SoftMgrShowActivity.class, bundle);
			break;

		}
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				startActivity(HomeActivity.class);
				finish();
				break;

			default:
				break;
			}

		}
	};

}