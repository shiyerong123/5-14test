package com.jk.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * 
 * 类: HttpClient <br>
 * 描述: httpclient工具类 <br>
 * 作者: song<br>
 * 时间: 2017年7月21日 下午3:15:27
 */
public class HttpClientUtil {
	
	static CloseableHttpClient client = null;
	static {
		client = HttpClients.createDefault();
	}
	/**
	 * <pre>get2(直接传参)   
	 * 创建人：史叶荣
	 * 创建时间：2019年3月8日 下午5:11:28    
	 * 修改人：史叶荣
	 * 修改时间：2019年3月8日 下午5:11:28    
	 * 修改备注： 
	 * @param url
	 * @param params
	 * @return</pre>
	 */
	public static String get2(String url,Object params){
		try {
			HttpGet httpGet = new HttpGet();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(url).append("/").append(params);
			httpGet.setURI(new URI(stringBuffer.toString()));
			CloseableHttpResponse execute = client.execute(httpGet);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * 方法: get <br>
	 * 描述: get请求 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年7月21日 下午3:15:25
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String get(String url,HashMap<String, Object> params){
		try {
			HttpGet httpGet = new HttpGet();
			Set<String> keySet = params.keySet();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());
			for (String key : keySet) {
				stringBuffer.append("&").append(key).append("=").append(params.get(key));
			}
			httpGet.setURI(new URI(stringBuffer.toString()));
			CloseableHttpResponse execute = client.execute(httpGet);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * 方法: post <br>
	 * 描述: post请求 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年7月21日 下午3:20:31
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String url,HashMap<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(url));
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
				parameters.add(e);
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
			httpPost.setEntity(entity );
			CloseableHttpResponse execute = client.execute(httpPost);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
