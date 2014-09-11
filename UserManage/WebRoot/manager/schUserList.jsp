<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="app" var="myKey" scope="session"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML><HEAD>
<LINK href="<%=request.getContextPath()%>/css/admin.css" type="text/css" rel="stylesheet">
<style type="text/css">
<!--
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {	color: #344b50;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE22 {color: #FFFFFF}
-->
</style>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js""></script>
<script language="javascript"  type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.datepick.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datepick.css" />
<script  language="javascript" type="text/javascript">
$(function() {
	 $('#begin').datepick({dateFormat: 'yyyy-mm-dd'});
	 $('#end').datepick({dateFormat: 'yyyy-mm-dd'});
});
function doDelete(id){
	      if(confirm("<fmt:message bundle="${myKey}" key="msg_delete"/>?")){
			document.location.href="<%=request.getContextPath()%>/user?method=delete&id=" +id;
		  }		
   }
function doAdd(){

			document.location.href="<%=request.getContextPath()%>/manager/addUser.jsp";
		
}
</script>
</HEAD>
<BODY>
<%@ page import="java.util.*"%>
<%@ page import="com.usermanage.vo.UserInfoVo"%>
<form action="<%=request.getContextPath()%>/user" method="get" name="form1">
<input name="method" id="method" value="schuserlist" type="hidden"/>
<table width="545" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="82"><fmt:message bundle="${myKey}" key="searchtime"/>：</td>
    <td width="110"><fmt:message bundle="${myKey}" key="from"/>    
      <input name="start_date" type="text" id="begin"  style="width:110px">
    </td>
    <td width="21"><fmt:message bundle="${myKey}" key="to"/></td>
    <td width="172">
    	<input name="end_date" type="text" id="end"  style="width:110px">
    </td>
  </tr>
</table>
<table width="255" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="91"><fmt:message bundle="${myKey}" key="searchserialnum"/>：</td>
    <td width="105" colspan="3"><input name="serialNum" type="text" id="mobile" value="" style="width:110px">
    </td>
  </tr>
</table>
<table width="355" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="91"><fmt:message bundle="${myKey}" key="searchagent"/>：</td>
    <td width="105" colspan="3"><input name="Agent" type="text" id="mobile" value="" style="width:110px">
    </td>
  </tr>
</table>
<table width="355" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="91"><fmt:message bundle="${myKey}" key="searchuser"/>：</td>
    <td width="105" colspan="3"><input name="userName" type="text" id="mobile" value="" style="width:110px">
    </td>
    <td width="49"><input type="submit" name="button5" id="button5" value="<fmt:message bundle="${myKey}" key="btn_search"/>"></td>
    <td width="49"><input type="button" name="button6" id="button6" value="<fmt:message bundle="${myKey}" key="btn_adduser"/>" onclick="doAdd()"></td>
  </tr>
</table>
</form>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/image/title_bg2.jpg">
  <tr>
    <td><span class="STYLE22"><fmt:message bundle="${myKey}" key="allmaterial"/></span></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#a8c7ce">
  <tr>
    <td align="center" bgcolor="d3eaef" class="STYLE10" width=30><fmt:message bundle="${myKey}" key="number"/></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width="100"><span class="STYLE10"><fmt:message bundle="${myKey}" key="agent"/></span></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width="100"><span class="STYLE10"><fmt:message bundle="${myKey}" key="userid"/></span></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width="100"><span class="STYLE10"><fmt:message bundle="${myKey}" key="password"/></span></td>
    <td align="center" bgcolor="d3eaef" class="STYLE6" width=150><fmt:message bundle="${myKey}" key="serialnum"/></td>
    <td align="center" bgcolor="d3eaef" class="STYLE6" width=50><fmt:message bundle="${myKey}" key="auth"/></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width=150><fmt:message bundle="${myKey}" key="activetime"/></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width=150><fmt:message bundle="${myKey}" key="address"/></td>
    <td bgcolor="d3eaef" class="STYLE6" align="center"><span class="STYLE10" width=150><fmt:message bundle="${myKey}" key="tel"/></span></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width=150><fmt:message bundle="${myKey}" key="start_date"/></td>
    <td bgcolor="d3eaef" class="STYLE6" align="center"><span class="STYLE10" width=150><fmt:message bundle="${myKey}" key="end_date"/></span></td>
    <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width=50><fmt:message bundle="${myKey}" key="restday"/></td>
     <td height="20" align="center" bgcolor="d3eaef" class="STYLE6" width=150><fmt:message bundle="${myKey}" key="action"/></td>
  </tr>
  <%
		List<UserInfoVo> list = (List)request.getAttribute("userList");
  		if(null != list && list.size()>0){
			for(int i = 0;i < list.size(); i++){
				UserInfoVo userInfo = list.get(i);
	%>
  <tr>
    <td align="center" bgcolor="#FFFFFF"><%=userInfo.getId()%></td>
    <td align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getAgent()%></td>
    <td align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getUserName()%></td>
    <td align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getPass()%></td>
    <td height="20" align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getSerialnum()%></td>
    <td align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getAccess()%></td>
    <td height="20" width="100" align="center" bgcolor="#FFFFFF" class="STYLE19"><%if(userInfo.getNew_date()!=null)%><%=userInfo.getNew_date()%><%else%><fmt:message bundle="${myKey}" key="neverlogin"/></td>
    <td height="20" width="150" align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getAddress()%></td>
    <td bgcolor="#FFFFFF" class="STYLE19" align="center"><%=userInfo.getPhone()%></td>
    <td height="20" width="100" align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getStart_date()%></td>
    <td bgcolor="#FFFFFF" class="STYLE19" align="center"><%=userInfo.getEnd_date()%></td>
    <td height="20" align="center" bgcolor="#FFFFFF" class="STYLE19"><%=userInfo.getRes_date()%></td> 
    <td height="20" align="center" bgcolor="#FFFFFF" class="STYLE19"><a href="<%=request.getContextPath()%>/user?method=findbyid&id=<%=userInfo.getId()%>"><fmt:message bundle="${myKey}" key="edit"/></a>|<a href="#" onclick="doDelete('<%=userInfo.getId()%>')"><fmt:message bundle="${myKey}" key="delete"/></a></td> 
  </tr>
  <%
			}
  		}
  %>
</table>
</BODY>
</HTML>
