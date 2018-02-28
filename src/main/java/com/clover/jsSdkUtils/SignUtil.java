package com.clover.jsSdkUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
/**
 * @ClassName SignUtil
 * @Description 微信接入签名类工具
 * @author xieyuan 969354251@qq.com
 * @Date 2017年12月14日 
 * @version 1.0.0
 */
public class SignUtil {
	/**生成微信js_SDK接入验证的签名字符串
	 *注意这里参数名必须全部小写，且必须有序*/
	private static String jointString(){
		  //注意这里参数名必须全部小写，且必须有序
        String signString = "jsapi_ticket=" + ConfigUtils.JSAPI_TICKET +
                  "&noncestr=" + ConfigUtils.NONCESTR +
                  "&timestamp=" + ConfigUtils.TIMESTAMP +
                  "&url=" + ConfigUtils.CURRENT_URL;
        System.out.println(signString);
		return signString;
	}
	/**方便使用接口随时获取AccessToken、JsapiTicket接口*/
	public static void createSignByCollect() throws NoSuchAlgorithmException{
		TokenUtil tokenUtil = new TokenUtil();
		tokenUtil.create_timestamp();
		tokenUtil.create_nonce_str();
		tokenUtil.getAccessToken();
		tokenUtil.getJsapiTicket();
		String sinString = jointString();
		createSign(sinString);
	}
	/**生成微信js_SDK接入验证的签名
	 **/
	public static String createSign(String signString) throws NoSuchAlgorithmException{	
         try {
        	 MessageDigest crypt = MessageDigest.getInstance("SHA-1");
    		 crypt.reset();
    		 crypt.update(signString.getBytes("UTF-8"));
			 ConfigUtils.SIGNNATURE = byteToHex(crypt.digest()); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signString;
		
	}
	/**两个小时请求一次接口*/
	public static void judgMent() throws NoSuchAlgorithmException{
		long momentTime = System.currentTimeMillis() / 1000;
		long timestamp = Long.parseLong(ConfigUtils.TIMESTAMP);
		if(momentTime-timestamp > 3600){
			setTimeStamp();
		}
	}
	
	/**使用同步锁获取AccessToken、JsapiTicket，防止请求并发操作*/
	private static synchronized void setTimeStamp() throws NoSuchAlgorithmException{
		long momentTime = System.currentTimeMillis() / 1000;
		long timestamp = Long.parseLong(ConfigUtils.TIMESTAMP);
		if(momentTime-timestamp > 3600){
			TokenUtil tokenUtil = new TokenUtil();
			tokenUtil.create_timestamp();
			tokenUtil.create_nonce_str();
			tokenUtil.getAccessToken();
			tokenUtil.getJsapiTicket();
			String sinString = jointString();
			createSign(sinString);
		}
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
}
