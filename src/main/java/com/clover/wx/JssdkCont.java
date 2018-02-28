package com.clover.wx;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clover.jsSdkUtils.ConfigUtils;
import com.clover.jsSdkUtils.SignUtil;
import com.clover.jsSdkUtils.TokenUtil;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * @ClassName Validation
 * @Description 微信接入验证接口
 * @author xieyuan 969354251@qq.com
 * @Date 2017年12月14日 
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/js")
public class JssdkCont {
	@RequestMapping(value= "/getSign")
	public @ResponseBody String getSign(HttpServletRequest request){
		String collecter =request.getParameter("collecter");
		String url =request.getParameter("url");
		if(url != null && !"".equals(url)){
			ConfigUtils.CURRENT_URL = url;
		}
		if(collecter !=null && !"".equals(collecter) && collecter.equalsIgnoreCase("y")){
			try {
				SignUtil.createSignByCollect();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				SignUtil.judgMent();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map<String ,String> map = new HashMap<String,String>();
		map.put("timestamp", ConfigUtils.TIMESTAMP);
		map.put("nonceStr", ConfigUtils.NONCESTR);
		map.put("signature", ConfigUtils.SIGNNATURE);
		map.put("appId", ConfigUtils.APPID);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
}
