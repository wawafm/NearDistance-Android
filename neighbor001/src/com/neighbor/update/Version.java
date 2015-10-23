package com.neighbor.update;

import java.io.Serializable;


/**
 * 04-20 18:21:57.024: {"result":"","message":"","remarks":"紧急解决部分历史版本哇好玩板块无法访问的问题",
 * "createDate":"2015-02-13 05:10:23",
 * "updateDate":"2015-03-11 02:03:51",
 * "id":"71","name":"","type":"0",
 * "apkfullName":"wawa11445.apk",
 * "versionName":"1.1.4",
 * "versionCode":"45",
 * "url":"http://fast.wawa.fm:8888/group1/M00/00/01/Cvtf3VTcX3yALDV9AD1-KUmCkW4470.apk",
 * "fsize":4029993,"isup":"false","issuedate":1423775208000,"channel":"normal_channel","nshow":"3"}

 * @author fighting
 * @email huazhao520@163.com
 * @date 2015年4月20日
 * @company zz
 * @version 1.0
 * @description
 */
public class Version implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int type;
	private String name;
	private String versionName;
	private String versionCode;
	private String url;
	private String fsize;
	private boolean isup;
	private String remarks;
	
	
	public Version() {
		super();
		
	}





	public Version(int id, int type, String name, String versionName,
			String versionCode, String url, String size, boolean isup,
			String desc) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.url = url;
		this.fsize = size;
		this.isup = isup;
		this.remarks = desc;
	}





	


	public String getRemarks() {
		return remarks;
	}





	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getVersionName() {
		return versionName;
	}


	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}


	public String getVersionCode() {
		return versionCode;
	}


	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getSize() {
		return fsize;
	}


	public void setSize(String size) {
		this.fsize = size;
	}


	public boolean isIsup() {
		return isup;
	}


	public void setIsup(boolean isup) {
		this.isup = isup;
	}
	
	

}
