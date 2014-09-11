<%@page import="com.usermanage.vo.UserInfoVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="app" var="myKey" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<HEAD>
		<LINK href="<%=request.getContextPath()%>/css/admin.css"
			type="text/css" rel="stylesheet">
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
			src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js""></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.datepick.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/jquery.datepick.css" />
		<script language="javascript" type="text/javascript">
		
	<!--去左空格--> 
	function ltrim(s)
	{ 
		return s.replace( /^\s*/, ""); 
	} 
	<!--去右空格-->  
	function rtrim(s)
	{ 
		return s.replace( /\s*$/, ""); 
	} 
	<!--去左右空格-->
	function trim(s)
	{ 
		return rtrim(ltrim(s)); 
	}
	function doChangePass(){
			 var prepassword = trim(document.formpass.prepassword.value);
			 var newpassword = trim(document.formpass.newpassword.value);
			 var repassword = trim(document.formpass.repassword.value);
			 var pass = <%= (String)request.getSession().getAttribute("pass") %>;
		     			  
			 if(prepassword.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_originalpass"/>");
			 	return document.formpass.prepassword.focus();
			 }
			 if(pass!=prepassword)
			 {  alert("<fmt:message  bundle="${myKey}" key="msg_wrongpass"/>");
			 	return document.formpass.prepassword.focus();
			 }
			 if(newpassword.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_newpass"/>");
			 	return document.formpass.newpassword.focus();
			 }
			  if(document.formpass.newpassword.value!=document.formpass.repassword.value){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_notpass"/>");
			 	return document.formpass.repassword.focus();
			 }
			 if(confirm("<fmt:message  bundle="${myKey}" key="msg_confirmpass"/>?")){
			 document.formpass.action = "<%=request.getContextPath()%>/manage?method=ChangePass";
			 document.formpass.submit();
			 }
			 alert("<fmt:message  bundle="${myKey}" key="msg_passsucess"/>");
			 top.location.href = "<%=request.getContextPath() %>/index.jsp"; 
	}

	</script>
	</HEAD>
	<BODY>
		<form action="" method="post" name="formpass">
			<table width="545" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td width="100">
						<fmt:message  bundle="${myKey}" key="oldpass"/>：
					</td>
					<td width="150" colspan="2">
						<input type="password" name="prepassword" value="" style="width: 110px">
					</td>
				</tr>
				
				<tr>
					<td width="100">
						<fmt:message  bundle="${myKey}" key="newpass"/>：
					</td>
					<td width="150" colspan="2">
						<input type="password" name="newpassword" value="" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="100">
						<fmt:message  bundle="${myKey}" key="renewpass"/>：
					</td>
					<td width="150" colspan="2">
						<input type="password" name="repassword" value="" style="width: 110px">
					</td>
				</tr>
				
				<tr>

					<td align="center">
						<input type="button" value="<fmt:message  bundle="${myKey}" key="btn_yes"/>" onclick="doChangePass();" >
					</td>
					<td align="left">
						<input type="button" value="<fmt:message  bundle="${myKey}" key="btn_cancel"/>"
							onclick="javascript:history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</BODY>

</HTML>