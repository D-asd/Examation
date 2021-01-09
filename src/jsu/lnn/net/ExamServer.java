package jsu.lnn.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jsu.lnn.entity.EntityContext;
import jsu.lnn.entity.EntityContextDaoImpl;
import jsu.lnn.service1.ExamService;
import jsu.lnn.service1.ExamServiceImpl;
import jsu.lnn.util.Config;

/** 考试服务器*/
public class ExamServer {
	private Config config;
	/**服务列表，每个SID 对应一个ExamService实例，每个客户对应一个SID*/
	Map<String,ExamService> serviceMap = 
		new HashMap<String, ExamService>();
	private EntityContext entityContext;
	public void setEntityContext(EntityContext entityContext){
		this.entityContext = entityContext;
	}
	public ExamServer(){
	}
	public void setConfig(Config config){
		this.config = config;
	}
	public void start(){
		int port = config.getInt("ServerPort");
		try{
			ServerSocket ss = new ServerSocket(port);
			while(true){
				//等待客户端的连接
				Socket s = ss.accept();
				new Service(s).start();
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	class Service extends Thread{
		Socket s;
		public Service(Socket s){
			this.s = s;
		}
		@Override
		public void run() {
			try{
				//接受请求
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				Request req = (Request)in.readObject();
				//处理一
				//String obj = "ABCD"
				//根据用户请求 req ，找到对应SessionID的 考试服务对象
				//一个考试服务对象对应于一个考试客户端(一个考生)
				//如果请求req中没有SessionID   getService()方法会创建
				//新的SessionID和ExamService 实力
				ExamService service = getService(req);
				//res   调用service方法的req方法
				Response res = Utils.call(service,req);
				res.setSessionID(req.getSessionID());
				//发送响应
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				out.writeObject(res);
				//断开连接
				s.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private ExamService getService(Request req){
		String sid = req.getSessionID();
		if(sid == null){//如果没有SID 就创建新的
			//java系统提供的永不重复的字符串
			sid = UUID.randomUUID().toString();
			req.setSessionID(sid);
		}
		ExamService service = serviceMap.get(sid);
		if(service == null){//第一次访问服务器，需要创建新的service
			ExamServiceImpl serviceImpl = new ExamServiceImpl();
			serviceImpl.setEntityContext(entityContext);
			serviceMap.put(sid, serviceImpl);
			service = serviceImpl;
		}
		return service;
	}
	/**启动服务器*/
	public static void main(String[] args){
		ExamServer server = new ExamServer();
		Config config = new Config("server.properties");
		EntityContextDaoImpl entityContext = new EntityContextDaoImpl();
		server.setConfig(config);
		server.setEntityContext(entityContext);
		server.start();
	}
	
}
