package com.askprice.carprice.common.cache;

import java.util.Random;

public class SmsCode
{
	private String phone;

	private String code;
	
	private Long sendTime;
	
	private Integer expiredMinutes;
	
	

	public SmsCode()
	{}
	
	public SmsCode(String phone, Integer smsTimeOutMinute)
	{
		this.setPhone(phone);
		this.setSendTime(System.currentTimeMillis());
		this.setExpiredMinutes(smsTimeOutMinute);
		this.setCode(generateRandomSmsCode());
	}
	
	public static String generateRandomSmsCode()
  	{
  		String str = "0123456789";  
  	    Random random = new Random();  
  	    StringBuffer buf = new StringBuffer();  
  	    for (int i = 0; i < 6; i++) {  
  	        int num = random.nextInt(10);  
  	        buf.append(str.charAt(num));  
  	    }  
  	    return buf.toString();
  	}
	
	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Long getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(Long sendTime)
	{
		this.sendTime = sendTime;
	}

	public Integer getExpiredMinutes()
	{
		return expiredMinutes;
	}

	public void setExpiredMinutes(Integer expiredMinutes)
	{
		this.expiredMinutes = expiredMinutes;
	}
	
	public boolean isExpired() 
	{
		Long expTime = this.sendTime + this.expiredMinutes * 60 * 1000;
		Long curTime = System.currentTimeMillis();
		
		return (curTime >= expTime);
	}
	
}
