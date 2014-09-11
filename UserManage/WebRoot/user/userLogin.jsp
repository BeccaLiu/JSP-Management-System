<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户后台管理系统-登录</title>
    
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
	         var  username = trim(document.form1.username.value);
	         var  serialnum = trim(document.form1.serialnum.value);
	         if(username.length ==0){
	              alert("请输入用户名");
	              return document.form1.username.focus();
	         }
	         if(serialnum.length ==0){
	              alert("请输入序列号");
	              return document.form1.serialnum.focus();	
	         }
	        else {
				document.form1.action = "user?method=login";
				document.form1.submit();
			}
		}	
   }
   function doReSet(){
     	document.form1.reset();
   }  
   </script>
  </head>
  
  <body >
  <form action="" method="post" name="form1" onkeyUp="doLogin(event.keyCode)" >
  		<table border="0"><tr><td height="150"></td></tr></table>
		<table width="468" align="center" border="0"  cellspacing="0"  cellpadding="0">
			<tr>
				<td width="468" colspan="3" height="23"><img src="<%=request.getContextPath()%>/image/login0.jpg"></td>
			</tr>
			<tr><td width="468" colspan="3" height="147"><img src="<%=request.getContextPath()%>/image/login1.jpg"></td>
			</tr>
			<tr>
				<td width="16" height="122"  style="background-image:url(<%=request.getContextPath()%>/image/login_3.jpg)">
				</td>
				<td width="436">
					<table border="0" width="350" align="center" class="login" cellpadding="10" cellspacing="0">						
						<tr>
							<td width="10" align="right"><img src="<%=request.getContextPath()%>/image/40.gif"/></td>
							<td width="120" align="right">用户名</td>
							<td>
								<input name="username" class="tdt" type="text" size="20" value="" maxlength="10">
							</td>
						</tr>
						<tr>
							<td width="10" align="right"><img src="<%=request.getContextPath()%>/image/40.gif"/></td>
							<td width="120" align="right">序列号</td>
							<td width="200">
								<input name="serialnum" class="tdt" type="password" size="20" value="" maxlength="15">
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
	 <% Object obj = request.getAttribute("error1");
   	if(obj != null){
   	%>
   		alert("用户名、序列号错误或该用户不是本系统用户");
   	<%
   	} %>
   	</script>
</html>
