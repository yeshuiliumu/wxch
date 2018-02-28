package com.clover.serviceImpl;

import org.springframework.stereotype.Service;

import com.clover.service.ProcessService;
import com.clover.utils.FormatXmlProcess;
import com.clover.utils.ReceiveXmlEntity;
import com.clover.utils.ReceiveXmlProcess;
import com.clover.utils.TuilingApiProcess;


import lombok.extern.slf4j.Slf4j;
@Service("processImpl")
public class ProcessImpl implements ProcessService{

	@Override
	public String processWechatMag(String xml) {
		//解析xml数据
				ReceiveXmlEntity xmlEntity =new ReceiveXmlProcess().getMsgEntuity(xml);
				//以文本消息为例，调用图灵机器人api接口，获取回复内容
				String result="";
				if("text".endsWith(xmlEntity.getMsgType())){
							result=new TuilingApiProcess().getTuilingResult(xmlEntity.getContent());
						result=new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),xmlEntity.getToUserName(),result);
						return result;
				}
						result="参数错误";
						//回复给微信的也是xml格式的数据，所以需要将其封装为文本类型返回
						result=new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(),xmlEntity.getToUserName(),result);
						return result;
	}

}
