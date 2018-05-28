package com.mudi.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;


public class BaseDAO {
	public ArrayList getList(Class cl) {
		ArrayList ar = new ArrayList();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from " + cl.getSimpleName();
		Field[] fields = cl.getDeclaredFields();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object object = cl.newInstance();
				for (Field f : fields) {
					f.setAccessible(true);
					f.set(object, rs.getObject(f.getName()));
				}
				ar.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeResources(conn, ps, rs);
		}
		
		return ar;
	}
	
	//通过主键查询某一项的方法
	public Object getObjectById(Class cl, int id) {
		Object object = null;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fields = cl.getDeclaredFields();
		String sql = "select * from " + cl.getSimpleName() + " where " + fields[0].getName() + " = " + id;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				object = cl.newInstance();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(object, rs.getObject(field.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeResources(conn, ps, rs);
		}
		return object;
	}
	
	//通过特定条件查询方法
	public ArrayList getListByCondition(Class cl, String column, Object value) {
		ArrayList ar = new ArrayList<>();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from " + cl.getSimpleName() + " where " + column + " = '" + value +"'";
		Field[] fields = cl.getDeclaredFields();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object object = cl.newInstance();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(object, rs.getObject(field.getName()));
				}
				ar.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeResources(conn, ps, rs);
		}
		return ar;
	}
	
	//insert into query
	public boolean insert(Object obj) {
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = obj.getClass();
		Field[] fields = cl.getDeclaredFields();
		//sql: insert into tableName (col1, col2, col3) values (?, ?, ?);
		//stringbuffer is more efficient
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(cl.getSimpleName());
		sql.append("(");
		for (int i = 1; i < fields.length; i++) {
			sql.append(fields[i].getName());
			if (i != (fields.length - 1)) {
				sql.append(", ");
			}
		}
		sql.append(") values (");
		for (int i = 1; i < fields.length; i++) {
			sql.append("?");
			if (i != (fields.length - 1)) {
				sql.append(", ");
			}
		}
		sql.append(");");
		
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 1; i < fields.length; i++) {
				fields[i].setAccessible(true);
				ps.setObject(i, fields[i].get(obj));
			}
			int a = ps.executeUpdate();
			if (a > 0) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseConnection.closeResource(conn, ps);
		}
		return b;
	}
	
	//update
	public boolean update(Object ob){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = ob.getClass();
		Field[] fi = cl.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		//update animals set name = ?,age = ?,anid = ? where id = ?
		sb.append(" update ");
		sb.append(cl.getSimpleName());
		sb.append(" set ");
		for(int i = 1;i<fi.length;i++){
			fi[i].setAccessible(true);
			sb.append(fi[i].getName());
			sb.append(" = ? ");
			if(i!=fi.length-1){
				sb.append(" , ");
			}
		}
		sb.append(" where ");
		sb.append(fi[0].getName());
		sb.append("=?");
		
		try {
			ps = conn.prepareStatement(sb.toString());
			for(int i = 1;i<fi.length;i++){
				fi[i].setAccessible(true);
				ps.setObject(i, fi[i].get(ob));
			}
			fi[0].setAccessible(true);
			ps.setObject(fi.length, fi[0].get(ob));
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
	
	//delete
	public boolean delete(Class cl , int id){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fi = cl.getDeclaredFields();
		String sql = "delete from "+cl.getSimpleName()+" where "+fi[0].getName()+" = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, id);
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeResource(conn, ps);
		}
	return b ;
	
	}
	//delete by conditions
	public boolean deleteBySome(Class cl , String name,Object value){
		boolean b = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fi = cl.getDeclaredFields();
		String sql = "delete from "+cl.getSimpleName()+" where "+name+" = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, value);
			int a = ps.executeUpdate();
			if(a>0){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeResource(conn, ps);
		}
	return b ;
	
	}
	
	
}
