package com.usermanage.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.usermanage.dao.impl.ManagerDaoImpl;
import cn.usermanage.dao.impl.UserInfoDaoImpl;

import com.usermanage.vo.ManagerVo;
import com.usermanage.vo.UserInfoVo;

public class ManagerService {
	private static final ManagerService instance = new ManagerService();

	private ManagerService() {
	}

	public static ManagerService getInstance() {
		return instance;
	}

	public void login(HttpServletRequest request,
			HttpServletResponse response){
		String managerName = request.getParameter("mng_id");
		String password = request.getParameter("mng_pwd");
		ManagerVo managerVo = ManagerDaoImpl.getInstance().login(managerName,password);
		try{
			if(managerVo != null){
				request.getSession().setAttribute("name", managerVo.getManage_name());
				request.getSession().setAttribute("role", managerVo.getManage_role());
				request.getSession().setAttribute("pass", managerVo.getManage_pass());
				System.out.println("--------"+new Date()+"  "+managerVo.getManage_role()+":"+managerVo.getManage_name()+"访问--------");
				request.getRequestDispatcher("/manager/main.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("LoginError","error" );//密码错误弹框
				request.getRequestDispatcher("").forward(request, response);
				request.getSession().removeAttribute("LoginError");//无这条会报错
			}
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		public void passchange(HttpServletRequest request,
						HttpServletResponse response){
			String managerName = (String)request.getSession().getAttribute("name");
			String newpassword= request.getParameter("newpassword");
			ManagerVo managerVo = ManagerDaoImpl.getInstance().passchange(managerName,newpassword);
			request.getSession().setAttribute("pass", managerVo.getManage_pass());
    }
	
	
	
	public void logOut(HttpServletRequest request,
		HttpServletResponse response){
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("role");
		request.getSession().removeAttribute("pass");
		try {
			request.getRequestDispatcher("").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
