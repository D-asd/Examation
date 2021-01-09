package jsu.lnn.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsu.lnn.util.Config;
import jsu.lnn.util.InsertData;

/**
 * 实体数据管理：
 * 	 软件中的数据管理：
 * 		从磁盘(文件)加载用户和考题数据到内存集合中
 * 		用来读取数据文件放到内存集合中
 */
public class EntityContextImpl implements EntityContext{
	
	private Config config;
	
	/**
	 * 通过用户的Id  查找
	 * @param id
	 * @return
	 */
	public User findUserById(int id){
		return users.get(id);
	}
	/**
	 * 根据试题的等级   得到试题的集合
	 * @param level
	 * @return
	 */
	public List<Question> findQuestion(int level){
		//从新复制了  so 删除不影响原集合
		return new ArrayList<Question>(questions.get(level));
	}
	/**
	 * 从配置文件中 得到考试时间 
	 * @return
	 */
	public int getTimeLimit(){
		return config.getInt("TimeLimit");
	}
	/**
	 * 从配置文件中得到考试信息 
	 */
	public String getTitle() {
		return config.getString("PaperTitle");
	}
	public EntityContextImpl(Config config) {
		this.config = config;//创建前提是Config  依赖性更强
		//只要是用EntityContext 就必须加载Config
		loadUsers(config.getString("UserFile"));
		loadQuestions(config.getString("QuestionFile"));
//		loadMessage(config.getString("MessageFile"));
	}
	
	//散列表存储
	/**users 是用来存储所有用户信息的 
	 * key: 用户的ID ，value：用户实例
	 * 用户信息从磁盘文件加载
	 */
	private HashMap<Integer,User> users = 
		new HashMap<Integer,User>();
	
	/**
	 * 加载并解析用户文件内容到users集合中
	 * @param file 用户文件名
	 */
	private void loadUsers(String filename){
		try{
			BufferedReader in = new BufferedReader(//读文件
				new InputStreamReader(					//读取一行
					new FileInputStream(filename),"GBK"));
			String line;
			while((line = in.readLine())!= null){
				line = line.trim();
				if(line.startsWith("#") || line.equals("")){
					continue;//忽略空行和注释(#)
				}
				User one = parseUser(line);
				users.put(one.getId(), one);
			}
			in.close();
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	//1002:张三:1234:13810381038:zhangsan@tarena.con.cn
	//  0    1   2     3              4
	private User parseUser(String line) {
		//把一行数据解释为对象
		String[] data = line.split(":");
		User user = new User();
		user.setId(Integer.parseInt(data[0]));
		user.setName(data[1]);
		user.setPasswd(data[2]);
		user.setPhone(data[3]);
		user.setEmail(data[4]);
		return user;
	}
	
	/** 全部试题的集合
	 * key :Level  难度级别
	 * value: 是同一个级别下全部试题
	 */
//	所有的问题
	private Map<Integer,List<Question>> questions =  
			new HashMap<Integer, List<Question>>();  
	
	/** 解析试题文件，到questions集合中 */
	private void loadQuestions(String file) {
		try{
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(file),"GBK"));
			String str;
			while((str = in.readLine())!=null){
				str = str.trim();
				if(str.startsWith("#") || str.equals("")){
					continue;
				}
				//parseQuestion(str,in)解析流信息到Question 对象
				Question q = parseQuestion(str,in);
				addQuestion(q);//添加到集合
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void addQuestion(Question q){
//		if(questions.containsKey(q.getLevel())){
//			questions.get(q.getLevel()).add(q);
		//接口在运行期间会动态帮定到类上，so .add(q) 是类的
//		}else{
//			List<Question> list = new ArrayList<Question>();
//			list.add(q);
//			questions.put(q.getLevel(), list);
//		}
		List<Question> list = questions.get(q.getLevel());//难度级别
		if(list == null){  //当list 为null时
			list = new ArrayList<Question>();//在创建一个list
			questions.put(q.getLevel(),list);//将这个list加进难度级别
		}
		list.add(q);//加到题里
//		System.out.println("q:"+q);
//		System.out.println("list:"+list);
//		System.out.println("questions:" + questions);
	}
	//字符串  流
	private Question parseQuestion(String str,BufferedReader in)
		throws IOException{
		String[] data = str.split("[@,][a-z]+=");
		//@或,  a-z的字符 可多次出现
		//str:@answer = 2/3,score = 5,level = 5
		//以上字符串 切为：如下结果
		//data:{"","2/3","5","5"}
		//sun公司规定 split() 从头开始切 会出现空串
		Question q = new Question();
		//答案
		q.setAnswers(parseAnswer(data[1]));
		//分数
		q.setScore(Integer.parseInt(data[2]));
		//级别
		q.setLevel(Integer.parseInt(data[3]));
		//读取题干
		q.setTitle(in.readLine());
		
		List<String> options = new ArrayList<String>();
		//连续读取4个选项
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		q.setOptions(options);
		q.setType(q.getAnswers().size() == 1? 
				Question.SINGLE_SELECTION:
				Question.MULTI_SELECTION);
		return q;
	}
	//answer:"2/3"
	private List<Integer> parseAnswer(String answer){
		List<Integer> list = new ArrayList<Integer>();
		String[] data = answer.split("/");
		for(String s:data){
			list.add(Integer.parseInt(s));
		}
		return list;
	}
	
	
	
//	public void loadMessage(String filename){
//		try{
//			BufferedReader in = new BufferedReader(//读文件
//				new InputStreamReader(					//读取一行
//					new FileInputStream(filename),"GBK"));
//			String line;
//			while((line = in.readLine())!= null){
//				line.toString();
//			}
//			in.close();
//		}catch(IOException e){
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
//	}
	
	
	/**测试 方法*/
	public static void main(String[] args){
		//EntityContext context = new EntityContext();
		Config config = new Config("client.properties");
		EntityContextImpl context = new EntityContextImpl(config);
//		for (User user : context.users.values()) {
//			InsertData.insertUser(user);
//		}
//		InsertData.showUsers();
		Collection<List<Question>> questions = context.questions.values();
		for (List<Question> list : questions) {
			for (Question question : list) {
				InsertData.insertQuestion(question);
			}
		}
	}
}
