<%@page import="com.usermanage.vo.UserInfoVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="app" var="myKey" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<HEAD>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<style type="text/css">
<!--
.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE22 {
	color: #FFFFFF
}
-->
</style>
		<script language="javascript" type="text/javascript"
			src="js/jquery-1.4.2.min.js""></script>
		<script language="javascript" type="text/javascript"
			src="js/jquery.datepick.js"></script>
		<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css" />
	<script language="javascript" type="text/javascript">
		$(function() {
	 		$('#begin').datepick({dateFormat: 'yyyy-mm-dd'});
	 		$('#end').datepick({dateFormat: 'yyyy-mm-dd'});
		});
	</script>
	</HEAD>
	<BODY>
		<%@ page import="java.util.*"%>
		<form action="user?method=moduser" method="post"> 
			<% UserInfoVo userInfo = (UserInfoVo)request.getAttribute("userInfoVo"); %>
			<table width="545" border="0" cellpadding="0" cellspacing="5">
				<input type="hidden" name="id" value="<%=userInfo.getId()%>" style="width: 110px">
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="agent"/>：
					</td>
					<td width="150" colspan="2">
						<input type="text" name="agent" value="<%=userInfo.getAgent()%>" style="width: 110px">
					</td>
				</tr>
				
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="msg_userid"/>：
					</td>
					<td width="150" colspan="2">
						<input type="text" name="username" value="<%=userInfo.getUserName()%>" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="msg_password"/>：
					</td>
					<td width="150" colspan="2">
						<input type="text" name="password" value="<%=userInfo.getPass()%>" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="21">
						<fmt:message  bundle="${myKey}" key="serialnum"/>：
					</td>	
					<td width="172" colspan="2">
						<input type= "text" name="serialnum" value="<%=userInfo.getSerialnum()%>" rows="10" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="auth"/>：
					</td>
					<td width="150" colspan="2">
						<select name="access"  style="width: 110px">
						<option value="HD" <%="HD".equals(userInfo.getAccess())?"selected":"" %>><fmt:message  bundle="${myKey}" key="HD"/></option>
						<option value="SD" <%="SD".equals(userInfo.getAccess())?"selected":"" %>><fmt:message  bundle="${myKey}" key="SD"/></option>
						<option value="default"<%="default".equals(userInfo.getAccess())?"selected":"" %>><fmt:message  bundle="${myKey}" key="default"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="address"/>：
					</td>
					<td width="172" colspan="2">
						<input type="text" name="address" value="<%=userInfo.getAddress()%>">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="tel"/>：
					</td>
					<td width="172" colspan="2">
						<input type="text" name="phone" value="<%=userInfo.getPhone()%>">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="start_date"/>：
					</td>
					<td width="172" colspan="2">
						<input name="start_date" type="text" id="begin"  style="width:110px" value="<%=userInfo.getStart_date()%>">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message  bundle="${myKey}" key="end_date"/>：
					</td>
					<td width="172" colspan="2">
						<input name="end_date" type="text" id="end"  style="width:110px" value="<%=userInfo.getEnd_date()%>">
					</td>
				<tr>
					
					<td align="center">
						<input type="submit" value="<fmt:message  bundle="${myKey}" key="btn_yes"/>">
					</td>
					<td align="left">
						<input type="button" value="<fmt:message  bundle="${myKey}" key="btn_cancel"/>" onclick="javascript:history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</BODY>
</HTML>