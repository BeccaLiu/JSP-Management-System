package cn.usermanage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.usermanage.vo.ManagerVo;
import com.usermanage.vo.UserInfoVo;

import cn.usermanage.dao.ManagerDao;
import cn.usermanage.db.DBConnection;

public class ManagerDaoImpl implements ManagerDao {
	private static ManagerDaoImpl instance = new ManagerDaoImpl();

	private ManagerDaoImpl() {
	}

	public static ManagerDaoImpl getInstance() {
		return instance;
	}

	public ManagerVo passchange(String managerName,String pass) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update manage set manage_pass=? where manage_name=? ");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ManagerVo managerVo = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,pass );
			pstmt.setString(2, managerName);
			pstmt.executeUpdate();
			managerVo = new ManagerVo();
			managerVo.setManage_pass(pass);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return managerVo;
	}
	
	public void loginOut() {

	}

	public ManagerVo login(String managerName,String pass) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from manage where manage_name = ? and manage_pass = ? ");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		ManagerVo managerVo = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, managerName);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				managerVo = new ManagerVo();
				String manage_name = rs.getString("manage_name");
				String manage_pass = rs.getString("manage_pass");
				String manage_role = rs.getString("manage_role");
				managerVo.setManage_name(manage_name);
				managerVo.setManage_pass(manage_pass);
				managerVo.setManage_role(manage_role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return managerVo;
	}

}
