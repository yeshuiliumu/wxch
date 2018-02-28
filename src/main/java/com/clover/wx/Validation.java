package com.clover.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clover.service.ProcessService;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Validation
 * @Description 微信接入验证接口
 * @author xieyuan 969354251@qq.com
 * @Date 2017年12月14日 
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/wx")
public class Validation {
	@Autowired
	private ProcessService processImpl;
	
	@RequestMapping(value= "/validation")
	public @ResponseBody String getWxInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String echostr =request.getParameter("echostr");
		//读取接收到的xml信息
		StringBuffer sb=new StringBuffer();
		try {
			InputStream is=request.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			String s="";
			while((s=br.readLine())!=null){
				sb.append(s);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String xml=sb.toString();//微信端发送过来的数据
		System.out.println("xml="+xml);
		/*判断是否是微信端接入验证码，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回
		 * */
		String result="";
		if(echostr!=null&&echostr.length()>1){
			result=echostr;
			System.out.println("echostr:"+echostr);
		}else{
			//正常微信处理流程
			try {
				result=processImpl.processWechatMag(xml);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("正常微信处理流程失败");
			}
		}
		//response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		//response.setCharacterEncoding("UTF-8");
		return result;
	}

}
