package com.clover.jsSdkUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
/**
 * @ClassName TokenUtil
 * @Description 微信js_SDK引入验证工具
 * @author xieyuan 969354251@qq.com
 * @Date 2017年12月14日 
 * @version 1.0.0
 */
public class TokenUtil {
	/**获取access_token然后保存在缓存中
	 * 需要在微信公众平台进行ip白名单设置
	 * */
	public void getAccessToken() {
		StringBuffer url = new StringBuffer(ConfigUtils.ACCESS_TOKEN_URL);
		url.append("?grant_type=").append(ConfigUtils.GRANT_TYPE).append("&appid=")
		.append(ConfigUtils.APPID).append("&secret=").append(ConfigUtils.APP_SECRET);
		String result = getHttpResult(url.toString());
		JSONObject json = JSONObject.fromObject(result);
		//打印返回信息日记
		//log.info(json.toString);
		if(json.getString("access_token") !=null){
			ConfigUtils.ACCESS_TOKEN = json.getString("access_token");
		}	
	}
	/**获取ticket然后保存在缓存中
	 * 需要先获取access_token
	 * */
	public void getJsapiTicket(){
		StringBuffer url = new StringBuffer(ConfigUtils.JSAPI_TICKET_URL);
		url.append("?access_token=").append(ConfigUtils.ACCESS_TOKEN)
		.append("&type=").append(ConfigUtils.JSAPI_TICKET_TYPE);
		String result = getHttpResult(url.toString());
		JSONObject json = JSONObject.fromObject(result);
		//打印返回信息日记
		//log.info(json.toString);
		ConfigUtils.JSAPI_TICKET = json.getString("ticket");
	}
	/**获取随机数并保存到缓存*/
	public  void create_nonce_str() {
		ConfigUtils.NONCESTR = UUID.randomUUID().toString();
    }
	/**获取时间戳并保存到缓存*/
	public  void create_timestamp() {
    	ConfigUtils.TIMESTAMP = Long.toString(System.currentTimeMillis() / 1000);
    }
	/**模拟http get请求*/
	public String getHttpResult(String url) {
		String result = "";
		/* 发送httpget请求 */
		HttpGet request = new HttpGet(url);
		// 发送Post,并返回一个HttpResponse对象
		// Header header = response.getFirstHeader("Content-Length");
		// String Length=header.getValue();
		// 上面两行可以得到指定的Header
		try {
			HttpResponse response = new DefaultHttpClient().execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
