package com.neighbor.bean;

import java.io.Serializable;


public class CommentBean implements Serializable{

	
	private int id;
	private int contentId;
	private int type;
	private String title;
	private String content;
	private String createDate;
	private UserInfoBean createUser;
	private UserInfoBean replyUser;
	private int result;
	private String message;

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

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public UserInfoBean getUserInfo() {
		return createUser;
	}

	public void setUserInfo(UserInfoBean userInfo) {
		this.createUser = userInfo;
	}

	public UserInfoBean getReplyUserInfo() {
		return replyUser;
	}

	public void setReplyUserInfo(UserInfoBean replyUserInfo) {
		this.replyUser = replyUserInfo;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
