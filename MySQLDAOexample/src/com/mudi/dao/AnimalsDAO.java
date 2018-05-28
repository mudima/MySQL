package com.mudi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mudi.bean.Animals;
import com.mudi.util.BaseConnection;

public class AnimalsDAO {
	//查询animals表的方法
	public ArrayList<Animals> getList() {
		ArrayList<Animals> arrayList = new ArrayList<>();
		Connection connection = BaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from Animals;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Animals animal = new Animals();
				animal.setId(resultSet.getInt("id"));
				animal.setName(resultSet.getString("name"));
				animal.setAge(resultSet.getInt("age"));
				animal.setAnId(resultSet.getInt("anId"));
				arrayList.add(animal);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeResources(connection, preparedStatement, resultSet);
		}
		return arrayList;
	}
	
	//insert into针对Animals表
	public boolean insert(Animals an) {
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into Animals (name, age, anid) values (?, ?, ?);";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, an.getName());
			ps.setInt(2, an.getAge());
			ps.setInt(3, an.getAnId());
			int a = ps.executeUpdate();
			if (a > 0) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeResources(conn, ps, rs);
		}
		return b;
	}
	
	//update DAO
	public boolean update(Animals an){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update animals set name = ?,age = ?,anid = ? where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, an.getName());
			ps.setInt(2, an.getAge());
			ps.setInt(3, an.getAnId());
			ps.setInt(4, an.getId());
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeResource(conn, ps);
		}
		return b;
	}
}
