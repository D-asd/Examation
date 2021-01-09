package jsu.lnn.service1;//功能包

import java.util.List;

import jsu.lnn.entity.ExamInfo;
import jsu.lnn.entity.QuestionInfo;
import jsu.lnn.entity.User;

/** 软件的核型功能： 登录 ， 开始(发卷),交卷
 * 业务层 
 */
public interface ExamService {// 接口方法一定是公有且抽象
	User login(int id, String pwd) 
		throws IdOrPwdException;

	ExamInfo start();
	
	QuestionInfo getQuestion(int index);

	void saveUserAnswers(int index, List<Integer> userAnswers);

	int send();

	int getScore();
	
//	String getMessage();
}
