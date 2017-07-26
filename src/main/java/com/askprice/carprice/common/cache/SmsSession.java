package com.askprice.carprice.common.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SmsSession
{
	private Map<String, SmsCode> sms = new HashMap<>();
	
	public SmsSession()
	{
	}
	
	public void put(String requestId, SmsCode code)
	{
		sms.put(requestId, code);
	}
	
	public void remove(String requestId)
	{
		sms.remove(requestId);
	}
	
	public SmsCode getCode(String requestId)
	{
		return sms.get(requestId);
	}

}


