package jsu.lnn.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jsu.lnn.util.Config;
import jsu.lnn.util.JDBCUtils;

public class EntityContextDaoImpl implements EntityContext {
	private static final String findQuestionsByLevel ="select * from question where level=?";
	private static final String findUserById = "select id,name,password from user where id=?";
	private static Config config ;
	static {
		config = new Config("client.properties");
	}
	@Override
	public List<Question> findQuestion(int level) {
		List<Question> questions = new ArrayList<Question>();
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(findQuestionsByLevel);
			statement.setInt(1, level);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				String title = resultSet.getString("title");
				String answer = resultSet.getString("answer");
				String options = resultSet.getString("options");
				int score = resultSet.getInt("score");
				Question selectQuestion = parseQuestion(title,answer,options,score,level);
				questions.add(selectQuestion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(resultSet);
			JDBCUtils.close(statement);
			JDBCUtils.close(connection);
		}
		return questions;
	}

	private Question parseQuestion(String title, String answer, String options,
			int score, int level) {
		Question question = new Question();
		question.setTitle(title);
		question.setScore(score);
		question.setAnswers(parseAnswers(answer));
		question.setOptions(parseOptions(options));
		question.setLevel(level);
		question.setType(question.getAnswers().size()==1?Question.SINGLE_SELECTION:Question.MULTI_SELECTION);
		return question;
	}

	private List<String> parseOptions(String options) {
		List<String> op = new ArrayList<String>();
		for (String string : options.split("@")) {
			op.add(string);
		}
		return op;
	}

	private List<Integer> parseAnswers(String answer) {
		List<Integer> an = new ArrayList<Integer>();
		String[] as = answer.split("@");
		for (int i = 0; i < as.length-1; i++) {
			an.add(Integer.parseInt(as[i]));
		}
		return an;
	}

	@Override
	public User findUserById(int key) {
		User user = null;
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement statement = null ;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(findUserById);
			statement.setInt(1, key);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				return parseUser(id,name,password);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(resultSet);
			JDBCUtils.close(statement);
			JDBCUtils.close(connection);
		}
		return user;
	}

	private User parseUser(int id, String name, String password) {
		User user = new User();
		user.setId(id);
		user.setPasswd(password);
		user.setName(name);
		return user;
	}

	@Override
	public int getTimeLimit() {
		return config.getInt("TimeLimit");
	}

	@Override
	public String getTitle() {
		return config.getString("PaperTitle");
	}

}
