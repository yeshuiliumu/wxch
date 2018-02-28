package com.clover.jsSdkUtils;
/**
 * @ClassName ConfigUtils
 * @Description 微信接入常量类
 * @author xieyuan 969354251@qq.com
 * @Date 2017年12月14日 
 * @version 1.0.0
 */
public class ConfigUtils {
	public static final String APPID = "wx1010dd9dfac8f85e";
	//最好通过接口获取保证安全，不建议在代码中直接保存
	public static final String APP_SECRET = "0494ee50ef6c762fbae14a5d0210ec18"; 
	public static final String GRANT_TYPE =  "client_credential";
	public static final String JSAPI_TICKET_TYPE = "jsapi";
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	 // 注意 URL 一定要动态获取，不能 hardcode
	public static String  CURRENT_URL = "http://www.xyliumu.cn/wxch/test3.html";
	public static String ACCESS_TOKEN = "";
	public static String  JSAPI_TICKET = "";    
	public static String NONCESTR = "";        //随机数
	public static String TIMESTAMP ="0";        //时间戳
	public static String SIGNNATURE ="";       //数字签名
}
