package jsu.lnn.entity;

import java.io.Serializable;

/**
 * 考试信息值对象
 * 是值对象
 *
 */
public class ExamInfo implements Serializable{
	private static final long serialVersionUID = 7171922215075641740L;
	/** 考试科目 */
	private String title;
	/** 考生 */
	private User user;
	/** 分钟为单位*/
	private int timeLimit;
	private int questionCount;
	
	public ExamInfo(){
	}
	public ExamInfo(String title,User user,int timeLimit,int questionCount){
		super();
		this.title = title;
		this.user = user;
		this.timeLimit = timeLimit;
		this.questionCount = questionCount;
	}
	public String toString(){
		//System.out.println("ExamInfo.toString():");
		if(user == null){
			//System.out.println("ExamInfo.toString(): user is null");
			return "无信息！";
		}
		//System.out.println("ExamInfo.toString():user isn't null");
		return "姓名：" + user.getName()+
			"   编号：" + user.getId()+
			"   考试时间：" + timeLimit + "分钟"+
			"   考试科目：" + title +
			"   题目数量：" +questionCount;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
