<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.usermanage.vo.UserInfoVo;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<LINK href="<%=request.getContextPath()%>/css/admin.css" type="text/css" rel="stylesheet">
	
  </head>
  <%
  	UserInfoVo userInfo = (UserInfoVo)request.getAttribute("userInfo");
  %>
  <body>
    <table width="80%" border="0" cellpadding="3" cellspacing="1" bgcolor="#a8c7ce" align="center">
   	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10" colspan="2">用户：<%=userInfo.getUserName()%>的相关信息</td>
  	</tr>
 	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">用户名</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%=userInfo.getUserName()%></td>
  	</tr>
 	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">序列号</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%=userInfo.getSerialnum()%></td>
  	</tr>
  	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">地址</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%=userInfo.getAddress()%></td>
  	</tr>
  	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">电话</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%=userInfo.getPhone()%></td>
  	</tr>
  	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">开通时间</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%=userInfo.getStart_date()%></td>
  	</tr>
	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">结束时间</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%=userInfo.getEnd_date()%></td>
  	</tr>
  	<tr>
    	<td align="center" bgcolor="d3eaef" class="STYLE10">剩余时间(天)</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%if(userInfo.getRes_date()>0){%><%=userInfo.getRes_date()%><%}else{%>无<%}%></td>
  	</tr>
  	<tr>
  		<td align="center" bgcolor="d3eaef" class="STYLE10">文件下载权限</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><%if(userInfo.getRes_date()>=1){%>是<%}else{%>否<%}%></td>
  	</tr>
  	<%if(userInfo.getRes_date()>=1){%>
  	<tr>
  		<td align="center" bgcolor="d3eaef" class="STYLE10" colspan="2">文件下载列表</td>
  	</tr>
  	<tr>
  		<td align="center" bgcolor="d3eaef" class="STYLE10">文件下载地址</td>
    	<td align="center" bgcolor="d3eaef" class="STYLE6"><a href="#">http://www.download.cn/1.jpg<a></td>
  	</tr>
  	<%}%>
</table>
  </body>
</html>
