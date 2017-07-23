package com.askprice.carprice.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.askprice.carprice.entity.CarDealer;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemoteAPIProxy {
	
	private CloseableHttpClient client;

	private static class ProxyHolder {
		private static final RemoteAPIProxy INSTANCE = new RemoteAPIProxy();
	}
	
	private RemoteAPIProxy() {
		
		client = HttpClientBuilder.create().build();
	}

	public static final RemoteAPIProxy getInstance() {
		return ProxyHolder.INSTANCE;
	}
	
	public static void main(String[] args) 
	{
		Map<String, String> params = new HashMap<>();
		params.put(RemoteAPI.CommonParams.AuthorizeCode.name(), RemoteAPI.AuthorizeCode);
		params.put(RemoteAPI.CommonParams.InterfaceID.name(), RemoteAPI.InterfaceID);
		params.put(RemoteAPI.CommonParams.op.name(), RemoteAPI.APIOperation.recommend_dealers_csid.name());
		params.put("cityId", "201");
		params.put("csid", "2874");
		params.put("startRcord", "1");
		params.put("endRecord", "6");
		String responseBody = RemoteAPIProxy.getInstance().sendGetRequest(RemoteAPI.URL, params);
		
		responseBody = responseBody.replace("Longitude", "longitude");
		responseBody = responseBody.replace("Latitude", "latitude");
		
		System.out.println(responseBody);
		ObjectMapper mapper = new ObjectMapper();
		
		JavaType javaType = getCollectionType(mapper, ArrayList.class, CarDealer.class); 
		try {
			@SuppressWarnings("unchecked")
			List<CarDealer> list =  (List<CarDealer>)mapper.readValue(responseBody, javaType);
			list.forEach(dealer -> System.out.println(dealer.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {   
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }
	
	public String sendPostRequest(String url, Map<String, String> params) 
	{
		List<NameValuePair> requestParams = initParams(params);
		String responseBody = "";
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(requestParams, HTTP.UTF_8));
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
			{
				responseBody = paseResponse(response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseBody;
	}
	
	private static String paseResponse(HttpResponse response) {  
        HttpEntity entity = response.getEntity();  
        String body = null;  
        try {  
            body = EntityUtils.toString(entity);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return body;  
    } 
	
	public String sendGetRequest(String url, Map<String, String> params) 
	{
		List<NameValuePair> requestParams = initParams(params);
		String requestURL = url + "?" + URLEncodedUtils.format(requestParams, HTTP.UTF_8);
		String responseBody = "";
		HttpGet get = new HttpGet(requestURL);
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
			{
				responseBody = paseResponse(response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseBody;
	}
	
	private List<NameValuePair> initParams(Map<String, String> params)
	{
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		params.forEach((paramName, paramValue) -> {
			requestParams.add(new BasicNameValuePair(paramName, paramValue));
		});
		
		return requestParams;
	}

}
