package com.neighbor.bean;

import java.io.Serializable;

public class UserLoginReturnBean<MemberinfoBean> implements Serializable{

	/*{
	    "code": "1",
	    "msg": "用户登录成功",
	    "memberinfo": {
	        "id": "8",
	        "mobile": "18276172451",
	        "password": "4607e782c4d86fd5364d7e4508bb10d9",
	        "nickname": null,
	        "headerphoto": null,
	        "memberid": null,
	        "address": null
	    }
	}*/
	
	private String code;
	private String msg;
	private MemberinfoBean memberinfo;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public MemberinfoBean getMemberinfo() {
		return memberinfo;
	}
	public void setMemberinfo(MemberinfoBean memberinfo) {
		this.memberinfo = memberinfo;
	}
	
	@Override
	public String toString() {
		return "UserLoginReturnBean [code=" + code + ", msg=" + msg
				+ ", memberinfo=" + memberinfo + ", getCode()=" + getCode()
				+ ", getMsg()=" + getMsg() + ", getMemberinfo()="
				+ getMemberinfo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
