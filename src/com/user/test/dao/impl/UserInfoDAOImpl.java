package com.user.test.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.user.test.dao.UserInfoDAO;
import com.user.test.vo.UserInfoVO;

public class UserInfoDAOImpl implements UserInfoDAO {

	@Override
	public int insertUserInfoDAO(UserInfoVO userInfoVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","test","test");
			con.setAutoCommit(false);
			String sql = "insert into user_info(ui_num, ui_name, ui_age, ui_birth,\r\n" + 
					"ui_id, ui_password, ui_phone, ui_email,\r\n" + 
					" ui_credat, ui_nickname)\r\n" + 
					" values(seq_ui_num.nextval, ?, ?, ?,\r\n" + 
					" ?, ?, ?, ?,\r\n" + 
					" sysdate, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, userInfoVO.getUiName());
			ps.setInt(2, userInfoVO.getUiAge());
			ps.setString(3, userInfoVO.getUiBirth());
			ps.setString(4, userInfoVO.getUiId());
			ps.setString(5, userInfoVO.getUiPassword());
			ps.setString(6, userInfoVO.getUiPhone());
			ps.setString(7, userInfoVO.getUiEmail());
			ps.setString(8, userInfoVO.getUiNickname());
			
			int result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return 0;
	}

	@Override
	public UserInfoVO selectUserLogin(UserInfoVO userInfoVO) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","test","test");
			String sql = "select * from user_info where ui_id=? and ui_password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userInfoVO.getUiId());
			ps.setString(2, userInfoVO.getUiPassword());
			rs = ps.executeQuery();
			if(rs.next()) {
				userInfoVO.setUiNum(rs.getInt("ui_num"));
				userInfoVO.setUiName(rs.getString("ui_name"));
				userInfoVO.setUiAge(rs.getInt("ui_age"));
				userInfoVO.setUiBirth(rs.getString("ui_birth"));
				userInfoVO.setUiId(rs.getString("ui_id"));
				userInfoVO.setUiPassword(rs.getString("ui_password"));
				userInfoVO.setUiPhone(rs.getString("ui_phone"));
				userInfoVO.setUiEmail(rs.getString("ui_email"));
				userInfoVO.setUiCredat(rs.getString("ui_credat"));
				userInfoVO.setUiNickname(rs.getString("ui_nickname"));
				return userInfoVO;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return null;
	}

}
