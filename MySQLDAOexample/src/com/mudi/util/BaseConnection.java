package com.mudi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class BaseConnection {
	//与数据库建立连接方法
	public static Connection getConnection() {
		Connection connection = null;
		try {
			//将真正的Connector/J驱动类生成实例
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//调用自动加载驱动生成的实例，创建连接对象，建立连接时应捕获SQLException异常
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal", "root", "mamudi");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//关闭一个资源方法
	public static void closeResource(Connection conn, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//关闭三个资源方法
	public static void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
