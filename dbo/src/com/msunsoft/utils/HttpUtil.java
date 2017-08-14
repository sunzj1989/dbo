package com.msunsoft.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	/**
	 * 接口调用方法
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getRetVal(String url, Map<String, String>... params) {
		String retVal = null;
		try {
			PostMethod postMethod = new PostMethod(url);
			for (Map<String, String> map : params) { //循环参数，有几项添加几项
				for (String key : map.keySet()) {
					if (map.get(key) != null && !"".equals(map.get(key))) { //如果参数为空则不传
						postMethod.addParameter(key, map.get(key));
					}
				}
			}
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8"); //统一编码UTF-8
			HttpClient httpClient = new HttpClient();
			httpClient.executeMethod(postMethod);
			retVal = postMethod.getResponseBodyAsString();
			postMethod.releaseConnection();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return retVal;

	}

	public static String getRetValJson(String url, String params) {
		String retVal = "";
		try {
			PostMethod postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
			postMethod.setRequestBody(params);
			HttpClient httpClient = new HttpClient();
			httpClient.executeMethod(postMethod);
			retVal = postMethod.getResponseBodyAsString();
			postMethod.releaseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

}
