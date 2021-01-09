package jsu.lnn.net;

import java.util.List;

import jsu.lnn.entity.ExamInfo;
import jsu.lnn.entity.QuestionInfo;
import jsu.lnn.entity.User;
import jsu.lnn.service1.ExamService;
import jsu.lnn.service1.IdOrPwdException;
import jsu.lnn.util.Config;
/** 考试服务代理客户端 */
public class ExamServiceAgent implements ExamService{
	private Config config;
	private String host;
	private String sid;
	private int port;
	
	public void setConfig(Config config){
		this.config = config;
		this.host = config.getString("ServerIP");
		this.port = config.getInt("ServerPort");
	}
//	登录按钮
	public User login(int id,String pwd) throws IdOrPwdException{
		//拿到ID列表请求
		Request req = new Request("login",
				//参数类型列表
				new Class[]{int.class,String.class},
				//参数列表
				new Object[]{id,pwd});
//		将请求封装成req
		req.setSessionID(sid);
//		远程传递  remoteCall
		Response res = Utils.remoteCall(host,port,req);
		sid = res.getSessionID();
		if(res.isSuccess()){
			return (User)res.getValue();//把服务器结果作为返回值
		}else{
			Exception e = res.getException();
			if(e instanceof IdOrPwdException){
				throw (IdOrPwdException)e;
			}else{
				throw new RuntimeException(e); 
			}
		}
	}
	public QuestionInfo getQuestion(int index){
		Request req = new Request("getQuestion",
				new Class[]{int.class},
				new Object[]{index});
		req.setSessionID(sid);
		Response res = Utils.remoteCall(host, port, req);
		sid = res.getSessionID();
		if(res.isSuccess()){
			return (QuestionInfo)res.getValue();
		}else{
			Exception e = res.getException();
			throw new RuntimeException(e);
		}
	}
	public int getScore(){
		Request req = new Request("getScore",
				new Class[]{},
				new Object[]{});
		req.setSessionID(sid);
		Response res = Utils.remoteCall(host, port, req);
		sid = res.getSessionID();
		if(res.isSuccess()){
			return (Integer)res.getValue();
		}else{
			Exception e = res.getException();
			throw new RuntimeException(e);
		}
	}
	public void saveUserAnswers(int index,List<Integer> userAnswers){
		Request req = new Request("saveUserAnswers",
				new Class[]{int.class,List.class},
				new Object[]{index,userAnswers});
		//System.out.println(req);
		req.setSessionID(sid);//请求时发送的id
		
		Response res = Utils.remoteCall(host, port, req);
		sid = res.getSessionID();//返回时带的id
		if(res.isSuccess()){
			//return (Integer)res.getValue();
		}else{
			Exception e= res.getException();
			throw new RuntimeException(e);
		}
	}
	public int send(){
		Request req = new Request("send",
				new Class[]{},
				new Object[]{});
		req.setSessionID(sid);
		Response res = Utils.remoteCall(host, port, req);
		sid = res.getSessionID();
		if(res.isSuccess()){
			return (Integer)res.getValue();
		}else{
			Exception e = res.getException();
			throw new RuntimeException(e);
		}
	}
	public ExamInfo start(){
		Request req = new Request("start",
				new Class[]{},
				new Object[]{});
		req.setSessionID(sid);
		Response res = Utils.remoteCall(host, port, req);
		sid = res.getSessionID();
		if(res.isSuccess()){
			return (ExamInfo)res.getValue();
		}else{
			Exception e = res.getException();
			throw new RuntimeException(e);
		}
	}
	
}
