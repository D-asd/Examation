package jsu.lnn.ui;

import jsu.lnn.entity.EntityContextDaoImpl;
import jsu.lnn.service1.ExamServiceImpl;
import jsu.lnn.util.Config;

public class Main {
	public static void main(String[] args){
		//创建所有对象，注入，解决依赖关系
		LoginFrame loginFrame = new LoginFrame();
		MenuFrame menuFrame  = new MenuFrame();
		ExamFrame examFrame = new ExamFrame(); 
		WelcomeWindow welcomeWindow = new WelcomeWindow();
		ClientContext clientContext = new ClientContext();
		
//		ExamService examService = new ExamService(){
//			public User login(int id,String pwd) 
//				throws IdOrPwdException{
//				if(id == 1 && pwd.equals("1234")){
//					return new User("Robin",1,"1234");
//				}
//				throw new IdOrPwdException("登录错误！");
//			}
//		};
		//使用show()显示登录界面
		ExamServiceImpl examService = new ExamServiceImpl();
		//ExamServiceAgent examService = new ExamServiceAgent();
		//Config config = new Config("client.properties");
		EntityContextDaoImpl entityContext = new EntityContextDaoImpl();
		
		
		//按照依赖关系组装
		loginFrame.setClientContext(clientContext);
		clientContext.setLoginFrame(loginFrame);
		menuFrame.setClientContext(clientContext);
		clientContext.setMenuFrame(menuFrame);
		examFrame.setClientContext(clientContext);
		clientContext.setExamFrame(examFrame);
		examService.setEntityContext(entityContext);
		//将entityContext的引用 给 examFrame 
		clientContext.setWelcomeWindow(welcomeWindow);
		clientContext.setExamService(examService);
		
		
		clientContext.show();
	}
}
