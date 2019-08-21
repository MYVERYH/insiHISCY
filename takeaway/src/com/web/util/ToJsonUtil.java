package com.web.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ToJsonUtil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����
	public static String toJson(Collection<?> lists){
		JSONObject json = new JSONObject();//��������put�ķ�ʽ����
		JSONArray jsonArray=new JSONArray();
		JsonConfig config = new JsonConfig();//����Ҫ����JSON���л�����е�Date����ĸ�ʽ�����Լ���ֵ�ĸ�ʽ��
		config.registerJsonValueProcessor(
				Date.class,
				new JsonValueProcessor(){
					@Override
					public Object processObjectValue(String key,
							Object value, JsonConfig config) {
						  	String str = new SimpleDateFormat("yyyy-MM-dd").format((java.util.Date) value);
		                    return str;
					}
					
					@Override
					public Object processArrayValue(Object arg0,
							JsonConfig arg1) {
						return null;
					}
				});
		config.registerJsonValueProcessor(
				Timestamp.class,
				new JsonValueProcessor(){
					@Override
					public Object processObjectValue(String key,
							Object value, JsonConfig config) {
						  	String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format((java.sql.Timestamp) value);
		                    return str;
					}
					
					@Override
					public Object processArrayValue(Object arg0,
							JsonConfig arg1) {
						return null;
					}
				});
		if(lists!=null && lists.size()>0){
			//���ʱ��ת����
			jsonArray.addAll(lists,config);
			json.put("count",lists.size());
			json.put("data",jsonArray);
		}else{
			//û�в�ѯ�����
			json.put("count", 0);
		}
		return json.toString();
	}
	//�ַ�
	public static String toJson(String str){
		JSONObject json = new JSONObject();
		JSONArray jsonArray=new JSONArray();
		JsonConfig config = new JsonConfig();
		jsonArray.add(str,config);
		json.put("data",jsonArray);
		return json.toString();
	}
	//����
		public static String toJson(Object t){
			JSONObject json = new JSONObject();
			JSONArray jsonArray=new JSONArray();
			JsonConfig config = new JsonConfig();
			jsonArray.add(t,config);
			json.put("data",jsonArray);
			return json.toString();
		}
		public static String toJson(Integer curPage,Integer totalRows,Collection<?> lists,boolean success){
			JSONObject json = new JSONObject();
			JSONArray jsonArray=new JSONArray();
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(
					Date.class,
					new JsonValueProcessor(){
						@Override
						public Object processObjectValue(String key,
								Object value, JsonConfig config) {
							  	String str = new SimpleDateFormat("yyyy-MM-dd").format((java.util.Date) value);
			                    return str;
						}
						
						@Override
						public Object processArrayValue(Object arg0,
								JsonConfig arg1) {
							return null;
						}
					});
			if(lists!=null && lists.size()>0){
				//���ʱ��ת����
				jsonArray.addAll(lists,config);
				json.put("success",success);
				json.put("totalRows",totalRows);
				json.put("curPage",curPage);
				json.put("data",jsonArray);
			}else{
				//û�в�ѯ�����
				json.put("curPage", 0);
			}
			return json.toString();
		}
}
