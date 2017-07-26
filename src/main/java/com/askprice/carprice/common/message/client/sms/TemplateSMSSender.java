package com.askprice.carprice.common.message.client.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.askprice.carprice.common.message.cloopen.rest.sdk.CCPRestSDK;

@Component
@Scope("singleton")
public class TemplateSMSSender {
	
	@Value("${sms.provider.serverIP}")
	private String serverIP;
	
//	@Value(value = "#{appProperties['sms.provider.serverPort']}")
	@Value("${sms.provider.serverPort}")
	private String serverPort;
	
//	@Value(value = "#{appProperties['sms.provider.accountSid']}")
	@Value("${sms.provider.accountSid}")
	private String accountSid;
	
//	@Value(value = "#{appProperties['sms.provider.accountToken']}")
	@Value("${sms.provider.accountToken}")
	private String accountToken;
	
//	@Value(value = "#{appProperties['sms.provider.appId']}")
	@Value("${sms.provider.appId}")
	private String appId;
	
	private CCPRestSDK sender = new CCPRestSDK();
	
	public TemplateSMSSender()
	{
		
	}
	
	public CCPRestSDK getSmsSender()
	{
		sender.init(serverIP, serverPort);
		sender.setAccount(accountSid, accountToken);
		sender.setAppId(appId);
		sender.setBodyType(CCPRestSDK.BodyType.Type_JSON);
		return this.sender;
	}
//	/**
//	 * @param args
//	 */
//	public void main(String[] args) {
//		HashMap<String, Object> result = null;
//
//		
//		result = restAPI.sendTemplateSMS("18665982895","1" ,new String[]{"234536","2"});
//
//		System.out.println("SDKTestSendTemplateSMS result=" + result);
//		
//		if("000000".equals(result.get("statusCode"))){
//			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				System.out.println(key +" = "+object);
//			}
//		}else{
//			//异常返回输出错误码和错误信息
//			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//		}
//	}

}
