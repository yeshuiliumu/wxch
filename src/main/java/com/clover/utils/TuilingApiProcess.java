package com.clover.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class TuilingApiProcess {
	public static void main(String[] args) {
		TuilingApiProcess ii = new TuilingApiProcess();
		String result = ii.getTuilingResult("ajsgdfuiasgtf吗");
		System.out.println(result);
	}

	public String getTuilingResult(String content) {
		String result = "流木站长欢迎您";
		if (content != null
				&& !content.equals("")
				&& (content.contains("你好") || content.contains("嗨")
						|| content.contains("娱乐") || content.contains("节目")
						|| content.contains("功能") || content.contains("吗"))) {
			result = "回复序号使用娱乐功能:\n   1.注册(请先注册)\n   2.爱情告白\n   3.任务悬赏\n   4.写信给n年后的他.她.它 \n   5.市场调研系统\n   维护：流木站长主页：www.xyliumu.cn";
			return result;

		}
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(content);
		if (content != null && !content.equals("") && m.matches()) {

			try {
				int type = Integer.parseInt(content);
				Explain ep = new Explain();
				result = ep.explain(type);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		String apiUrl = "http://www.tuling123.com/openapi/api?key=5c2ace3806b74e23901d4f5b97964c49&info=";
		String param = "";
		try {
			param = apiUrl + URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** 发送httpget请求 */
		HttpGet request = new HttpGet(param);

		try {
			// 发送Post,并返回一个HttpResponse对象
			// Header header = response.getFirstHeader("Content-Length");
			// String Length=header.getValue();
			// 上面两行可以得到指定的Header
			HttpResponse response = new DefaultHttpClient().execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
			if (result == null) {
				return "机器人进入休眠，凤姐表示注意你了";
			}
			try {
				JSONObject json = JSONObject.fromObject(result);
				/*
				 * 的到响应的数据格式 {"code":100000,"text":"停了多久呢"}
				 */
				if (100000 == json.getInt("code")) {
					result = json.getString("text");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
