package jsu.lnn.service1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jsu.lnn.entity.EntityContext;
import jsu.lnn.entity.ExamInfo;
import jsu.lnn.entity.Question;
import jsu.lnn.entity.QuestionInfo;
import jsu.lnn.entity.User;
import jsu.lnn.service.AdminFrame;
/**核心业务功能的实现类
 * 软件业务实现，实现软件核心业务功能 login 方法
*/
public class ExamServiceImpl implements ExamService{
	
	private EntityContext entityContext;
	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}
	
	public User login(int id,String pwd) 
		throws IdOrPwdException{
		
		User user = entityContext.findUserById(id);
		if("100".equals(id+"")&&"123".equals(pwd)){
			AdminFrame admin = new AdminFrame();
			admin.setVisible(true);
			throw new IdOrPwdException("管理员登陆成功");
		}else if(user == null){
			throw new IdOrPwdException("无用户！");
		}else if(user.getPasswd().equals(pwd)){
			this.loginUser = user;
			return user;//登录成功
		}else{
			throw new IdOrPwdException("密码错误！");
			
		}
		

	}
	
	/** 考卷，考卷是考题的线性表集合*/
	private List<QuestionInfo> paper = new ArrayList<QuestionInfo>();
	
	private User loginUser;  //登录用户
	
	private void createPaper(){
		//每个级别抽取两道考题
		Random random = new Random();
		int i = 0;
		//level 代表每个级别
		for(int level = Question.LEVEL1;level <= Question.LEVEL10;level++){
			//去数据层查找题目  两道题不能重复
			List<Question> list = entityContext.findQuestion(level);
			//int index = random.nextInt(list.size());//1-10
			//Question q1 = list.remove(index);
			Question q1 = list.remove(random.nextInt(list.size()));
			//remive 删除   抽出被删除的一个  即可理解为     从数据层中抽出了一道题
			Question q2 = list.remove(random.nextInt(list.size()));
			paper.add(new QuestionInfo(i++,q1));
			paper.add(new QuestionInfo(i++,q2));
		}
	}
	public ExamInfo start(){
		if(finish){
			throw new RuntimeException("考试已经结束啦！");
		}
		//创建/组织考卷
		createPaper();
		//创建考试信息对象，阻止考试信息
		ExamInfo examInfo = new ExamInfo();
		//考卷的大小 即 题目的数量
		examInfo.setQuestionCount(paper.size());
		examInfo.setTimeLimit(entityContext.getTimeLimit());
		examInfo.setTitle(entityContext.getTitle());
		examInfo.setUser(loginUser);//当前系统登录用户	
		//返回考试信息
		return examInfo;
	}
	//取第几道题
	public QuestionInfo getQuestion(int index) {
		// TODO Auto-generated method stub
		return paper.get(index);
	}

	public int getScore() {
		if(! finish){
			throw new RuntimeException("还没有考试！");
		}
		return score;
	}
//	public String getMessage(){
//		return entityContext;
//	}
//	private String message;

	public void saveUserAnswers(int index, List<Integer> userAnswers) {
		QuestionInfo q = paper.get(index);
		q.getUserAnswers().clear();
		q.getUserAnswers().addAll(userAnswers);
	}
	
	private int score;
	private boolean finish = false;
	public int send(){
		if(finish){
			throw new RuntimeException("考试已经结束！");
		}
		score = 0;
		for(QuestionInfo info: paper){
			Question q = info.getQuestion();
			List<Integer> answers = q.getAnswers();
			List<Integer> userAnswers = info.getUserAnswers();
			if(answers.equals(userAnswers)){
				score += q.getScore();
			}
		}
		finish = true;
		return score;
	}

}
