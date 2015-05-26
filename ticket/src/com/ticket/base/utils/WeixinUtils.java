package com.ticket.base.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

/**  
 * 微信接口调用工具类
 *
 * @author wujunjun 
 * @date 2015-1-27 上午9:12:30
 * @since 1.0  
 * @Copyright 2014 东莞市邮政局All rights reserved.  
 */
public class WeixinUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6506912922082700132L;
	
	private String appId = "wxac7d69a69e29b843";
    private String appSecret = "935cffc4115d6567d81c6007fd218838";
    private String accessToken;
    private String jsapiTicket;
    
    public WeixinUtils(){
    	accessToken = GetAccessToken();
    	jsapiTicket = GetJsApiTicket();
    }
    
    //获取accesstoken
    public String GetAccessToken()
    {
        if (appId == null || appSecret == null){
            return "ERROR 001";//APPID APPSECRET NULL
        }
        try
        {
        	String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    		Map<String, String> parmap = new HashMap<String, String>();
    		parmap.put("appid", appId);
    		parmap.put("secret", appSecret); 
    		String result = HttpClientUtils
    				.sendPostRequest(urlStr, parmap, "UTF-8");
    		System.out.println(result);
    		return JSON.parseObject(result).getString("access_token");
        }
        catch (Exception e)
        {
            return "ERROR 000";//HTTP ERROR
        }
    }
    
  //获取jsApiTicket
    public String GetJsApiTicket()
    {
        if (appId == null || appSecret == null){
            return "ERROR 001";//APPID APPSECRET NULL
        }
        try
        {
        	String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
    		String result = HttpClientUtils.sendApacheGetRequst(urlStr, "UTF-8");
    		System.out.println(result);
    		return JSON.parseObject(result).getString("ticket");
        }
        catch (Exception e)
        {
            return "ERROR 000";//HTTP ERROR
        }
    }
    
    public Map<String, Object> getJsApiConfig(String url){
    	//sM4AOVdWfPE4DxkXGEs8VMjZadWesP1-U_uZ5vki0LnS_Hi1zunjD8jrDELabrHZgsswOX_RkkFOgW-l8fletQ
    	//sM4AOVdWfPE4DxkXGEs8VMjZadWesP1-U_uZ5vki0LnS_Hi1zunjD8jrDELabrHZgsswOX_RkkFOgW-l8fletQ
    	System.out.println(accessToken);
    	System.out.println(jsapiTicket);
    	Map<String, Object> map = new HashMap<String, Object>();
    	long timestamp = System.currentTimeMillis();
        String nonceStr = getRandomString(10);
        String string1 = MessageFormat.format("jsapi_ticket={0}&noncestr={1}&timestamp={2}&url={3}", jsapiTicket, nonceStr, timestamp, url);
        String signature = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    	map.put("appId", appId);
    	map.put("timestamp", timestamp);
    	map.put("nonceStr", nonceStr);
    	map.put("signature", signature);
    	map.put("url", url);
		return map;
    	
    }
    
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    
    public static void main(String[] args) {
    	WeixinUtils weixin = new WeixinUtils();
    	System.out.println(weixin.getJsApiConfig("http://www.baidu.com"));
//    	String result = "{\"access_token\":\"ldWelvddgLF1vEeolBl-F5lCTUhG8abujLUJxR-A-xu3bey22niSCMl1aHpmoHchJtDnYMn2LWUn25NDJv-kiS3jm948PQJEJqrUkGDuQAc\",\"expires_in\":7200}";
//    	System.out.println(JSON.parseObject(result).getString("access_token"));
//    	//WeinxinUtils.GetAccessToken();
//    	String a = "{0} sad";
//    	System.out.println(MessageFormat.format(a, new Object[]{"abc"}));
	}
    
    public String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
     }   


	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
    
    
}
