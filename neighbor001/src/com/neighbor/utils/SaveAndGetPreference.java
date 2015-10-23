package com.neighbor.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import org.apache.commons.codec.binary.Base64;

import com.neighbor.bean.MemberinfoBean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveAndGetPreference {
	
	private Context mContext;
	
	public SaveAndGetPreference(Context mContext){
		this.mContext = mContext;
	}
	
	public void saveOAuth(MemberinfoBean memberinfoBean) {  
	    SharedPreferences preferences = mContext.getSharedPreferences("base64",  
	            mContext.MODE_PRIVATE);
	    // 创建字节输出流  
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    try {  
	        // 创建对象输出流，并封装字节流  
	        ObjectOutputStream oos = new ObjectOutputStream(baos);  
	        // 将对象写入字节流  
	        oos.writeObject(memberinfoBean);  
	        // 将字节流编码成base64的字符窜  Base64.encodeBase64(baos.toByteArray()));  
	        String oAuth_Base64 = new String(Base64.encodeBase64(baos.toByteArray()));
	        
	        Editor editor = preferences.edit();  
	        editor.putString("memberinfoBean", oAuth_Base64);  
	  
	        editor.commit();  
	    } catch (IOException e) {  
	        // TODO Auto-generated  
	    }  
	}  
	
	public MemberinfoBean readOAuth() {  
		MemberinfoBean menMemberinfoBean = null;  
	    SharedPreferences preferences = mContext.getSharedPreferences("base64",  
	            mContext.MODE_PRIVATE);  
	    String productBase64 = preferences.getString("memberinfoBean", "");  
	              
	    //读取字节  Base64.decodeBase64(productBase64.getBytes());
	    byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
	      
	    //封装到字节流  
	    ByteArrayInputStream bais = new ByteArrayInputStream(base64);  
	    try {  
	        //再次封装  
	        ObjectInputStream bis = new ObjectInputStream(bais);  
	        try {  
	            //读取对象  
	        	menMemberinfoBean = (MemberinfoBean) bis.readObject();  
	        } catch (ClassNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    } catch (StreamCorruptedException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	    return menMemberinfoBean;  
	}  
}
