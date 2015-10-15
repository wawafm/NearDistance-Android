package com.neighbor.bean;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.neighbor.utils.StringUtils;


public class UserInfoBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String word;
	private String genter;
	private int userType;
	private String username;
	private String message;
	private String result;
	private String pimg;
	private String sign;
	private String area;
	// 80后
	private String age;
	// 星座
	private String constellation;
	// 婚姻状况
	private String emotion;

	private int fsnum;
	private int gznum;
	

	// 标签


	// 关系
	private String subscription;
	//用户音乐数量
	private int yscnum;
	//音乐圈数量
	private int yqqnum;
	
	//收藏数量
	private int wscnum;
	//收听数量
	private int gdnum;
	
	

	
	
	

	public int getGdnum() {
		return gdnum;
	}

	public void setGdnum(int gdnum) {
		this.gdnum = gdnum;
	}

	public int getYscnum() {
		return yscnum;
	}

	public void setYscnum(int yscnum) {
		this.yscnum = yscnum;
	}

	public int getYqqnum() {
		return yqqnum;
	}

	public void setYqqnum(int yqqnum) {
		this.yqqnum = yqqnum;
	}

	public int getWscnum() {
		return wscnum;
	}

	public void setWscnum(int wscnum) {
		this.wscnum = wscnum;
	}

	public int getFsnum() {
		return fsnum;
	}

	public void setFsnum(int fsnum) {
		this.fsnum = fsnum;
	}

	public int getGznum() {
		return gznum;
	}

	public void setGznum(int gznum) {
		this.gznum = gznum;
	}

	public UserInfoBean(String id, String word, int userType, String name,
			String gender, String pimg) {
		this.id = id;
		this.word = word;
		this.userType = userType;
		this.genter = gender;
		this.username = name;
		this.pimg = pimg;
	}

	public UserInfoBean(String id, String word, int userType, String name,
			String gender, String pimg, String area, String sign, String age,
			String em, String cons) {
		this.id = id;
		this.word = word;
		this.userType = userType;
		this.genter = gender;
		this.username = name;
		this.pimg = pimg;
		this.area = area;
		this.sign = sign;
		this.age = age;
		this.emotion = em;
		this.constellation = cons;
	}

	public UserInfoBean() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getGenter() {
		return genter;
	}

	public void setGender(String genter) {
		this.genter = genter;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPimg() {
		return pimg;
	}

	public void setPimg(String pimg) {
		this.pimg = pimg;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", id);
			json.put("username", username);
			json.put("pimg", pimg);
			json.put("genter", genter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json.toString();
	}

	public String getSign() {
		if(StringUtils.isEmpty(sign))
			sign = "这里不只有音乐";
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAddr() {
		if(StringUtils.isEmpty(area))
			area = "";
		return area;
	}

	public void setAddr(String addr) {
		this.area = addr;
	}

	public String getAge() {
		
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getConstellation() {
		
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof UserInfoBean) {
			UserInfoBean othor = (UserInfoBean) o;
			return id.equals(othor.getId());
		} else
			return false;

	}

	/*// 获取用户信息字符串
	public String getUserString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getAge()).append("、").append(this.getConstellation());
		if (dicts!=null && dicts.size() != 0) {
			sb.append("、");
			for (UserLabel label : dicts) {
				sb.append(label.getSign()).append("、");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		if(sb.toString().length() <2){
			return "不听歌会死星人！";
		}

		return sb.toString();
	}
	
	//获取用户标签格式字符串
	public String getLabelString()
	{
		StringBuffer sb = new StringBuffer();
		
		if (dicts!=null && dicts.size() != 0) {
			
			for (UserLabel label : dicts) {
				sb.append(label.getSign()).append("、");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		
		return sb.toString();
	}
	
	//http 提交参数   &dists=501,503
	public String getHttpArgsString()
	{
		StringBuffer sb = new StringBuffer();
		for (UserLabel label : dicts) {
			sb.append(label.getDictid()).append(",");
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}*/
}
