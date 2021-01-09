package jsu.lnn.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jsu.lnn.entity.Question;
import jsu.lnn.entity.User;

public class InsertData {
	private static String insertUser = "insert into user(id,name,password) values (?,?,?)";
	private static String insertQuestion = "insert into question values (?,?,?,?,?)";
	private static String showUser = "select name from user";
	public static int insertUser(User user){
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(insertUser);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPasswd());
			int rows = statement.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{
			JDBCUtils.close(statement);
			JDBCUtils.close(connection);
		}
	}
	public static int insertQuestion(Question question){
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(insertQuestion);
			String answers = pareAnswer(question);
			statement.setString(1, question.getTitle());
			statement.setString(2,answers);
			statement.setString(3, question.getOptions().get(0)+"@" +question.getOptions().get(1)+"@"
							+question.getOptions().get(2)+"@" +question.getOptions().get(3));
			statement.setInt(4, question.getScore());
			statement.setInt(5, question.getLevel());
			int rows = statement.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{
			JDBCUtils.close(statement);
			JDBCUtils.close(connection);
		}
	}
	private static String pareAnswer(Question question) {
		String answer = "";
		List<Integer> list = question.getAnswers();
		for (int i = 0; i < list.size(); i++) {
			answer+=list.get(i)+"@";
		}
		return answer;
	}
	public static void showUsers(){
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(showUser);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{
			JDBCUtils.close(statement);
			JDBCUtils.close(resultSet);
			JDBCUtils.close(connection);
		}
	}
}
