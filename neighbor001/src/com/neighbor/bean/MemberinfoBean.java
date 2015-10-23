package com.neighbor.bean;

import java.io.Serializable;

public class MemberinfoBean implements Serializable{

	/*memberinfo": {
    "id": "8",
    "mobile": "18276172451",
    "password": "4607e782c4d86fd5364d7e4508bb10d9",
    "nickname": null,
    "headerphoto": null,
    "memberid": null,
    "address": null
	}*/
	
	private String id;
	private String mobile;
	private String password;
	private String nickname;
	private String headerphoto;
	private String memberid;
	private String address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeaderphoto() {
		return headerphoto;
	}
	public void setHeaderphoto(String headerphoto) {
		this.headerphoto = headerphoto;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "MemberinfoBean [id=" + id + ", mobile=" + mobile
				+ ", password=" + password + ", nickname=" + nickname
				+ ", headerphoto=" + headerphoto + ", memberid=" + memberid
				+ ", address=" + address + "]";
	}
	
	
}
