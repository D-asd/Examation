package jsu.lnn.ui;//控制器与视图关联密切 so 写在视图包里

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jsu.lnn.entity.ExamInfo;
import jsu.lnn.entity.QuestionInfo;
import jsu.lnn.entity.User;
import jsu.lnn.net.FileUtil;
import jsu.lnn.service1.ExamService;
import jsu.lnn.service1.IdOrPwdException;

/** 客户界面控制器： 客户端上下文环境 */
public class ClientContext implements Serializable{
	//private LoginFrame loginFrame = new LoginFrame();//自己作的
	private static final long serialVersionUID = -7691963212048016326L;
	//对象注入
	private LoginFrame loginFrame;
	//别人给的  跟合理    
	/** 依赖注入(IOC) 这里注入的是loginFrame是实例 */
	public void setLoginFrame(LoginFrame loginFrame){
		this.loginFrame = loginFrame;
		//如果不加会显示NullpointerException
	}
	
	private ExamService examService;
	public void setExamService(ExamService examService){
		this.examService = examService;
	}
	
	private MenuFrame menuFrame;
	public void setMenuFrame(MenuFrame menuFrame){
		//System.out.println("menuFrame:"+menuFrame);
		//变量的“权限定名”
		jsu.lnn.ui.ClientContext.this.menuFrame = menuFrame;
		//this.menuFrame = menuFrame;
	}
	
	private ExamFrame examFrame;
	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}
	private WelcomeWindow welcomeWindow;
	public void setWelcomeWindow(WelcomeWindow welcomeWindow) {
		this.welcomeWindow = welcomeWindow;
	}
	
	/**
	 * 此方法被login按钮调用！
	 * 登录系统，控制逻辑
	 *  1 从登录界面获得用户的ID 和 pwd
	 *  2 调用业务模型的login方法完成登录功能
	 *  3 根据登录的结果，如果成功就 更新菜单界面，显示用户信息
	 *  	关闭登录界面，打开菜单界面
	 *  4 如果登录失败就在登录界面上显示失败消息
	 */
	public void login(){
		try{
			//从登录界面获取ID和pwd
			int id = loginFrame.getUserId();
			String pwd = loginFrame.getPassword();
			//调用业务模型的登录功能
			System.out.println("examService:"+examService);
			//examService 接口类型
			User loginUser = examService.login(id, pwd);//引用类型要动态绑定到 对象/类
			//调试语句
			//System.out.println("login():menuFrame:"+menuFrame);
			//System.out.println("login():user:"+user);
			//更新菜单界面,显示登录用户信息
			menuFrame.updateView(loginUser);
			//如果登录成功就关闭登录界面，打开菜单界面
			loginFrame.setVisible(false);
		
			menuFrame.setVisible(true);
		}catch(IdOrPwdException e){
			e.printStackTrace();
			//如果登录失败就留在登录界面，并且在界面上提示错误
			if(e.getMessage().equals("管理员登陆成功")){
				loginFrame.showMessage("管理员登陆成功");
			}else{
				loginFrame.showMessage("登录失败"+e.getMessage());
			}
			
			//在setText() 	定义此组件将要显示的单行文本
		}catch(NumberFormatException e){
			e.printStackTrace();
			loginFrame.showMessage("登录失败"+e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			loginFrame.showMessage("登录失败"+ e.getMessage());
		}
	}
//	public void login1(){
//		System.out.println("login");
//		int id =loginFrame.getUserId();
//		String pwd = loginFrame.getPassword();
//		//User user = userDao.fin
//		catch(Exception e){
//			//显示错误界面。。。。
//		}
//	}
	/**
	 * 显示软件的开始界面 ， 先显示登录界面
	 * show()方法的运行，必须依赖于loginFrame变量引用具体的界面对象，
	 * 这个界面对象必须依赖方法setLoginFrame() 注入，注入这个界面对象实例！
	 */
	public void show(){//如果变量为空会出现空指针异常
		//center(welcomeWindow);
		center(loginFrame);
		center(menuFrame);
		center(examFrame);
		//welcomeWindow.setVisible(true);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				//welcomeWindow.setVisible(false);
				loginFrame.setVisible(true);
				timer.cancel();
			}
		},2000);
	}
	// 设置居中  也可以  setLocationRelativeTo(null);
	private void center(Window win){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - win.getWidth()) /2;
		int y = (screen.height - win.getHeight()) /2;
		win.setLocation(x, y);
	}
	
	/**
	 * 退出系统  控制逻辑
	 * @param source  代表从哪一个界面退出，是一个窗口的引用
	 */
	public void exit(JFrame source){
		//confirm 确认
		//Dialog 对话框
		//当在前面的对话框 模太对话框
		int val = 
			JOptionPane.showConfirmDialog(source, "离开吗？");
		//挡在source 界面的前面
		if(val == JOptionPane.YES_OPTION){
			System.exit(0);//结束当前Java进程  参数习惯写0表示正确退出(只要是整数都可以，
			//习惯0表示正常对出)
			//java 全部前台线程都结束后 进程才会结束
			//图形界面的线程较多，经常会进程不结束
		}
	}
	
	/**考试界面*/
	public void start(){
		try{
			//调用业务层方法开始考试，返回考试信息详情
			ExamInfo examInfo = examService.start();//生成考卷
			//调用业务层方法取回第一道题
			QuestionInfo questionInfo = 
				examService.getQuestion(0);
			//保持当前题目状态
			currentQuestionInfo = questionInfo;
			//保持考试信息状态
			this.examInfo = examInfo;
			//更新ExamFrame显示考试信息，和第一道题
			examFrame.updateView(examInfo,questionInfo);
//			关闭菜单界面
			menuFrame.setVisible(false);
			//打开考试界面
			examFrame.setVisible(true);
			
			startTimer();
		}catch(Exception e){
			//一旦出现异常 会显示消息对话框
			e.printStackTrace();
			JOptionPane.showMessageDialog(
					menuFrame, e.getMessage());
			menuFrame.repaint();
			//重绘界面
		}
	}
	private QuestionInfo currentQuestionInfo;
	private ExamInfo examInfo;
	private Timer timer;
	
	public void prev(){
		try{
			//取得当前的题号
			int index = currentQuestionInfo.getQuestionIndex();
			//保存当前考题的用户答案到业务层
			List<Integer> userAnswers = examFrame.getUserAnswers();
			examService.saveUserAnswers(index,userAnswers);
			//取得上一题目
			QuestionInfo questionInfo = examService.getQuestion(index-1);
			currentQuestionInfo = questionInfo;
			//更新考试界面，显示上一题目
			examFrame.updateView(examInfo, questionInfo);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(examFrame, e.getMessage());
		}
	}
	
	public void next(){
		try{
			//取得当前的题号
			int index = currentQuestionInfo.getQuestionIndex();
			//保存当前考题的答案到业务层
			List<Integer> userAnswers = examFrame.getUserAnswers();
			examService.saveUserAnswers(index,userAnswers);
			//取得下一题目
			QuestionInfo questionInfo = examService.getQuestion(index+1);
			currentQuestionInfo = questionInfo;
			//更新考试界面，显示下一题目
			examFrame.updateView(examInfo, questionInfo);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(examFrame, e.getMessage());
		}
	}
	/** 交卷 */
	public void send(){
		int val = JOptionPane.showConfirmDialog(examFrame, "有木有打算交卷？");
		if(val == JOptionPane.YES_OPTION){
			gameOver();
		}
	}
	public void gameOver(){
		try{
			int index = currentQuestionInfo.getQuestionIndex();
			//获取最后的用户答案
			List<Integer> userAnswers = examFrame.getUserAnswers();
			//保存用户答案
			examService.saveUserAnswers(index,userAnswers);
			//交卷处理
			int score = examService.send();
			//显示分数
			timer.cancel();
			JOptionPane.showMessageDialog(examFrame, "分数："+score);
			//关闭考试界面
			examFrame.setVisible(false);
			//记录考试分数
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String[] records = {loginFrame.getUserId()+"",score+"",df.format(new Date())};
			FileUtil.addFile("record.txt",records);
			//返回菜单界面
			menuFrame.setVisible(true);
			timer.cancel();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(examFrame, e.getMessage());
		}
	}
	public void result(){
		try{
			int score = examService.getScore();
			JOptionPane.showMessageDialog(menuFrame, "分数："+score);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	public void msg(){
		try{
//			String message = examService.getMessage();
			JOptionPane.showMessageDialog(menuFrame, "不说话\n不抄袭\n诚信考试\n诚实做人");
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	private void startTimer(){
		timer = new Timer();
		//当前时间
		long start = System.currentTimeMillis();
		//结束时间  ＝ 当前时间+
		final long end = examInfo.getTimeLimit()*60*1000+start;
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				//show是需要显示的剩余时间
				long show = end-System.currentTimeMillis();
				long h = show/1000/60/60;
				long m = show/1000/60%60;
				long s = show/1000%60;
				examFrame.updateTime(h, m, s);
			}
		}, 0,1000);
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				gameOver();//提前交卷
			}
		}, new Date(end));
	}
	
}
