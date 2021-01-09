package jsu.lnn.net;

import java.io.Serializable;
/**返回*/
public class Response implements Serializable{
	private static final long serialVersionUID = -3632449302389103065L;
	private String sessionID;
	
	private Object value;
	private Exception e ;
	public Response(){
	}
	public Response(Object value){
		this.value =value;
	}
	public Response(Exception e){
		this.e = e;
	}
	public Exception getException() {
		return e;
	}
	public void setException(Exception e) {
		this.e = e;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean isSuccess(){
		return e == null;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(e == null){
			return sessionID + ","+value;
		}else{
			return sessionID + ","+e;
		}
	}
}
