<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="app" var="myKey" scope="session"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><fmt:message  bundle="${myKey}" key="title"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
   <script type="text/javascript">
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
     function doLogin(code){
         if(code == 13){
	         var  mng_id = trim(document.form1.mng_id.value);
	         var  mng_pwd = trim(document.form1.mng_pwd.value);
	         if(mng_id.length ==0){
	              alert("<fmt:message  bundle="${myKey}" key="msg_managerid"/>");
	              return document.form1.mng_id.focus();
	         }
	         if(mng_pwd.length ==0){
	              alert("<fmt:message  bundle="${myKey}" key="msg_password"/>");
	              return document.form1.mng_pwd.focus();
	         }
	        else {
				document.form1.action = "manage?method=login";
				document.form1.submit();
			}
		}	
   }
   function doReSet(){
     	document.form1.reset();
   }
   function changeLanguage(language){
   				
                window.location.href="languageGet.jsp?language="+language;
                window.location.reload();
            }            

   </script>
  </head>
  
  <body >
  
   <select name="language" onchange="changeLanguage(this.value)">
   				<option>选择语言</option>
                <option value="zh_CN">简体中文</option>
                <option value="en_US">English</option>
   </select>
  
  <form action="" method="post" name="form1" onkeyUp="doLogin(event.keyCode)" >
  		<table border="0"><tr><td height="150"></td></tr></table>
		<table width="468" align="center" border="0"  cellspacing="0"  cellpadding="0">
			<tr>
				<td width="468" colspan="3" height="23"><img src="<%=request.getContextPath()%>/image/login0.jpg"></td>
			</tr>
			<tr><td width="468" colspan="3" height="147"><img src="<%=request.getContextPath()%>/image/login1.jpg"></td>
			</tr>
			<tr>
				<td height="122" width="16" style="background-image:url(<%=request.getContextPath()%>/image/login3.jpg)">
				</td>
				<td width="436">
					<table border="0" width="350" align="center" class="login" cellpadding="10" cellspacing="0">						
						<tr>
							<td width="10" align="right"><img src="<%=request.getContextPath()%>/image/40.gif"/></td>
							<td width="120" align="right"><fmt:message  bundle="${myKey}" key="managerid"/></td>
							<td>
								<input name="mng_id" class="tdt" type="text" size="20" value="" maxlength="10">
							</td>
						</tr>
						<tr>
							<td width="10" align="right"><img src="<%=request.getContextPath()%>/image/40.gif"/></td>
							<td width="120" align="right"><fmt:message  bundle="${myKey}" key="password"/></td>
							<td width="200">
								<input name="mng_pwd" class="tdt" type="password" size="20" value="" maxlength="15">
							</td>
						</tr>
						<tr>
							<td ></td><td ></td>
							<td><img style="cursor: hand;" onclick="doLogin('13');" src="<%=request.getContextPath()%>/image/btn_login.gif"/>
							&nbsp;<img style="cursor: hand;" onclick="doReSet();" src="<%=request.getContextPath()%>/image/btn_reset.gif"/>
							</td>
						</tr>
					</table>
				</td>
				<td width="16" height="122" style="background-image:url(<%=request.getContextPath()%>/image/login4.jpg)">
				</td>
			</tr>
			<tr><td colspan="3" width="468"><img src="<%=request.getContextPath()%>/image/login5.jpg"></td></tr>	
		</table>
		</form> 
	</body>
	<script type="text/javascript">
	 <% String error=(String)request.getSession().getAttribute("LoginError");
   	if(error == "error"){
   	%>
   		alert("<fmt:message  bundle="${myKey}" key="msg_alert"/>");
   		
   		
   		 	<%
   	} %>
   	</script>
</html>
