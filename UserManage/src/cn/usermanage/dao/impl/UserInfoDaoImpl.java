package cn.usermanage.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import sun.applet.Main;

import cn.usermanage.dao.UserInfoDao;
import cn.usermanage.db.DBConnection;

import com.mysql.jdbc.ResultSet;
import com.usermanage.vo.UserInfoVo;

public class UserInfoDaoImpl implements UserInfoDao {

		private static UserInfoDaoImpl instance = new UserInfoDaoImpl();

		private UserInfoDaoImpl() {
		}

		public static UserInfoDaoImpl getInstance() {
			return instance;
		}

		/**
		 * 
		 * @param news
		 */
		public void addUser(UserInfoVo userInfo) {
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into user_info(Agent,username,password,serialnum,access,address,phone,start_date,end_date)  ");
			sql.append(" values (?,?,?,?,?,?,?,?,?) ");
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, userInfo.getAgent());
				pstmt.setString(2, userInfo.getUserName());
				pstmt.setString(3, userInfo.getPass());
				pstmt.setString(4, userInfo.getSerialnum());
				pstmt.setString(5, userInfo.getAccess());
				pstmt.setString(6, userInfo.getAddress());
				pstmt.setString(7, userInfo.getPhone());
				pstmt.setDate(8, userInfo.getStart_date());
				pstmt.setDate(9, userInfo.getEnd_date());
				pstmt.executeUpdate();
				pstmt.close();
//				System.out.println(conn.getAutoCommit());
//				conn.setAutoCommit(false);
//				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.closePreparedStatement(pstmt);
				DBConnection.closeConn(conn);
			}

		}
		
		public void updateDate(UserInfoVo userInfo) {
			StringBuffer sql = new StringBuffer();
			sql.append(" update user_info set new_date=? where id=? ");
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql.toString());
				java.sql.Date newdate =new java.sql.Date(System.currentTimeMillis());
				pstmt.setDate(1, newdate);
				pstmt.setInt(2,userInfo.getId());
				pstmt.executeUpdate();
				pstmt.close();
								
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.closePreparedStatement(pstmt);
				DBConnection.closeConn(conn);
			}

		}
		

	public void modUser(UserInfoVo userInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update user_info set Agent=?,username=?,password=?,serialnum = ?,access=?,address=?,phone=?,start_date=?,end_date=? where id=? ");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
//		System.out.println(userInfo.getUserName());
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userInfo.getAgent());
			pstmt.setString(2, userInfo.getUserName());
			pstmt.setString(3, userInfo.getPass());
			pstmt.setString(4, userInfo.getSerialnum());
			pstmt.setString(5, userInfo.getAccess());
			pstmt.setString(6, userInfo.getAddress());
			pstmt.setString(7, userInfo.getPhone());
			pstmt.setDate(8, userInfo.getStart_date());
			pstmt.setDate(9, userInfo.getEnd_date());
			pstmt.setInt(10,userInfo.getId());
			pstmt.executeUpdate();
			pstmt.close();
//			System.out.println(conn.getAutoCommit());
//			conn.setAutoCommit(false);
//			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
	}
	
	public void delUser(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from user_info where id=? ");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
//			System.out.println(conn.getAutoCommit());
//			conn.setAutoCommit(false);
//			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
	}
	
	public String check (String serialnum) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id from user_info where serialnum=? ");
		Connection conn = DBConnection.getConnection();
		java.sql.ResultSet rs = null;
		PreparedStatement pstmt = null;
		String checknum = null ;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, serialnum);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				String serialNum = rs.getString("serialnum");
				checknum = serialNum ;
			}
			else{
				checknum = "notexist";}
			
			
//			System.out.println(conn.getAutoCommit());
//			conn.setAutoCommit(false);
//			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return checknum;
	}
	

	public UserInfoVo findUserById(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,agent,username,password,serialnum,access,address,phone,start_date,end_date,TO_DAYS(end_date)-TO_DAYS(now()) as res_date from user_info where id=?  order by id asc");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		UserInfoVo userInfoVo = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userInfoVo = new UserInfoVo();
				int ID = rs.getInt("id");
				String Agent = rs.getString("agent");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String serialNum = rs.getString("serialnum");
				String Access = rs.getString("access");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				
				String startDate = rs.getString("start_date");
				String endDate = rs.getString("end_date");
				int res_date = rs.getInt("res_date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				long enddate = 1l;
				long startdate = 1l;
				
				try {
					startdate = sdf.parse(startDate).getTime();
					enddate = sdf.parse(endDate).getTime();
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				userInfoVo.setId(ID);
				userInfoVo.setAgent(Agent);
				userInfoVo.setUserName(userName);
				userInfoVo.setPass(password);
				userInfoVo.setSerialnum(serialNum);
				userInfoVo.setAccess(Access);
				userInfoVo.setAddress(address);
				userInfoVo.setPhone(phone);
				userInfoVo.setStart_date(new java.sql.Date(startdate));
				userInfoVo.setEnd_date(new java.sql.Date(enddate));
				userInfoVo.setRes_date(res_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return userInfoVo;
	}
	
	public List<UserInfoVo> findUserList(String start_Date,String end_Date,String username,String serialNum,String Agent) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,agent,username,password,serialnum,access,address,phone,new_date,start_date,end_date,TO_DAYS(end_date)-TO_DAYS(now()) as res_date from user_info where 1=1 ");
		if(start_Date!=null&& start_Date!="")sql.append("and start_date > '" + start_Date +"'");
		if(end_Date!=null && end_Date!="")sql.append(" and end_date < '" + end_Date + "'");
		if(Agent != null&& Agent != "")sql.append(" and agent like '%" + Agent + "%'");
		if(username != null&& username != "")sql.append(" and username like '%" + username + "%'");
		if(serialNum !=null && serialNum != "")sql.append(" and serialnum like '%" + serialNum + "%'");
		sql.append(" order by id asc");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		List<UserInfoVo> userList = new ArrayList<UserInfoVo>();
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserInfoVo userInfoVo = new UserInfoVo();
				int id = rs.getInt("id");
				String agent = rs.getString("agent");
				String userName = rs.getString("username");
				String passsword = rs.getString("password");
				String serial_num = rs.getString("serialnum");
				String Access = rs.getString("access");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String newDate = rs.getString("new_date");
				String startDate = rs.getString("start_date");
				String endDate = rs.getString("end_date");
				int res_date = rs.getInt("res_date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				long newdate = 1l;
				long enddate = 1l;
				long startdate = 1l;
				try {
					startdate = sdf.parse(startDate).getTime();
					enddate = sdf.parse(endDate).getTime();
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(newDate!=null){ //判断更新时间如果为空，则不处理，返回
					try{
					newdate = sdf.parse(newDate).getTime();}
					catch(ParseException e) {
						e.printStackTrace();
					}
					userInfoVo.setNew_date(new java.sql.Date(newdate));	
				}
				userInfoVo.setId(id);
				userInfoVo.setAgent(agent);
				userInfoVo.setUserName(userName);
				userInfoVo.setPass(passsword);
				userInfoVo.setSerialnum(serial_num);
				userInfoVo.setAccess(Access);
				userInfoVo.setAddress(address);
				userInfoVo.setPhone(phone);
     			userInfoVo.setStart_date(new java.sql.Date(startdate));
				userInfoVo.setEnd_date(new java.sql.Date(enddate));
				userInfoVo.setRes_date(res_date);
				userList.add(userInfoVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return userList;
	}
	
	public static void main(String[] args) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
//		UserInfoVo userInfo = new UserInfoVo();
//
//		Date date = new java.util.Date();
//		userInfo.setStart_date(new java.sql.Date(date.getTime()));
//		userInfo.setEnd_date(new java.sql.Date(date.getTime()));
//		userInfo.setUserName("fuck2");
//		userInfo.setAddress("China beijing");
//		userInfo.setPhone("13611092400");
//		userInfo.setId(2);
//		userInfo.setUserName("admin");
//		userInfo.setAddress("huoxing");
//		userInfo.setPhone("13407171268");
//		uidi.addUser(userInfo);
//		uidi.modUser(userInfo);
//		uidi.delUser(14);
//		UserInfoVo userInfoVo = uidi.findUserById(8);
		System.out.println(uidi.findUserList("", "", "","","34").size());
//		System.out.println(userInfoVo.getUserName()+"---"+userInfoVo.getAddress()+"---"+userInfoVo.getStart_date()+"---"+userInfoVo.getEnd_date());
	}

	public UserInfoVo login(String serialnum) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,agent,access,username,password,serialnum,address,phone,start_date,end_date,TO_DAYS(end_date)-TO_DAYS(now()) as res_date from user_info where serialnum = ? order by id asc");
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		UserInfoVo userInfoVo = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, username);
			pstmt.setString(1, serialnum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userInfoVo = new UserInfoVo();
				int id = rs.getInt("id");
				String Agent = rs.getString("agent");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String serialNum = rs.getString("serialnum");
				String Access = rs.getString("access");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String startDate = rs.getString("start_date");
				String endDate = rs.getString("end_date");
				int resDate = rs.getInt("res_date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				long enddate = 1l;
				long startdate = 1l;
				try {
					startdate = sdf.parse(startDate).getTime();
					enddate = sdf.parse(endDate).getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				userInfoVo.setId(id);
				userInfoVo.setAgent(Agent);
				userInfoVo.setUserName(userName);
				userInfoVo.setPass(password);
				userInfoVo.setSerialnum(serialNum);
				userInfoVo.setAccess(Access);
				userInfoVo.setAddress(address);
				userInfoVo.setPhone(phone);
				userInfoVo.setStart_date(new java.sql.Date(startdate));
				userInfoVo.setEnd_date(new java.sql.Date(enddate));
				userInfoVo.setRes_date(resDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return userInfoVo;
	}

}
