package com.askprice.carprice.common.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SmsSession
{
	private Map<String, SmsCode> sms = new HashMap<>();
	
	private ReentrantLock lock = new ReentrantLock();
	
	public SmsSession()
	{
	}
	
	public void put(String requestId, SmsCode code)
	{
		lock.lock();
		if (sms.size() > 1000) 
		{
			removeExpiredSms();
		}
		sms.put(requestId, code);
		lock.unlock();
	}
	
	private void removeExpiredSms() {
		Map<String, SmsCode> tmp = new HashMap<>();
		sms.forEach((reqId, code) -> 
		{
			if(!code.isExpired()) 
			{
				tmp.put(reqId, code);
			}
		});
		
		sms.clear();
		sms.putAll(tmp);
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


