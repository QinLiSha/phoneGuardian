package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.FileManager;
import com.example.test8_10_2.util.FileManager.SearchFileListener;
import com.example.test8_10_2.util.FileTypeUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author Administrator one of a kind 2016-8-23下午3:58:38
 */

public class FileMgrActivity extends BaseActivity {

	private FileManager fileManager;
	private Thread thread;

	private TextView tv_filesize;// 文件总大小

	private RelativeLayout rl_all;
	private RelativeLayout rl_doxc;
	private RelativeLayout rl_video;
	private RelativeLayout rl_music;
	private RelativeLayout rl_img;
	private RelativeLayout rl_apk;
	private RelativeLayout rl_zip;

	private TextView tv_size_all;
	private TextView tv_size_doxc;
	private TextView tv_size_video;
	private TextView tv_size_music;
	private TextView tv_size_img;
	private TextView tv_size_apk;
	private TextView tv_size_zip;

	private ProgressBar pb_all_loading;
	private ProgressBar pb_doxc_loading;
	private ProgressBar pb_video_loading;
	private ProgressBar pb_music_loading;
	private ProgressBar pb_img_loading;
	private ProgressBar pb_apk_loading;
	private ProgressBar pb_zip_loading;

	private ImageView iv_filemgr_all;
	private ImageView iv_filemgr_doxc;
	private ImageView iv_filemgr_music;
	private ImageView iv_filemgr_video;
	private ImageView iv_filemgr_img;
	private ImageView iv_filemgr_apk;
	private ImageView iv_filemgr_zip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_mgr);
		initActionBar("文件管理", R.drawable.btn_homeasup_default, -1,
				onClickListener);
		initUI();
		asyncTaskLoadData();
	}

	private void asyncTaskLoadData() {

		fileManager = FileManager.getFileManager();
		fileManager.setSearchFileListener(onclFileListener);
		thread = new Thread() {
			public void run() {
				fileManager.searchSDCardFile();
			};
		};
		thread.start();
	}

	@Override
	protected void myHandlerMessage(Message msg) {
		int id = msg.what;
		switch (msg.what) {
		case 1:
			String typeName = (String) msg.obj;
			tv_filesize.setText(CommonUtil.getFileInfo(fileManager
					.getAnyFileSize()));
			tv_size_all.setText(CommonUtil.getFileInfo(fileManager
					.getAnyFileSize()));
			if (typeName.equals(FileTypeUtil.TYPE_APK)) {
				tv_size_apk.setText(CommonUtil.getFileInfo(fileManager
						.getApkFileSize()));
			} else if (typeName.equals(FileTypeUtil.TYPE_AUDIO)) {
				tv_size_music.setText(CommonUtil.getFileInfo(fileManager
						.getAudioFileSize()));
			} else if (typeName.equals(FileTypeUtil.TYPE_TXT)) {
				tv_size_doxc.setText(CommonUtil.getFileInfo(fileManager
						.getTxtFileSize()));
			} else if (typeName.equals(FileTypeUtil.TYPE_IMAGE)) {
				tv_size_img.setText(CommonUtil.getFileInfo(fileManager
						.getImageFileSize()));
			} else if (typeName.equals(FileTypeUtil.TYPE_VIDEO)) {
				tv_size_video.setText(CommonUtil.getFileInfo(fileManager
						.getVideoFileSize()));
			} else if (typeName.equals(FileTypeUtil.TYPE_ZIP)) {
				tv_size_zip.setText(CommonUtil.getFileInfo(fileManager
						.getZipFileSize()));
			}
			break;
		case 2:
			tv_filesize.setText(CommonUtil.getFileInfo(fileManager
					.getAnyFileSize()));
			tv_size_all.setText(CommonUtil.getFileInfo(fileManager
					.getAnyFileSize()));
			tv_size_apk.setText(CommonUtil.getFileInfo(fileManager
					.getApkFileSize()));
			tv_size_music.setText(CommonUtil.getFileInfo(fileManager
					.getAudioFileSize()));
			tv_size_img.setText(CommonUtil.getFileInfo(fileManager
					.getImageFileSize()));
			tv_size_video.setText(CommonUtil.getFileInfo(fileManager
					.getVideoFileSize()));
			tv_size_zip.setText(CommonUtil.getFileInfo(fileManager
					.getZipFileSize()));

			iv_filemgr_all.setVisibility(View.VISIBLE);
			iv_filemgr_doxc.setVisibility(View.VISIBLE);
			iv_filemgr_video.setVisibility(View.VISIBLE);
			iv_filemgr_apk.setVisibility(View.VISIBLE);
			iv_filemgr_img.setVisibility(View.VISIBLE);
			iv_filemgr_music.setVisibility(View.VISIBLE);
			iv_filemgr_zip.setVisibility(View.VISIBLE);

			pb_all_loading.setVisibility(View.INVISIBLE);
			pb_doxc_loading.setVisibility(View.INVISIBLE);
			pb_video_loading.setVisibility(View.INVISIBLE);
			pb_music_loading.setVisibility(View.INVISIBLE);
			pb_img_loading.setVisibility(View.INVISIBLE);
			pb_apk_loading.setVisibility(View.INVISIBLE);
			pb_zip_loading.setVisibility(View.INVISIBLE);

			rl_all.setOnClickListener(onClickListener);
			rl_doxc.setOnClickListener(onClickListener);
			rl_video.setOnClickListener(onClickListener);
			rl_apk.setOnClickListener(onClickListener);
			rl_img.setOnClickListener(onClickListener);
			rl_music.setOnClickListener(onClickListener);
			rl_zip.setOnClickListener(onClickListener);

			break;

		default:
			break;
		}
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {

		tv_filesize = (TextView) findViewById(R.id.tv_filesize);
		// RelativeLayout
		rl_all = (RelativeLayout) findViewById(R.id.rl_all);
		rl_doxc = (RelativeLayout) findViewById(R.id.rl_doxc);
		rl_video = (RelativeLayout) findViewById(R.id.rl_video);
		rl_music = (RelativeLayout) findViewById(R.id.rl_music);
		rl_img = (RelativeLayout) findViewById(R.id.rl_img);
		rl_apk = (RelativeLayout) findViewById(R.id.rl_apk);
		rl_zip = (RelativeLayout) findViewById(R.id.rl_zip);
		// TextView
		tv_size_all = (TextView) findViewById(R.id.tv_filemgr_all);
		tv_size_doxc = (TextView) findViewById(R.id.tv_filemgr_doxc);
		tv_size_video = (TextView) findViewById(R.id.tv_filemgr_video);
		tv_size_music = (TextView) findViewById(R.id.tv_filemgr_music);
		tv_size_img = (TextView) findViewById(R.id.tv_filemgr_img);
		tv_size_apk = (TextView) findViewById(R.id.tv_filemgr_apk);
		tv_size_zip = (TextView) findViewById(R.id.tv_filemgr_zip);
		// ProgressBar进度条
		pb_all_loading = (ProgressBar) findViewById(R.id.pb_all_loading);
		pb_doxc_loading = (ProgressBar) findViewById(R.id.pb_doxc_loading);
		pb_video_loading = (ProgressBar) findViewById(R.id.pb_video_loading);
		pb_music_loading = (ProgressBar) findViewById(R.id.pb_music_loading);
		pb_img_loading = (ProgressBar) findViewById(R.id.pb_img_loading);
		pb_apk_loading = (ProgressBar) findViewById(R.id.pb_apk_loading);
		pb_zip_loading = (ProgressBar) findViewById(R.id.pb_zip_loading);

		// ImageView图片
		iv_filemgr_all = (ImageView) findViewById(R.id.iv_filemgr_all);
		iv_filemgr_doxc = (ImageView) findViewById(R.id.iv_filemgr_doxc);
		iv_filemgr_video = (ImageView) findViewById(R.id.iv_filemgr_video);
		iv_filemgr_music = (ImageView) findViewById(R.id.iv_filemgr_music);
		iv_filemgr_img = (ImageView) findViewById(R.id.iv_filemgr_img);
		iv_filemgr_apk = (ImageView) findViewById(R.id.iv_filemgr_apk);
		iv_filemgr_zip = (ImageView) findViewById(R.id.iv_filemgr_zip);

	}

	private FileManager.SearchFileListener onclFileListener = new SearchFileListener() {

		@Override
		public void searching(String typeName) {
			Message msg = handler.obtainMessage();
			msg.what = 1;
			msg.obj = typeName;
			handler.sendMessage(msg);
		}

		@Override
		public void end(boolean isExceptionEnd) {
			handler.sendEmptyMessage(2);
		}
	};

	protected void onDestroy() {
		super.onDestroy();//需要加上不然按返回键时会出问题
		fileManager.setStopSearch(true);
		// 终端线程释放资源
		thread.interrupt();
		thread = null;

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int viewID = v.getId();
			switch (viewID) {
			case R.id.iv_left:
				startActivity(HomeActivity.class);
//				finish();//如果不写finish()的话会一直加载
				break;
			case R.id.rl_all:
			case R.id.rl_apk:
			case R.id.rl_doxc:
			case R.id.rl_img:
			case R.id.rl_music:
			case R.id.rl_video:
			case R.id.rl_zip:
				Intent intent = new Intent(FileMgrActivity.this,
						FileMgrShowActivity.class);
				intent.putExtra("id", viewID);
				startActivityForResult(intent, 1);//---当页面有操作时会刷新页面
				break;
			case R.id.btn_show_progress_soft:
				break;
			}
		}
	};


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==1) {
			tv_filesize.setText(CommonUtil.getFileInfo(fileManager
					.getAnyFileSize()));
			tv_size_all.setText(CommonUtil.getFileInfo(fileManager
					.getAnyFileSize()));
			tv_size_apk.setText(CommonUtil.getFileInfo(fileManager
					.getApkFileSize()));
			tv_size_doxc.setText(CommonUtil.getFileInfo(fileManager
					.getTxtFileSize()));
			tv_size_music.setText(CommonUtil.getFileInfo(fileManager
					.getAudioFileSize()));
			tv_size_img.setText(CommonUtil.getFileInfo(fileManager
					.getImageFileSize()));
			tv_size_video.setText(CommonUtil.getFileInfo(fileManager
					.getVideoFileSize()));
			tv_size_zip.setText(CommonUtil.getFileInfo(fileManager
					.getZipFileSize()));
		}
	};
	


}
