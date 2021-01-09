package jsu.lnn.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**系统工具类*/
public class Utils {
	/**
	 * 远程方法调用
	 * @param host 远程主机
	 * @param port 端口号
	 * @param rep 方法请求
	 * @return 运行结果
	 */
	public static Response remoteCall(String host,int port, Request req){
		try{
			Socket s = new Socket(host,port);//建立连接
			//发送请求
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			//System.out.println("remoteCall request:"+req);
			//写入
			out.writeObject(req);
			out.flush();
			//获取响应
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			Response res = (Response)in.readObject();
			//System.out.println("remoteCall Response:"+res);
			s.close();
			return res;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 调用对象的方法
	 * @param obj 是被调用对象
	 * @param req 是请求值对象，包含方法名，参数类型列表，参数列表
	 * @retrun Response  对象，是返回值，异常的打包
	 */
	public static Response call(Object/*给谁做*/ obj,Request/*请求/读到的*/ req){
		Response res = new Response();
		try{
			Class cls = obj.getClass();
			Method method = cls.getDeclaredMethod(
					req.getMethod(),req.getParamTypes());
			//用户值
			Object val = method.invoke(obj, req.getParams());
			res.setValue(val);
			return res;
		}catch(InvocationTargetException e){
			e.printStackTrace();
			res.setException((Exception)e.getTargetException());
		}catch(Exception e){
			e.printStackTrace();
			res.setException(e);
		}
		return res;
	}
}
