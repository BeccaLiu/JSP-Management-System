package com.usermanage.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import com.usermanage.vo.ManagerVo;
//import com.usermanage.service.ManagerService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.usermanage.dao.impl.UserInfoDaoImpl;
import cn.usermanage.util.Encrypt;

import com.usermanage.vo.UserInfoVo;

public class UserManageService {
	private static final UserManageService instance = new UserManageService();

	private UserManageService() {
	}

	public static UserManageService getInstance() {
		return instance;
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response,StringBuffer buffer){
		String sid = request.getParameter("sid");
		String serialnum = request.getParameter("hid");
		String auth = request.getParameter("auth");
		UserInfoVo userInfoVo = UserInfoDaoImpl.getInstance().login(serialnum);
		String method = request.getParameter("method");
		if(userInfoVo !=null){
			System.out.println("--------"+new Date()+"用户："+userInfoVo.getUserName()+"访问--------");
			UserInfoDaoImpl.getInstance().updateDate(userInfoVo);
		}
		String temp = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] TmpKey2 = null;
		try{
			if(userInfoVo != null ){
				String authEncrypt = Encrypt.byte2hex(Encrypt.package_encrypt(sid,16,userInfoVo.getPass().getBytes()));
				if(authEncrypt.equals(auth.toUpperCase())&&userInfoVo.getRes_date()>0){
//					String TmpKey1 = serialnum;
					TmpKey2 = Encrypt.package_encrypt(serialnum, 16, userInfoVo.getPass().getBytes());
					String defaultFilePath = null ;
					if ("login".equalsIgnoreCase(method)) {// 用户登陆
						defaultFilePath = "c:\\filevalid\\valid.xml";
					}
					else if ("getsd".equalsIgnoreCase(method)){
						defaultFilePath = "c:\\filevalid\\valid.xml";
					}
					else if ("gethd".equalsIgnoreCase(method)){
						defaultFilePath = "c:\\hdfilevalid\\valid.xml";
					}
					File file = new File(defaultFilePath);
					if (file.exists()) {
						FileInputStream fi;
						byte[] buffer1= new byte[1024];
						int len = 0;
						try {
							fi = new FileInputStream(file);
							while ((len = fi.read(buffer1)) != -1) {
								bos.write(buffer1, 0, len);
							}
							temp = new String(bos.toByteArray(), "utf-8");
//							System.out.println(temp);
							bos.flush();
							fi.close();
							bos.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else{
					TmpKey2 = Encrypt.package_encrypt(serialnum, 16, "18b5a17E7ecCEfd3".getBytes());
					File file = new File("c:\\filenotvalid\\notvalid.xml");
					if (file.exists()) {
						FileInputStream fi;
						byte[] buffer1= new byte[1024];
						int len = 0;
						try {
							fi = new FileInputStream(file);
							while ((len = fi.read(buffer1)) != -1) {
								bos.write(buffer1, 0, len);
							}
							temp = new String(bos.toByteArray(), "utf-8");
//							System.out.println(temp);
							bos.flush();
							fi.close();
							bos.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				TmpKey2 = Encrypt.package_encrypt(serialnum, 16, "18b5a17E7ecCEfd3".getBytes());
				File file = new File("c:\\filenotvalid\\notvalid.xml");
				if (file.exists()) {
					FileInputStream fi;
					byte[] buffer1= new byte[1024];
					int len = 0;
					try {
						fi = new FileInputStream(file);
						while ((len = fi.read(buffer1)) != -1) {
							bos.write(buffer1, 0, len);
						}
						temp = new String(bos.toByteArray(), "utf-8");
//						System.out.println(temp);
						bos.flush();
						fi.close();
						bos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
//			String SessionKey = sid;
			byte[] SessionKey2 = Encrypt.package_encrypt(sid, 16, TmpKey2);
			byte[] output = Encrypt.package_encrypt(temp, temp.getBytes("utf-8").length, SessionKey2);;
			response.getOutputStream().write(output);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addUser(HttpServletRequest req, HttpServletResponse res) {
//处理管理者代理商模块
		String Agent = null;
		if(req.getSession().getAttribute("name")!=null)
		{
			String agentname=(String)req.getSession().getAttribute("name");
			String agentrole=(String)req.getSession().getAttribute("role");
			if ("administrator".equalsIgnoreCase(agentrole))
			{	Agent = req.getParameter("agent");			}
			else 
			{Agent = agentname;			}
		}
//处理管理者代理商模块
				
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String serialNum = req.getParameter("serialnum");
		String Access = req.getParameter("access");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String startDate = req.getParameter("start_date");
		String endDate = req.getParameter("end_date");
		
		UserInfoVo userInfoVo = new UserInfoVo();
		userInfoVo.setAgent(Agent);
		userInfoVo.setUserName(userName);
		userInfoVo.setPass(password);
		userInfoVo.setSerialnum(serialNum);
		userInfoVo.setAccess(Access);
		userInfoVo.setAddress(address);
		userInfoVo.setPhone(phone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		long startdate = 1l;
		long enddate = 1l;
		try {
			startdate = sdf.parse(startDate).getTime();
			enddate = sdf.parse(endDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userInfoVo.setStart_date(new java.sql.Date(startdate));
		userInfoVo.setEnd_date(new java.sql.Date(enddate));
		
//		String check = UserInfoDaoImpl.getInstance().check(serialNum);
//		if(check == "valide"){
		UserInfoDaoImpl.getInstance().addUser(userInfoVo);
//			
		try {
			req.getRequestDispatcher("/user?method=schuserlist").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void schUserList(HttpServletRequest req, HttpServletResponse res){//搜索栏
		
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		
		String agent = null;
		if(req.getSession().getAttribute("name")!=null)
		{
			String agentname=(String)req.getSession().getAttribute("name");
			String agentrole=(String)req.getSession().getAttribute("role");
			if ("administrator".equalsIgnoreCase(agentrole))
			{	agent = req.getParameter("Agent");			}
			else 
			{agent = agentname;			}
		}
		
		String username = req.getParameter("userName");
		String serialNum = req.getParameter("serialNum");
		List<UserInfoVo> userList = UserInfoDaoImpl.getInstance().findUserList(start_date,end_date,username,serialNum,agent);
		try {
			req.setAttribute("userList",userList);
			req.getRequestDispatcher("/manager/schUserList.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void delUser(HttpServletRequest req,HttpServletResponse resp){
		int id = Integer.parseInt(req.getParameter("id"));
		UserInfoDaoImpl.getInstance().delUser(id);
		try {
			req.getRequestDispatcher("/user?method=schuserlist").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void check(HttpServletRequest req,HttpServletResponse resp){
		
		String strName = req.getParameter("NAME");//得到用户名
		try{
		req.setCharacterEncoding("UTF-8");
		//设置输出信息的格式及字符集
		resp.setContentType("text/xml;charset=UTF-8"); resp.setHeader("Cache-Control","no-cache");
		//创建输出流对象
		PrintWriter outer = resp.getWriter();
	
		//依据结果输出不同的数据信息
		
		outer.println("<response>");
		if(strName.equals("123")){//将获取的值与数据库中的值相对比
		   outer.println("<res>"+"用户名已存在"+"</res>");
		}else{
		outer.println("<res>"+"此用户名可以使用"+"</res>");
		}
		outer.println("</response>");
		outer.close();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void schUserById(HttpServletRequest request,HttpServletResponse response){//编辑
		int id = Integer.parseInt(request.getParameter("id"));
		UserInfoVo userInfo = UserInfoDaoImpl.getInstance().findUserById(id);
		
		try {
			request.setAttribute("userInfoVo",userInfo);
			request.getRequestDispatcher("/manager/userDetail.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void modUser(HttpServletRequest request,HttpServletResponse response){
		UserInfoVo userInfo = new UserInfoVo();
		int id = Integer.parseInt(request.getParameter("id"));
        
		String Agent = null;
		if(request.getSession().getAttribute("name")!=null)
		{
			String agentname=(String)request.getSession().getAttribute("name");
			String agentrole=(String)request.getSession().getAttribute("role");
			if ("administrator".equalsIgnoreCase(agentrole))
			{	Agent = request.getParameter("agent");			}
			else 
			{  Agent = agentname;			}
		}
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String serialNum = request.getParameter("serialnum");
		String Access = request.getParameter("access");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long enddate = 1l;
		long startdate = 1l;
		try {
			startdate = sdf.parse(startDate).getTime();
			enddate = sdf.parse(endDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userInfo.setId(id);
		userInfo.setAgent(Agent);
		userInfo.setUserName(userName);
		userInfo.setPass(password);
		userInfo.setSerialnum(serialNum);
		userInfo.setAccess(Access);
		userInfo.setAddress(address);
		userInfo.setPhone(phone);
		userInfo.setStart_date(new java.sql.Date(startdate));
		userInfo.setEnd_date(new java.sql.Date(enddate));
		UserInfoDaoImpl.getInstance().modUser(userInfo);
		try {
			request.getRequestDispatcher("/user?method=schuserlist").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void schSurplus(HttpServletRequest request,HttpServletResponse response,PrintWriter out ,StringBuffer buffer){
		String serialnum = request.getParameter("hid");
		
		UserInfoVo userInfoVo = UserInfoDaoImpl.getInstance().login(serialnum);
		try {
			buffer.append("<result username=\""+userInfoVo.getUserName()+"\" surplus_time=\""+userInfoVo.getRes_date()+"天\"/>");
			out.write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
