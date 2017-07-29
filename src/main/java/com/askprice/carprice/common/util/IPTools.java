package com.askprice.carprice.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPTools {
	
	public static Logger logger = LoggerFactory.getLogger(IPTools.class);

	public static void main(String[] args) {

	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@SuppressWarnings("deprecation")
	public static Map<String, String> getCity(String ipAddr) {
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("q", ipAddr));
		String url = "http://cn.geoipview.com/";
		String requestURL = url + "?" + URLEncodedUtils.format(requestParams, HTTP.UTF_8);
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpGet httpGet = new HttpGet(requestURL);
		httpGet.setHeader("Accept",	"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Host", "cn.geoipview.com");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		HttpResponse response;
		String html = "";
		try {
			response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				html = EntityUtils.toString(response.getEntity(), "UTF-8");
//				logger.info("====================== get html from cn.geoipview.com ======================");
//				logger.info(html);
//				logger.info("====================== get html from cn.geoipview.com ======================");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String countryStart = "<TD class=\"show1\" nowrap>国家:&nbsp;</td><td class=\"show2\">";
		String cityStart = "<TD class=\"show1\" nowrap>城市:&nbsp;</td><td class=\"show2\">";
				
		String city = getContent(html, cityStart);
		String country = getContent(html,countryStart);
		Map<String, String> cityInfo = new HashMap<>();
		cityInfo.put("country", country);
		cityInfo.put("city", city);
		return cityInfo;
	}

	private static String getContent(String html, String start) {
		int beginIndex = html.indexOf(start);
		if (beginIndex == -1)
			return null;

		int endIndex = html.indexOf("</td>", beginIndex + start.length());
		String address = html.substring(beginIndex + start.length(), endIndex);
		return address;
	}

}
