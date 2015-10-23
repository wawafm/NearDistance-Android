package com.neighor.neighbor001;

import com.neighbor.utils.Util;


public class AppConfig {

	//商圈首页http://m.dianping.com/huizhou
	public static final String BUSINESS_URL = "http://m.dianping.com/huizhou";
	//首页  “今日团购” 和  “今日优惠”
	public static final String MEITUAN_URL="http://api.union.meituan.com/data/api?city=惠州&category=美食&limit=10&district_name=惠城区&key=7715fcf5920382e453d94b8635332eb2965&keyword=全部&sort=1";
	
	public static final String GET_API = "http://wawa.fm/";
	// 获取服务端版本
	public static final String GET_SERVER_VERSION = "CmsSite/a/cms/version/mfind";
	
	//	短信发送接口  (获取验证码) 
	public static final String SEND_MESSAGE = "http://scyd.wmin.cc/sms/sendSMS?mobile=";

	//	注册用户接口   (参数键值对：mobile=xxx&password=xxx&code=xxx")
	public static final String USER_REGISTER ="http://scyd.wmin.cc/member/createMember?";

	//	重置密码接口   (参数键值对：mobile=xxx&password=xxx&code=xxx)
	public static final String USER_PASSWORD_RESETTING = "http://scyd.wmin.cc/member/resetPassword?";
	
	//	用户登录接口   (参数键值对：mobile=xxxx&password=xxxx)
	public static final String USER_LOGIN = "http://scyd.wmin.cc/member/memberLogin?";
	
	//首页房屋租赁
	public static final String HOUSE_HIRE = "http://m.fang.com/zf/huizhou/";
	// 小米统计渠道
	public static String MI_CHANNEL = Util.getMetaData(
			NeighborApplecation.getInstance(), "UMENG_CHANNEL");
}
