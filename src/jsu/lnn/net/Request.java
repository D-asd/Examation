package jsu.lnn.net;

import java.io.Serializable;
import java.util.Arrays;
/**请求*/
public class Request implements Serializable{
	private static final long serialVersionUID = 597071526803100317L;
	/**会话ID*/
	private String sessionID;
	/** 方法名 */
	private String method;
	/**参数类型列表*/
	private Class[] paramTypes;
	/**参数列表*/
	private Object[] params;
	
	public Request(){
	}
	
	public Request(String method,Class[] paramTypes,Object[] params){
		this.method = method;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+sessionID+","+method+Arrays.toString(paramTypes)+Arrays.toString(params)+"]";
	}
}
