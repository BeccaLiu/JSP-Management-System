<%@page import="com.usermanage.vo.UserInfoVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		$(function() {
	 		$('#begin').datepick({dateFormat: 'yyyy-mm-dd'});
	 		$('#end').datepick({dateFormat: 'yyyy-mm-dd'});
		});
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
	
	
	function doAddUser(){
			 var username = trim(document.form1.username.value);
			 var password = trim(document.form1.password.value);
			 var serialnum = trim(document.form1.serialnum.value);
			 var phone = trim(document.form1.phone.value);
			 var start_date = trim(document.form1.start_date.value);
			 var end_date = trim(document.form1.end_date.value);
			 var agent = trim(document.form1.agent.value);
			 var access = trim(document.form1.access.value);
			  if(agent.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_agent"/>");
			 	return document.form1.agent.focus();
			 }
			 if(username.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_userid"/>");
			 	return document.form1.username.focus();
			 }
			  if(password.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_password"/>");
			 	return document.form1.password.focus();
			 }
			  if(access.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_auth"/>");
			 	return document.form1.access.focus();
			 }
 			 if(serialnum.length == 0){
 			 	alert("<fmt:message  bundle="${myKey}" key="msg_serialnum"/>");
 			 	return document.form1.serialnum.focus();
 			 }
			
			
			 if(start_date.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_start_date"/>");
			 	return document.form1.start_date.focus();
			 }
			 if(end_date.length == 0){
			 	alert("<fmt:message  bundle="${myKey}" key="msg_end_date"/>");
			 	return document.form1.end_date.focus();
			 }
			
			
			 document.form1.action = "<%=request.getContextPath()%>/user?method=adduser";
			 document.form1.submit();
		}

	</script>
<script language="javascript" type="text/javascript">
    var XMLHttpReq = false;
    function creatXMLHttpRequest(){ //创建XMLHttpRequest 对象
    if(window.XMLHttpRequest){ //Mozilla 浏览器
  	XMLHttpReq = new XMLHttpRequest();
	}else if(window.ActiveXObject){ //IE 浏览器
   	try{
    XMLHttpReq = new ActiveXObject("Msxm12.XMLHTTP");
   	}catch(e){
    try{
     XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }catch(e){}
   }
}
}
//发出请求
function sendRequest(url){
creatXMLHttpRequest();
alert("send ok");
XMLHttpReq.open("POST",url,true);// XMLHttpReq.open("GET",url,true);
XMLHttpReq.onreadystatechange = processResponse();//指定响应函数(状态改变的触发器)
//或 XMLHttpReq.onreadystatechange = function(){};
XMLHttpReq.send(null);//发送请求

}

//处理返回信息函数
function processResponse(){
if(XMLHttpReq.readyState == 4){
   if(XMLHttpReq.status == 200){
var res = XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
    alert(res);//提示是否验证成功
    if(res == "用户名已存在"){
     document.form1.serialnum.value="";//设置用户名的值为空
     document.form1.serialnum.focus();//设置光标位置
    }
   }else{
    alert("你所请求的页面异常");
   }
}
}
function CheckSerialNum(){
var serialnum = document.form1.serialnum.value;//获取要验证的名字
if(serialnum.length == 0){
 			 	alert("<fmt:message  bundle="${myKey}" key="msg_serialnum"/>");
 			 	return false;
}else{
   
   sendRequest("strutsdemo.jsp?serialnum="+serialnum);//将信息发送到后台进行验证
   
}
return true;
}
</script>
	
	</HEAD>

	
	<BODY>
		<form action="" method="post" name="form1">
			<table width="545" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="agent"/>：
					</td>
					<td width="150" colspan="2">
						<input type="text" name="agent" value="" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="userid"/>：
					</td>
					<td width="150" colspan="2">
						<input type="text" name="username" value="" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="password"/>：
					</td>
					<td width="150" colspan="2">
						<input type="text" name="password" value="" style="width: 110px">
					</td>
				</tr>
				<tr>
					<td width="21">
						<fmt:message bundle="${myKey}" key="serialnum"/>：
					</td>
					<td width="172" colspan="2">
						<input type="text" name="serialnum" value=""  style="width: 110px" onblur="CheckSerialNum()" >
					</td>
					
				</tr>
					<tr>
					<td width="21">
						<fmt:message bundle="${myKey}" key="auth"/>：：
					</td>
					<td width="172" colspan="2">
						<select name="access" style="width: 110px">
						<option value="HD"><fmt:message bundle="${myKey}" key="HD"/></option>
						<option value="SD"><fmt:message bundle="${myKey}" key="SD"/></option>
						<option value="default"><fmt:message bundle="${myKey}" key="default"/>：</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="address"/>：
					</td>
					<td width="172" colspan="2">
						<input type="text" name="address" value="">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="tel"/>：
					</td>
					<td width="172" colspan="2">
						<input type="text" name="phone" value="">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="start_date"/>：
					</td>
					<td width="172" colspan="2">
						<input name="start_date" type="text" id="begin"
							style="width: 110px" value="">
					</td>
				</tr>
				<tr>
					<td width="88">
						<fmt:message bundle="${myKey}" key="end_date"/>：
					</td>
					<td width="172" colspan="2">
						<input name="end_date" type="text" id="end" style="width: 110px"
							value="">
					</td>
				<tr>

					<td align="center">
						<input type="button" value="<fmt:message bundle="${myKey}" key="btn_add"/>" onclick="doAddUser();">
					</td>
					<td align="left">
						<input type="button" value="<fmt:message bundle="${myKey}" key="btn_cancel"/>"
							onclick="javascript:history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</BODY>
</HTML>