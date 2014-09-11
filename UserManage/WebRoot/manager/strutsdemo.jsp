<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Hashtable"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript" type="text/javascript">
String serialnum = request.getParameter("serialnum");//得到用户名
request.setCharacterEncoding("UTF-8");
//设置输出信息的格式及字符集
response.setContentType("text/xml;charset=UTF-8"); response.setHeader("Cache-Control","no-cache");
//创建输出流对象
PrintWriter outer = response.getWriter();
//依据结果输出不同的数据信息
outer.println("<response>");
if(serialnum.equals("123")){//将获取的值与数据库中的值相对比
   outer.println("<res>"+"用户名已存在"+"</res>");
}else{
outer.println("<res>"+"此用户名可以使用"+"</res>");
}
outer.println("</response>");
outer.close();
</script>