package com.askprice.carprice.common.util;

public class RemoteAPI {
	
	public static String URL = "http://partner.bitauto.com/API/Common/Handler.ashx";
	
	public static String AuthorizeCode = "92D20FFC-3F5B-4C59-8E9F-D13EF26B6035";
	
	public static String InterfaceID = "35";
	
	public static enum APIOperation
	{
		recommend_dealers_csid,recommend_dealers_carid;
	}
	
	public static enum CommonParams
	{
		AuthorizeCode,InterfaceID,op
	}

}
