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
		City city = null;
		try{
			city = City.valueOf("dfffff");
		}
		catch(Exception e)
		{
		}
		if (city == null)
		{
			System.out.println("can not find city");
		}
		
//		System.out.println(city.getCode());
		
//		List<CarDealer> list = getCarDealerBySerialId("2874","201");
//		list.forEach(dealer -> System.out.println(dealer));
		
//		List<CarDealer> list2 = getCarDealerByCarId("117839","201");
//		list2.forEach(dealer -> System.out.println(dealer));
	}
	
	@SuppressWarnings("unchecked")
	public static List<CarDealer> getCarDealerBySerialId(String serialId, String cityId)
	{
		Map<String, String> params = new HashMap<>();
		initBasicParams(params, RemoteAPI.APIOperation.recommend_dealers_csid);
		params.put("cityId", cityId);
		params.put("csid", serialId);
		String responseBody = RemoteAPIProxy.getInstance().sendGetRequest(RemoteAPI.URL, params);
		responseBody = preProcessResponse(responseBody);
		
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = getCollectionType(mapper, ArrayList.class, CarDealer.class); 
		List<CarDealer> list = new ArrayList<>();
		try {
			list = (List<CarDealer>)mapper.readValue(responseBody, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<CarDealer> getCarDealerByCarId(String carId, String cityId)
	{
		Map<String, String> params = new HashMap<>();
		initBasicParams(params, RemoteAPI.APIOperation.recommend_dealers_carid);
		params.put("cityId", cityId);
		params.put("carid", carId);
		String responseBody = RemoteAPIProxy.getInstance().sendGetRequest(RemoteAPI.URL, params);
		responseBody = preProcessResponse(responseBody);
		
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = getCollectionType(mapper, ArrayList.class, CarDealer.class); 
		List<CarDealer> list = new ArrayList<>();
		try {
			list = (List<CarDealer>)mapper.readValue(responseBody, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private static String preProcessResponse(String responseBody) {
		responseBody = responseBody.replace("Longitude", "longitude");
		responseBody = responseBody.replace("Latitude", "latitude");
		return responseBody;
	}

	private static void initBasicParams(Map<String, String> params, RemoteAPI.APIOperation operation) {
		params.put(RemoteAPI.CommonParams.AuthorizeCode.name(), RemoteAPI.AuthorizeCode);
		params.put(RemoteAPI.CommonParams.InterfaceID.name(), RemoteAPI.InterfaceID);
		params.put(RemoteAPI.CommonParams.op.name(), operation.name());
		params.put("startRcord", "1");
		params.put("endRecord", "6");
	}
	  
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {   
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }
	
	@SuppressWarnings("deprecation")
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
