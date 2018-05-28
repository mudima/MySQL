package com.mudi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mudi.bean.AnType;
import com.mudi.util.BaseConnection;


public class AnTypeDAO {
	//查询anType表的方法
	public ArrayList<AnType> getList() {
		ArrayList<AnType> al = new ArrayList<>();
		java.sql.Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from an_type;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				AnType at = new AnType();
				at.setAnId(rs.getInt("anId"));
				at.setAnName(rs.getString("anName"));
				al.add(at);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseConnection.closeResources(conn, ps, rs);
		}
		return al;
	}
}
