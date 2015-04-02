package com.xinxi.util;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Title: Ext JS ������ Description: ��������ת��java����ΪXML�ļ���ʽ��JSON�ļ���ʽ,(JDK13H)
 * 
 * @author weijun,sigesoftware
 * @time: 2010.05.06
 * @version: 0.0.1.1
 */
public class ExtHelper {

	/**
	 * ͨ��List����JSON����
	 * 
	 * @param list
	 *            ����bean�����list
	 * @return JSON����
	 */
	public static JSONObject getJSONObject(List list) {
		return getJSONObjectByPage(list.size(), list);
	}

	/**
	 * ͨ����ҳ��List����JSON����
	 * 
	 * @param total
	 *            ��¼����
	 * @param list
	 *            ����bean�����list
	 * @return JSON����
	 */
	public static JSONObject getJSONObjectByPage(int total, List list) {
		try {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", total);
			jsonMap.put("rows", list);
			return notNullJSONObject(jsonMap);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * ͨ��bean����JSON����
	 * 
	 * @param bean
	 *            bean����
	 * @return ���ɵ�JSON����
	 */
	public static String getJSonFormBean(Object bean) {
		String jsonStr = "";
		try {
			if (bean != null) {
				JSONObject jsonArray = notNullJSONObject(bean);
				jsonStr = jsonArray.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonStr;
	}

	public static JSONObject getJSONObject(Object bean) {
		return JSONObject.fromObject(bean);
	}

	private static JSONObject notNullJSONObject(Object obj) {
		String result = JSONObject.fromObject(obj).toString();
		result = result.replaceAll("null", "''");
		return JSONObject.fromObject(result);
	}

	public static JSONObject getJSONObject(String jsonStr) {
		return JSONObject.fromObject(jsonStr);
	}
}
