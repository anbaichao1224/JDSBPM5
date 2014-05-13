package com.kzxd.xzsp.action;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class WsClientManager {
	private static String endpoint = "";
	public static void setEndPoint(String epoint){
		endpoint = epoint;
	}
	public static Object call(String epoint,String method,String[] params) throws Exception{
		setEndPoint(epoint);
		return call(method, params);
	}
	public static Object call(String method,String[] params) throws Exception{
		Object rObj = new Object();
		Service service = new Service();
		Call call;
		call = (Call) service.createCall();
		call.setTargetEndpointAddress(endpoint);
		call.setOperationName(method); 
		rObj = call.invoke(params);
		return rObj;
	}
}
