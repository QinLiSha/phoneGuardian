package com.example.test8_10_2.bean;

import java.io.File;

public class FileInfo {

	private File file;//�ļ�
	private Boolean isSelected = false;//�Ƿ�ѡ��
	private String icon;//ͼ�������
	private String filetype;//�ļ�����
	
	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return "FileInfo [file=" + file + ", isselected=" + isSelected
				+ ", icon=" + icon + ", filetype=" + filetype + "]";
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Boolean isSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public FileInfo(File file, Boolean isselected, String icon, String filetype) {
		super();
		this.file = file;
		this.isSelected = isselected;
		this.icon = icon;
		this.filetype = filetype;
	}
	public FileInfo(File file,  String icon, String filetype) {
		super();
		this.file = file;
		this.icon = icon;
		this.filetype = filetype;
	}
}
