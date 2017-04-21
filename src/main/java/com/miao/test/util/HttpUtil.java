package com.miao.test.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	/*
	 * private static PoolingHttpClientConnectionManager connMgr; private static
	 * RequestConfig requestConfig; private static final int MAX_TIMEOUT = 7000;
	 * 
	 * static { // 设置连接池 connMgr = new PoolingHttpClientConnectionManager(); //
	 * 设置连接池大小 connMgr.setMaxTotal(100);
	 * connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
	 * 
	 * RequestConfig.Builder configBuilder = RequestConfig.custom(); // 设置连接超时
	 * configBuilder.setConnectTimeout(MAX_TIMEOUT); // 设置读取超时
	 * configBuilder.setSocketTimeout(MAX_TIMEOUT); // 设置从连接池获取连接实例的超时
	 * configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT); // 在提交请求之前
	 * 测试连接是否可用 configBuilder.setStaleConnectionCheckEnabled(true);
	 * requestConfig = configBuilder.build(); }
	 */
	public static String doGet(String url, Map<String, Object> params)
			throws Throwable {
		String apiUrl = url;
		int i = 0;
		StringBuffer param = new StringBuffer();
		String result = null;
		CloseableHttpResponse httpresponse = null;
		try {
			if (params != null) {
				for (String key : params.keySet()) {
					if (i == 0) {
						param.append("?");
					} else {
						param.append("&");
					}
					param.append(key).append("=").append(params.get(key));
					i++;
				}
				apiUrl += param;
			}
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(apiUrl);
			httpresponse = httpclient.execute(httpget);
			HttpEntity entity = httpresponse.getEntity();
			result = EntityUtils.toString(entity);
		} finally {
			if (httpresponse != null) {
				httpresponse.close();
			}
		}
		return result;
	}

	public static String doPost(String url, Map<String, Object> params)
			throws Throwable {
		CloseableHttpResponse httpresponse = null;
		String result = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			if (params != null) {
				List<NameValuePair> pairList = new ArrayList<NameValuePair>(
						params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					pairList.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue().toString()));
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
						pairList, Charset.forName("utf-8"));
				httpPost.setEntity(formEntity);
			}
			httpresponse = httpclient.execute(httpPost);
			HttpEntity entity = httpresponse.getEntity();
			result = EntityUtils.toString(entity);
		} finally {
			if (httpresponse != null) {
				httpresponse.close();
			}
		}
		return result;
	}

}
