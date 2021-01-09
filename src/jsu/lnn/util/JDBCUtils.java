package jsu.lnn.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static String url;
	private static String dbUser;
	private static String dbpassword;
	private static String driver;
	
	/**
	 * 从配置文件中解析数据 并给
	 * url dbUser dbPassword 赋值
	 */
	static {
		Properties table = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream("db.properties");
			table.load(file);
			url = table.getProperty("url");
			dbUser = table.getProperty("dbUser");
			dbpassword = table.getProperty("dbPassword");
			driver = table.getProperty("driver");
			Class.forName(driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		JDBCUtils.getConnection();
	}
	/**
	 * 获取数据库的连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			/*
			 * 将oracle.jdbc.OracleDriver 类 装入内存
			 * OracleDriver 的 static 代码块随即运行
			 * static 代码块会向DriverManger 注册 oracle 的驱动类 
			 * JDBC 的 API 通过 该驱动类 找到其他实现 
			 */
			connection = DriverManager.getConnection(url,dbUser,dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 关闭连接
	 * @param connection
	 */
	public static void close(Connection connection){
		if(connection != null){
			try {
				connection.close();
				System.out.println("连接关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭语句对象
	 * @param statement
	 */
	public static void close(Statement statement){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭结果集
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 设置事务是否自动提交
	 * @param connection
	 * @param autoCommit
	 */
	public static void setAutoCommit(Connection connection ,boolean autoCommit){
		try {
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手动提交事务
	 * @param connection
	 */
	public static void commit(Connection connection){
		try {
			if(connection.getAutoCommit()){
				return;
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 事务回滚
	 * @param connection
	 */
	public static void rollBack(Connection connection){
		try {
			if(connection.getAutoCommit()){
				return;
			}
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}