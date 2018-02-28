package com.clover.utils;

import java.util.Date;

public class FormatXmlProcess {

	/**
	 * @param fromUserName
	 * @param toUserName
	 * @param result
	 * @return
	 */
	public  String formatXmlAnswer(String fromUserName, String toUserName,
			String result) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		Date date =new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(fromUserName);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(toUserName);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(result);
		sb.append("]]></Content></xml>");
		return sb.toString();
	}

	public static void main(String[] args){
		String fromUserName="xieyuan";
		String toUserName="liumu";
		String result="你好";
		FormatXmlProcess ss=new FormatXmlProcess();
		String str=ss.formatXmlAnswer(fromUserName,toUserName,result);
		System.out.println(str);
	}
}
