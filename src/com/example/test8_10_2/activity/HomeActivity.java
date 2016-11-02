package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.util.AppInfoManager;
import com.example.test8_10_2.util.MemoryManager;
import com.example.test8_10_2.view.ClearArcView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends BaseActivity {
	private ClearArcView clearView;
	private TextView tv_score;
	private ImageView iv_home_score;
	private TextView tv_speed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);// ����һ��ҳ��
		initActionBar("��ȫ��ʦ��ҳ", R.drawable.ic_launcher1,
				R.drawable.ic_child_configs, onClickListener);

		// ��Ϊ��װ��Activity,���Կ���ʹ�������һ�д�������������ļ���
		// actionBarView = (ActionBarView) findViewById(R.id.actionBarView);
		// actionBarView.initActionBar("��ҳ", R.drawable.ic_launcher1,
		// R.drawable.ic_child_configs, onClickListener);

		clearView = (ClearArcView) findViewById(R.id.clearView); // Ӧ���������,����Ļ�Ҫ��һ��.....
		tv_score = (TextView) findViewById(R.id.tv_score);
		initMemoryData();
		initView();
	}

	private void initView() {
		clearView = (ClearArcView) findViewById(R.id.clearView);
		tv_score = (TextView) findViewById(R.id.tv_score);
		tv_speed = (TextView) findViewById(R.id.tv_speed);
		iv_home_score = (ImageView) findViewById(R.id.iv_home_score);
		iv_home_score.setOnClickListener(onClickListener);
		tv_speed.setOnClickListener(onClickListener);
	}

	private void initMemoryData() {
		float totalMemory = MemoryManager.getTotalMemoryPhoneRam();// �ܵ�ϵͳ�ڴ�
		float freeMemory = MemoryManager.getFreeMemoryRam(HomeActivity.this);// �����ڴ�Ĵ�С
		float useMemory = totalMemory - freeMemory;
		float usePersent = useMemory / totalMemory;
		int user100 = (int) (usePersent * 100);
		tv_score.setText(user100 + "");
		int useAngle = (int) (usePersent * 360);
		clearView.setAngleWidthAnim(useAngle);

	}

	private OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.iv_home_score:
			case R.id.tv_speed:
				AppInfoManager.getAppInfoManager(HomeActivity.this)
						.KillAllProcess();
				initMemoryData();
				break;
			case R.id.iv_left:
				startActivity(AboutActivity.class);// �����˷�װȡ���������2��
				// Intent intent = new
				// Intent(HomeActivity.this,AboutActivity.class);
				// startActivity(intent);
				break;

			case R.id.iv_right:
				startActivity(SettingActivity.class);
				// Intent intent1 = new
				// Intent(HomeActivity.this,SettingActivity.class);
				// startActivity(intent1);
				break;

			default:
				break;
			}
		}
	};

	/**
	 * ����¼�
	 * 
	 * @param view
	 */
	public void hitHomeitem(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.tv_rocket: // rocket����:/'rakit/���,�ɳ�,Ѹ������ "�ֻ�����"
			startActivity(SpeedUpActivity.class);
			break;
		case R.id.tv_softmgr:
			startActivity(SoftMgrActivity.class);
			break;
		case R.id.tv_phonemgr:
			startActivity(PhoneMgrActivity.class);
			break;
		case R.id.tv_telmgr:
			startActivity(TelMgrActivity.class);
			break;
		case R.id.tv_filemgr:
			startActivity(FileMgrActivity.class);
			break;
		case R.id.tv_sdclean:
			startActivity(ClearActivity.class);
			break;
		}
	}
}
