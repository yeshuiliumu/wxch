package com.clover.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class ReceiveXmlProcess {

	/**
	 * @param 解析接收到的微信xml
	 * @return
	 */
	public ReceiveXmlEntity getMsgEntuity(String strXml) {
		// TODO Auto-generated method stub
		ReceiveXmlEntity msg=null;
		if(strXml.length()<=0 || strXml==null){
			return null;
		}
		try {
			//将字符串转化为xml文档
			Document document=DocumentHelper.parseText(strXml);
			//获取文档的根节点
			Element root=document.getRootElement();
			//遍历根节点下所有子节点
			Iterator<?> iter =root.elementIterator();
			//遍历所有节点
			msg =new ReceiveXmlEntity();
			//利用反射机制，调用set方法
			//利用该实体的元类型
			try {
				Class<?> c=Class.forName("com.clover.utils.ReceiveXmlEntity");
				msg=(ReceiveXmlEntity)c.newInstance();//创建这个实体的对象
				while(iter.hasNext()){
					Element ele=(Element)iter.next();
					//获取set方法中的参数字段（实体类的属性）
					Field field=c.getDeclaredField(ele.getName());
					//获取set方法，field.getType()获取它的参数数据类型
					Method method=c.getDeclaredMethod("set"+ele.getName(), field.getType());
					//调用set方法
					method.invoke(msg, ele.getText());
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

}
