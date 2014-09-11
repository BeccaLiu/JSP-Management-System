         package com.usermanage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.usermanage.service.UserManageService;


public class UserManageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = null;
		StringBuffer buffer = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"utf-8\" ?>");	
		try {
						
			String method = request.getParameter("method");
//			logger.info("serivceID:"+serviceId);
			// 获取JoyID
			if ("login".equalsIgnoreCase(method)) {// 用户登陆
//				out = response.getWriter();
				UserManageService.getInstance().login(request, response,buffer);
			}
			else if ("getsd".equalsIgnoreCase(method)){
				UserManageService.getInstance().login(request, response,buffer);
			}
			else if ("gethd".equalsIgnoreCase(method)){
				UserManageService.getInstance().login(request, response,buffer);
			}
			else if ("schuserlist".equalsIgnoreCase(method)) {// 查找用户列表
				UserManageService.getInstance().schUserList(request, response);
			}else if ("delete".equalsIgnoreCase(method)) {// 删除用户
				UserManageService.getInstance().delUser(request,response);
			}else if ("moduser".equalsIgnoreCase(method)) {				
				UserManageService.getInstance().modUser(request, response);// 修改用户信息
			}else if ("findbyid".equalsIgnoreCase(method)) {				
				UserManageService.getInstance().schUserById(request, response);// 按ID查找用户
			}else if ("adduser".equalsIgnoreCase(method)) {				
				UserManageService.getInstance().addUser(request, response);// 添加用户
			}else if ("checkserial".equalsIgnoreCase(method)) {				
				UserManageService.getInstance().check(request, response);// 添加用户
			}else if ("download".equalsIgnoreCase(method)) {				
				String flag = request.getParameter("flag");
				response.setContentType("application/x-download");
				String url = request.getSession().getServletContext().getRealPath("/")+"downloadfile/";
				if("true".equals(flag)){		
					String fileName = "valid.xml";
					url+=fileName;
					System.out.println(url);
					response.addHeader("Content-Disposition", "attachment;filename="+ fileName);
				}else{
					String fileName = "notvalid.txt";
					url+=fileName;
					System.out.println(url);
					response.addHeader("Content-Disposition", "attachment;filename="+ fileName);
				}
				out(url,response);
			}else if ("schsurplus".equalsIgnoreCase(method)) {		
				out = response.getWriter();
				UserManageService.getInstance().schSurplus(request, response,out ,buffer);// 查询剩余
			}else{
				out = response.getWriter();
				buffer.append("<result code=\"418\" description=\"找不到方法\" />");
				out.print(buffer.toString());
				out.flush();	
				out.close();
			}
		} finally {
			
		}
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	private void out(String url, HttpServletResponse response) {
        int i = 0;
        byte[] b = new byte[1024];
        File file = new File(url);
//        FileInputStream fis = null;
        OutputStream out1 = null;
        try {
        	if(file.exists()){
        		FileInputStream fis = new FileInputStream(file);
	            out1 = response.getOutputStream();
	            while ((i = fis.read(b)) > 0) {
	                     out1.write(b, 0, i);
	            }
	            fis.close();
        	}else{
        		 out1 = response.getOutputStream();
        		 out1.write("1".getBytes(), 0, 1);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        	System.out.println("-+--Exception  breaken pipe----");
        } finally {
              try {
                    if(out1!=null){
                    	out1.flush();
                    	out1.close();
                    }                   
              } catch (IOException e) {
                  e.printStackTrace();
            	  System.out.println("--+--IOException  breaken pipe----");
              }
        }
   }
}
