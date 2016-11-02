package com.example.test8_10_2.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.FileAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.FileInfo;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.FileManager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FileMgrShowActivity extends BaseActivity {
	private int id;
	private String title;
	private TextView tv_file_num, tv_file_size;
	private ListView lv_file_list;
	private Button btn_file_clear;

	private long fileSize;
	private long fileNum;
	private List<FileInfo> fileInfo;
	private FileAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_mgr_show);
		id = getIntent().getIntExtra("id", R.id.rl_all);
		initInfo(id);
		initActionBar(title, R.drawable.btn_homeasup_default, -1, null);
		initUI();
		adapter = new FileAdapter(this);
		lv_file_list.setAdapter(adapter);
		adapter.setDataToAdapter(fileInfo);
		adapter.notifyDataSetChanged();
		btn_file_clear.setOnClickListener(onClickListener);
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		tv_file_num = (TextView) findViewById(R.id.tv_file_num);
		tv_file_size = (TextView) findViewById(R.id.tv_file_size);
		lv_file_list = (ListView) findViewById(R.id.lv_file_list);
		btn_file_clear = (Button) findViewById(R.id.btn_file_clear);
		tv_file_num.setText(fileNum + "个");
		tv_file_size.setText(CommonUtil.getFileInfo(fileSize));

	}

	private void initInfo(int id2) {

		switch (id) {
		case R.id.rl_all:
			fileInfo = FileManager.getFileManager().getAnyFileList();
			fileSize = FileManager.getFileManager().getAnyFileSize();
			title = "全部文件";
			break;
		case R.id.rl_apk:
			fileInfo = FileManager.getFileManager().getApkFileList();
			fileSize = FileManager.getFileManager().getApkFileSize();
			title = "程序包";
			break;
		case R.id.rl_doxc:
			fileInfo = FileManager.getFileManager().getTxtFileList();
			fileSize = FileManager.getFileManager().getTxtFileSize();
			title = "文档";
			break;
		case R.id.rl_img:
			fileInfo = FileManager.getFileManager().getImageFileList();
			fileSize = FileManager.getFileManager().getImageFileSize();
			title = "图片";
			break;
		case R.id.rl_music:
			fileInfo = FileManager.getFileManager().getAudioFileList();
			fileSize = FileManager.getFileManager().getAudioFileSize();
			title = "音频";
			break;
		case R.id.rl_video:
			fileInfo = FileManager.getFileManager().getVideoFileList();
			fileSize = FileManager.getFileManager().getVideoFileSize();
			title = "视频";
			break;
		case R.id.rl_zip:
			fileInfo = FileManager.getFileManager().getZipFileList();
			fileSize = FileManager.getFileManager().getZipFileSize();
			title = "压缩包";
			break;
		default:
			break;
		}
		fileNum = fileInfo.size();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_file_clear:
				deleteFile();
				break;
			case R.id.iv_left:
				setResult(1);
				finish();
				break;
			}
		}

		private void deleteFile() {
			List<FileInfo> deleteFileInfo = new ArrayList<FileInfo>();// 保存用来删除的文件
			for (int i = 0; i < adapter.getDataList().size(); i++) {
				FileInfo Info = adapter.getDataList().get(i);
				if (Info.isSelected()) {
					deleteFileInfo.add(Info);
				}
			}
			for (int i = 0; i < deleteFileInfo.size(); i++) {
				FileInfo delFileInfo = deleteFileInfo.get(i);
				File file = delFileInfo.getFile();
				long size = file.length();
				if (file.delete()) {
					adapter.getDataList().remove(delFileInfo);
					FileManager.getFileManager().getAnyFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setAnyFileSize(
							FileManager.getFileManager().getAnyFileSize()
									- size);

					FileManager.getFileManager().getApkFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setApkFileSize(
							FileManager.getFileManager().getApkFileSize()
									- size);
					FileManager.getFileManager().getTxtFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setTxtFileSize(
							FileManager.getFileManager().getTxtFileSize()
									- size);
					FileManager.getFileManager().getImageFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setImageFileSize(
							FileManager.getFileManager().getImageFileSize()
									- size);
					FileManager.getFileManager().getAudioFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setAudioFileSize(
							FileManager.getFileManager().getAudioFileSize()
									- size);

					FileManager.getFileManager().getVideoFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setVideoFileSize(
							FileManager.getFileManager().getVideoFileSize()
									- size);
					FileManager.getFileManager().getZipFileList()
							.remove(delFileInfo);
					FileManager.getFileManager().setZipFileSize(
							FileManager.getFileManager().getZipFileSize()
									- size);
					switch (id) {
					case R.id.rl_all:

						fileSize = FileManager.getFileManager()
								.getAnyFileSize();
						break;
					case R.id.rl_apk:

						fileSize = FileManager.getFileManager()
								.getApkFileSize();
						break;
					case R.id.rl_doxc:

						fileSize = FileManager.getFileManager()
								.getTxtFileSize();
						break;
					case R.id.rl_img:

						fileSize = FileManager.getFileManager()
								.getImageFileSize();
						break;
					case R.id.rl_music:

						fileSize = FileManager.getFileManager()
								.getAudioFileSize();
						break;
					case R.id.rl_video:

						fileSize = FileManager.getFileManager()
								.getVideoFileSize();
						break;
					case R.id.rl_zip:

						fileSize = FileManager.getFileManager()
								.getZipFileSize();
						break;
					default:
						break;

					}

				}
			}
			adapter.notifyDataSetChanged();
			fileNum = adapter.getDataList().size();
			tv_file_num.setText(fileNum + "个");
			tv_file_size.setText(CommonUtil.getFileInfo(fileSize));
		}
	};

}
