package jsu.lnn.entity;

import java.util.List;

public interface EntityContext {
	/**
	 * 通过用户的Id  查找
	 * @param id
	 * @return
	 */
	User findUserById(int id);
	/**
	 * 根据试题的等级   得到试题的集合
	 * @param level
	 * @return
	 */
	List<Question> findQuestion(int level);
	/**
	 * 从配置文件中 得到考试时间 
	 * @return
	 */
	int getTimeLimit();
	/**
	 * 从配置文件中得到考试信息 
	 */
	String getTitle();
}
